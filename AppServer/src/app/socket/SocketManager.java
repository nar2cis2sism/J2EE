package app.socket;

import app.servlet.AppServlet;
import engine.java.log.LogFactory.LOG;
import engine.java.util.MyThreadFactory;
import protocol.java.ProtocolWrapper;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketManager implements Runnable {
    
    private static final int PORT = 10236;                              // 监听端口号
    
    static final byte[] CRYPT_KEY
    = {0x21, 0x45, (byte) 0x83, 0x39, 0x46, 0x75, (byte) 0x88, 0x11};   // 数据加解密密钥
    
    private static SocketManager instance;
    
    private static String address;
    
    /**
     * 启动socket服务器
     */
    public static synchronized void setup() {
        if (instance == null)
        {
            ProtocolWrapper.setEncryptSecret(CRYPT_KEY);
            instance = new SocketManager();
        }
    }
    
    static final SocketManager getInstance() {
        return instance;
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
                serverSocket.bind(new InetSocketAddress(AppServlet.SERVER_IP, PORT));
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
        InetAddress inetAddress = serverSocket.getInetAddress();
        if (inetAddress != null)
        {
            address = inetAddress.getHostAddress() + ":" + serverSocket.getLocalPort();
        }
    }
    
    public static String getAddress() {
        return address;
    }
}