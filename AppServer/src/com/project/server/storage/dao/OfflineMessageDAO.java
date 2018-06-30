package com.project.server.storage.dao;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.OfflineMessage;

import java.util.List;

import engine.java.dao.DAOTemplate.DAOExpression;
import protocol.java.stream.req.Message;

public class OfflineMessageDAO extends BaseDAO {
    
    /**
     * 添加一条离线消息
     */
    public static void addOfflineMessage(long uid, Message msg) {
        OfflineMessage offlineMsg = new OfflineMessage();
        offlineMsg.uid = uid;
        offlineMsg.setMessage(msg);
        dao.save(offlineMsg);
    }
    
    /**
     * 获取离线消息列表，然后删除
     */
    public static List<OfflineMessage> getAndRemoveOfflineMessages(long uid) {
        DAOExpression where = DAOExpression.create("uid").eq(uid);
        
        List<OfflineMessage> list = dao.find(OfflineMessage.class).where(where).getAll();
        dao.edit(OfflineMessage.class).where(where).delete();
        
        return list;
    }
}