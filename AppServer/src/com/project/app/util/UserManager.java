package com.project.app.util;

import java.util.HashMap;

import com.project.app.bean.User;
import com.project.server.storage.db.UserInfo;

/**
 * 在线用户管理器
 */
public class UserManager {
    
    private static final HashMap<Long, User> userMap
    = new HashMap<Long, User>();                                    // 用户查询表
    
    /**
     * 缓存登录用户
     * 
     * @return 被挤掉的用户
     */
    public static User saveUser(String token, UserInfo userInfo, String deviceID) {
        return userMap.put(userInfo.getUid(), new User(token, userInfo, deviceID));
    }
    
    public static User getUser(long uid) {
        return userMap.get(uid);
    }
}