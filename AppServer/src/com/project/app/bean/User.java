package com.project.app.bean;

import com.project.server.network.socket.SocketConnection;
import com.project.server.storage.db.UserInfo;

import protocol.java.ProtocolWrapper.ProtocolEntity.ProtocolData;
import protocol.java.stream.ErrorInfo;

/**
 * 登录用户
 */
public class User {
    
    public String token;
    
    public UserInfo info;
    
    public String deviceID;
    
    public SocketConnection conn;
    
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
     */
    public void push(ProtocolData data) {
        if (conn != null)
        {
            conn.send(0, data);
        }
    }
}