package com.project.app.bean;

import com.project.server.network.socket.SocketConnection;
import com.project.server.storage.db.UserInfo;

import protocol.java.ProtocolWrapper.ProtocolEntity.ProtocolData;
import protocol.java.stream.ErrorInfo;

/**
 * 登录用户
 */
public class User {
    
    public final String token;
    
    public final UserInfo info;
    
    public final String deviceID;
    
    private SocketConnection conn;
    
    public User(String token, UserInfo userInfo, String deviceID) {
        this.token = token;
        this.info = userInfo;
        this.deviceID = deviceID;
    }
    
    public void setSocketConnection(SocketConnection c) {
        if (conn != null)
        {
            conn.close();
        }
        
        conn = c;
    }
    
    public void invalidate() {
        if (conn != null)
        {
            conn.send(0, new ErrorInfo(410));
            conn.close();
            conn = null;
        }
    }
    
    /**
     * 推送消息给用户
     * 
     * @return 用户在线状态
     */
    public boolean push(ProtocolData data) {
        if (conn != null)
        {
            conn.send(0, data);
            return true;
        }
        
        return false;
    }
}