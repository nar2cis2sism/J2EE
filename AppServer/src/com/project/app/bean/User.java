package com.project.app.bean;

import com.project.server.network.socket.SocketConnection;
import com.project.server.storage.db.UserInfo;

import protocol.socket.ErrorInfo;
import protocol.util.ProtocolWrapper.ProtocolEntity.ProtocolData;

/**
 * 登录用户
 */
public class User {
    
    public final String token;
    public final String deviceID;
    
    public UserInfo info;
    private SocketConnection conn;
    
    public User(String token, String deviceID, UserInfo userInfo) {
        this.token = token;
        this.deviceID = deviceID;
        this.info = userInfo;
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