package app.socket;

import static app.socket.SocketManager.CRYPT_KEY;
import static engine.java.log.LogFactory.LOG.log;
import app.bean.User;
import app.servlet.util.Session;
import app.servlet.util.TokenManager;
import engine.java.util.io.ByteDataUtil.ByteData;
import engine.java.util.io.IOUtil;
import engine.java.util.secure.CRCUtility;
import engine.java.util.secure.HexUtil;
import engine.java.util.secure.Obfuscate;
import protocol.java.ProtocolWrapper;
import protocol.java.ProtocolWrapper.ProtocolEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketConnection implements Runnable {
    
    private static final ExecutorService writeThreadPool = Executors.newCachedThreadPool();
    
    private final Socket socket;                        // Socket连接
    private InputStream in;                             // 数据输入流
    private OutputStream out;                           // 数据输出流
    
    private final SocketWrite sw = new SocketWrite();
    
    private boolean isRunning;
    private int retry = 3;

    private long uid;
    private SocketParser parser;

    SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handshake(in = socket.getInputStream(), out = socket.getOutputStream());
        } catch (Throwable e) {
            log("握手失败", e);
            close();
            return;
        }
        
        isRunning = true;
        for (;recv(););
        
        // 循环退出后关闭连接
        User user = Session.getUser(uid);
        if (user != null)
        {
            user.closeSocketConnection();
        }
    }
    
    private void handshake(InputStream in, OutputStream out) throws Exception {
        // 读取16位Token值
        byte[] data = new byte[16];
        in.read(data);
        String token = HexUtil.encode(data);

        // 混淆8位密钥输出16位数据
        byte[] bs = new byte[7];
        System.arraycopy(data, 0, bs, 0, bs.length);
        byte[] key = Obfuscate.obfuscate(CRYPT_KEY, bs);
        out.write(key);
        int crc = in.read();
        
        User user = TokenManager.authenticate(token);
        if (user == null)
        {
            out.write(-1);
            throw new Exception("Token认证失败");
        }
        
        if (crc != CRCUtility.calculate(CRYPT_KEY, CRYPT_KEY.length))
        {

            out.write(-2);
            throw new Exception("CRC校验失败");
        }
        

        // 握手成功
        out.write(0);
        
        uid = user.info.getUid();
        user.buildSocketConnection(this);
        parser = new SocketParser();
    }
    
    private boolean/** loop **/ recv() {
        try {
            while (isRunning)
            {
                ProtocolEntity entity = ProtocolWrapper.parse(in);
                if (entity == null)
                {
                    throw new IOException("read bytes is -1.");
                }

                log("收到socket信令包-" + uid, entity);
                entity.parseBody();
                receive(entity.getCmd(), entity.getMsgId(), entity.getData());
            }
        } catch (Exception e) {
            if (retry-- == 0)
            {
                log(e);
            }
            else
            {
                return true;
            }
        } catch (Throwable e) {
            log(e);
        }
        
        return false;
    }
    
    public void close() {
        isRunning = false;
        IOUtil.closeSilently(socket);
    }

    private void receive(int cmd, int msgId, ByteData data) {
        log("收到socket信令包-" + uid, data);
        parser.parse(cmd, msgId, data);
    }

    /**
     * 发送数据
     */
    public void send(int cmd, int msgId, ByteData data) {
        log("发送socket信令包-" + uid, data);
        try {
            ProtocolEntity entity = ProtocolEntity.newInstance(msgId, cmd, data);
            entity.generateBody();
            log("发送socket信令包-" + uid, entity);
            sw.add(ProtocolWrapper.wrap(entity));
        } catch (Exception e) {
            log(e);
        }
    }
    
    private class SocketWrite implements Runnable {
        
        private final ConcurrentLinkedQueue<byte[]> conns             // 请求队列
        = new ConcurrentLinkedQueue<byte[]>();
        
        private final AtomicBoolean isRunning = new AtomicBoolean();
        
        private int sendCount;
        
        public void add(byte[] data) {
            conns.add(data);
            start();
        }

        private void start() {
            if (isRunning.compareAndSet(false, true))
            {
                writeThreadPool.execute(this);
            }
        }

        @Override
        public void run() {
            send();
            isRunning.set(false);
            if (!conns.isEmpty())
            {
                start();
            }
        }
        
        private void send() {
            try {
                byte[] data;
                try {
                    while ((data = conns.poll()) != null)
                    {
                        out.write(data);
                        if (sendCount++ >= 5)
                        {
                            sendout();
                        }
                    }
                } finally {
                    sendout();
                }
            } catch (IOException e) {
                log(e);
            }
        }
        
        private void sendout() throws IOException {
            out.flush();
            sendCount = 0;
        }
    }
}