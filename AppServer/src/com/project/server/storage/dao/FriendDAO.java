package com.project.server.storage.dao;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.DAOTemplate.DAOExpression;

public class FriendDAO extends BaseDAO {
    
    /**
     * 获取最新的操作记录时间
     * 
     * @param uid 用户ID
     */
    public static Long getLatestReflogTime(long uid) {
        FriendReflog log = dao.find(FriendReflog.class)
        .select("time")
        .where(DAOExpression.create("user_id").eq(uid))
        .orderDesc("time")
        .get();
        if (log != null)
        {
            return log.time;
        }
        
        return null;
    }
    
    /**
     * 添加删除好友
     * 
     * @return -1:操作失败or操作记录变更时间戳
     */
    public static long addFriend(UserInfo user, UserInfo contact, int op) {
        boolean success;
        
        FriendReflog log = dao.find(FriendReflog.class)
        .where(DAOExpression.create("user_id").eq(user.getUid())
        .and("friend_id").eq(contact.getUid()))
        .get();
        if (log == null)
        {
            log = new FriendReflog();
            log.user_id = user.getUid();
            log.friend_id = contact.getUid();
            log.op = op;
            log.time = System.currentTimeMillis();
            success = dao.save(log);
        }
        else
        {
            log.op = op;
            log.time = System.currentTimeMillis();
            success = dao.update(log, "op", "time");
        }
        
        return success ? log.time : -1;
    }
}