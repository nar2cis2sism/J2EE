package com.project.server.script.dao;

import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;

public class FriendScript extends UserDAO {
    
    /**
     * 添加好友
     */
    public static void addFriend(String me, String friend) {
        UserInfo me_info = getUserByUsername(me);
        UserInfo friend_info = getUserByUsername(friend);
        
        FriendReflog log = new FriendReflog();
        log.user_id = me_info.getUid();
        log.friend_id = friend_info.getUid();
        log.time = System.currentTimeMillis();
        if (!dao.save(log))
        {
            System.out.println(String.format("%s添加好友%s失败", me, friend));
        }
    }
}