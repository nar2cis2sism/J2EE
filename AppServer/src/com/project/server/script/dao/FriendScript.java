package com.project.server.script.dao;

import static com.project.server.storage.DAOManager.getDAO;

import com.project.server.storage.db.FriendReflog;

import engine.java.dao.DAOTemplate.DAOExpression;

public class FriendScript {
    
    /**
     * 添加好友
     */
    public static void addFriend(long uid, long friend_id) {
        FriendReflog log = getDAO().find(FriendReflog.class)
                .where(DAOExpression.create("user_id").equal(uid)
                .and("friend_id").equal(friend_id))
                .get();
        if (log != null)
        {
            System.out.println(String.format("好友%d已存在", friend_id));
            return;
        }
        
        log = new FriendReflog();
        log.user_id = uid;
        log.friend_id = friend_id;
        log.op = 1;
        log.time = System.currentTimeMillis();
        if (!getDAO().save(log))
        {
            System.out.println(String.format("添加好友%d失败", friend_id));
        }
    }
}