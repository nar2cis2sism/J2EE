package com.project.server.script.dao;

import static com.project.server.storage.DAOManager.getDAO;

import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.DAOTemplate.DAOExpression;

public class FriendScript {
    
    /**
     * 添加好友
     */
    public static void addFriend(String me, String friend) {
        UserInfo me_info = UserDAO.getUserByUsername(me);
        UserInfo friend_info = UserDAO.getUserByUsername(friend);
        
        FriendReflog log = getDAO().find(FriendReflog.class)
                .where(DAOExpression.create("user_id").eq(me_info.getUid())
                .and("friend_id").eq(friend_info.getUid()))
                .get();
        boolean hasLog = log != null;
        if (!hasLog)
        {
            log = new FriendReflog();
            log.user_id = me_info.getUid();
            log.friend_id = friend_info.getUid();
        }

        log.action = 0;
        log.time = System.currentTimeMillis();
        if (hasLog && !getDAO().update(log, "action", "time") || !getDAO().save(log))
        {
            System.out.println(String.format("%s添加好友%s失败", me, friend));
        }
    }
}