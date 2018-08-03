package com.project.server.storage.db;

import com.project.util.GsonUtil;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;
import protocol.socket.req.Message;

/**
 * 离线消息
 */
@DAOTable
public class OfflineMessage {
    
    @DAOPrimaryKey(autoincrement=true)
    private long id;

    @DAOProperty
    public long uid;                        // 用户唯一标识

    @DAOProperty
    public long creationTime;               // 消息创建时间
    
    @DAOProperty
    private String msg;                     // 聊天消息
    
    public void setMessage(Message msg) {
        this.msg = GsonUtil.toJson(msg);
    }
    
    public Message getMessage() {
        return GsonUtil.parseJson(msg, Message.class);
    }
}