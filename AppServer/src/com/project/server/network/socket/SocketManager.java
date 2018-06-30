package com.project.server.network.socket;

import com.project.app.util.UserManager;
import com.project.server.ServerConfig;
import com.project.server.storage.dao.OfflineMessageDAO;
import com.project.server.storage.db.OfflineMessage;

import engine.java.util.extra.MyThreadFactory;
import engine.java.util.log.LogFactory.LOG;
import protocol.java.ProtocolWrapper;
import protocol.java.stream.req.Message;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketManager implements SocketParam, Runnable {
    
    private static SocketManager instance;
    
    private static String address;
    
    /**
     * 启动socket服务器
     */
    public static void setup() {
        if (instance == null)
        {
            byte[] key = new byte[16];
            System.arraycopy(CRYPT_KEY, 0, key, 0, CRYPT_KEY.length);
            System.arraycopy(CRYPT_KEY, 0, key, CRYPT_KEY.length, CRYPT_KEY.length);
            
            ProtocolWrapper.setEncryptSecret(key);
            instance = new SocketManager();
        }
    }

    /******************************* 华丽丽的分割线 *******************************/
    
    private final ExecutorService serverThreadPool;
    private final ExecutorService socketThreadPool;
    
    private SocketManager() {
        serverThreadPool = Executors.newSingleThreadExecutor();
        socketThreadPool = Executors.newCachedThreadPool(new MyThreadFactory("Socket网络连接"));
        
        start();
    }
    
    private void start() {
        serverThreadPool.execute(this);
    }

    @Override
    public void run() {
        accept();
        start();
    }
    
    private void accept() {
        try {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket();
                serverSocket.setReuseAddress(true);
                serverSocket.bind(new InetSocketAddress(ServerConfig.SERVER_IP, PORT));
                setAddress(serverSocket);
                while (true)
                {
                    Socket socket = serverSocket.accept();
                    LOG.log(String.format("收到来自%s的socket连接:", 
                            String.valueOf(socket.getRemoteSocketAddress())));
                    socketThreadPool.execute(new SocketConnection(socket));
                }
            } finally {
                if (serverSocket != null)
                {
                    serverSocket.close();
                }
            }
        } catch (Exception e) {
            LOG.log(e);
        }
    }
    
    private void setAddress(ServerSocket serverSocket) {
        address = serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort();
    }
    
    public static String getAddress() {
        return address;
    }
}

interface SocketParam {
    
    long TIMEOUT = 4 * 60 * 1000;                   // 连接超时断开（4分钟）
    
    byte[] CRYPT_KEY                                // 数据加解密密钥
    = {0x21, 0x45, (byte) 0x83, 0x39,
       0x46, 0x75, (byte) 0x88, 0x11};
    
    int PORT = 10236;                               // 监听端口号
}

class SocketListener {

    /**
     * 连接已建立
     */
    public static void onConnected(long uid) {
        SocketTimeOut.getInstance().active(uid);
        // 推送离线消息
        pushOfflineMessage(uid);
    }

    /**
     * 联网数据接收
     */
    public static void onReceive(long uid) {
        SocketTimeOut.getInstance().active(uid);
    }
    
    private static void pushOfflineMessage(long uid) {
        List<OfflineMessage> list = OfflineMessageDAO.getAndRemoveOfflineMessages(uid);
        if (list != null && !list.isEmpty())
        {
            protocol.java.stream.OfflineMessage _ = new protocol.java.stream.OfflineMessage();
            Message[] message = _.message = new Message[list.size()];
            int index = 0;
            for (OfflineMessage offlineMsg : list)
            {
                message[index++] = offlineMsg.getMessage();
            }
            
            UserManager.getUser(uid).push(_);
        }
    }
}