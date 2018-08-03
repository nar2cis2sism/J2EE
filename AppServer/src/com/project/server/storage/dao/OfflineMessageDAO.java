package com.project.server.storage.dao;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.OfflineMessage;

import engine.java.dao.DAOTemplate.DAOExpression;
import protocol.socket.req.Message;

import java.util.List;

public class OfflineMessageDAO extends BaseDAO {
    
    /**
     * 添加一条离线消息
     */
    public static void addOfflineMessage(long uid, Message msg) {
        OfflineMessage offlineMsg = new OfflineMessage();
        offlineMsg.uid = uid;
        offlineMsg.creationTime = msg.creationTime;
        offlineMsg.setMessage(msg);
        dao.save(offlineMsg);
    }

    /**
     * 获取离线消息列表
     */
    public static List<OfflineMessage> getOfflineMessageList(long uid, long timestamp) {
        DAOExpression where = DAOExpression.create("uid").eq(uid);
        // 删除无效的离线消息
        dao.edit(OfflineMessage.class).where(where.and("creationTime").not().greaterThan(timestamp)).delete();
        // 返回时间节点之后的离线消息
        return dao.find(OfflineMessage.class).where(where).getAll();
    }
}