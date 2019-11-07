package com.project.server.network.socket;

import static engine.java.util.common.LogFactory.LOG.log;

import com.project.app.bean.User;
import com.project.app.util.TokenManager;
import com.project.app.util.UserManager;
import com.project.util.GsonUtil;

import engine.java.util.io.ByteDataUtil;
import engine.java.util.io.IOUtil;
import engine.java.util.secure.CRCUtil;
import engine.java.util.secure.HexUtil;
import engine.java.util.secure.Obfuscate;
import protocol.util.ProtocolWrapper;
import protocol.util.ProtocolWrapper.ProtocolEntity;
import protocol.util.ProtocolWrapper.ProtocolEntity.ProtocolData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketConnection implements SocketParam, Runnable {
    
    private static final ExecutorService writeThreadPool = Executors.newCachedThreadPool();
    
    private final Socket socket;                        // Socket连接
    private InputStream in;                             // 数据输入流
    private OutputStream out;                           // 数据输出流
    
    private final SocketWriter sw = new SocketWriter();
    
    private boolean isRunning;
    private int retry = 3;

    private long uid;

    SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handshake(in = socket.getInputStream(), out = socket.getOutputStream());
            log("握手成功", "uid=" + uid);
        } catch (Throwable e) {
            log("握手失败", e);
            close();
            return;
        }

        SocketListener.onConnected(uid);
        for (isRunning = true;recv(););
        // 循环退出后关闭连接
        SocketListener.onClosed(uid);
        if (isRunning)
        {
            User user = UserManager.getUser(uid);
            if (user != null)
            {
                user.setSocketConnection(null);
            }
        }
    }
    
    private void handshake(InputStream in, OutputStream out) throws Exception {
        // 读取Token值
        byte[] data = new byte[16];
        in.read(data);
        String token = HexUtil.encode(data);
        // 混淆64位密钥输出128位数据
        byte[] key = new byte[7];
        System.arraycopy(data, 0, key, 0, key.length);
        out.write(Obfuscate.obfuscate(CRYPT_KEY, key));
        // 读取crc值
        byte[] bs = new byte[4];
        in.read(bs);
        int crc = ByteDataUtil.bytesToInt_HL(bs, 0);
        
        User user = TokenManager.authenticate(token);
        if (user == null)
        {
            out.write(1);
            throw new Exception("Token认证失败");
        }
        
        if (crc != CRCUtil.calculate(CRYPT_KEY))
        {
            out.write(2);
            throw new Exception("CRC校验失败");
        }
        // 握手成功
        out.write(0);
        
        uid = user.info.getUid();
        user.setSocketConnection(this);
    }
    
    private boolean recv() {
        try {
            while (isRunning)
            {
                ProtocolEntity entity = ProtocolWrapper.parse(in);
                if (entity == null)
                {
                    throw new IOException("read bytes is -1.");
                }

                log("收到socket信令包-" + uid, entity);
                SocketListener.onReceive(uid);
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

    private void receive(int cmd, int msgId, ProtocolData data) {
        log("收到socket信令包-" + uid, data.getClass().getSimpleName() + GsonUtil.toJson(data));
        ProtocolData ack = SocketDispatcher.dispatch(cmd, data, UserManager.getUser(uid));
        if (ack != null)
        {
            send(msgId, ack);
        }
    }

    /**
     * 发送数据
     */
    public void send(int msgId, ProtocolData data) {
        log("发送socket信令包-" + uid, data.getClass().getSimpleName() + GsonUtil.toJson(data));
        try {
            ProtocolEntity entity = ProtocolEntity.newInstance(msgId, data);
            entity.generateBody();
            log("发送socket信令包-" + uid, entity);
            sw.add(ProtocolWrapper.wrap(entity));
        } catch (Exception e) {
            log(e);
        }
    }
    
    private class SocketWriter implements Runnable {
        
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
            if (!conns.isEmpty() && !socket.isClosed())
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
            } catch (Exception e) {
                log(e);
            }
        }
        
        private void sendout() throws IOException {
            out.flush();
            sendCount = 0;
        }
    }
}