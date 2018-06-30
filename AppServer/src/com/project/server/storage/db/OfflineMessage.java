package com.project.server.storage.db;

import com.project.util.GsonUtil;

import protocol.java.stream.req.Message;
import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

/**
 * 离线消息
 */
@DAOTable
public class OfflineMessage {
    
    @DAOPrimaryKey(autoincrement=true)
    private long id;

    @DAOProperty
    public long uid;

    @DAOProperty
    private String msg;
    
    public void setMessage(Message msg) {
        this.msg = GsonUtil.toJson(msg);
    }
    
    public Message getMessage() {
        return GsonUtil.parseJson(msg, Message.class);
    }
}