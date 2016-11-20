package app.servlet.util;

import app.bean.User;
import app.db.UserInfo;

import java.util.HashMap;

public class Session {
    
    private static final HashMap<Long, User> userMap
    = new HashMap<Long, User>();                                    // 用户查询表
    
    public static User saveUser(String token, UserInfo userInfo, String deviceID) {
        User user = new User();
        user.token = token;
        user.info = userInfo;
        user.deviceID = deviceID;
        return userMap.put(userInfo.getUid(), user);
    }
    
    public static User getUser(long uid) {
        return userMap.get(uid);
    }
}