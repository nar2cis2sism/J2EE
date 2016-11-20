package app.bean;

import app.db.UserInfo;
import app.socket.SocketConnection;
import engine.java.util.io.ByteDataUtil.ByteData;
import protocol.java.stream.ErrorInfo;

public class User {
    
    public String token;
    
    public UserInfo info;
    
    public String deviceID;
    
    public SocketConnection conn;
    
    public void buildSocketConnection(SocketConnection c) {
        if (conn != null)
        {
            conn.close();
        }
        
        conn = c;
    }
    
    public void closeSocketConnection() {
        if (conn != null)
        {
            conn.close();
            conn = null;
        }
    }
    
    public void invalidate() {
        if (conn != null)
        {
            conn.send(ErrorInfo.CMD, 0, new ErrorInfo(410));
            conn.close();
            conn = null;
        }
    }
    
    /**
     * 推送消息给用户
     * @param cmd
     * @param msgId
     * @param data
     */
    public void push(int cmd, int msgId, ByteData data) {
        if (conn != null)
        {
            conn.send(cmd, msgId, data);
        }
    }
}