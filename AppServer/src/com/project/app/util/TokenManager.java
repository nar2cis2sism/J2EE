package com.project.app.util;

import com.project.app.bean.User;
import com.project.server.storage.db.UserInfo;

import engine.java.util.Util;
import engine.java.util.extra.KeyExpiryMap;
import engine.java.util.secure.CryptoUtil;
import engine.java.util.secure.HexUtil;

import java.io.UnsupportedEncodingException;

/**
 * 凭证管理器
 */
public class TokenManager {
    
    /** 过期时间 **/
    private static final long expiryTime = 7 * 24 * 60 * 60 * 1000;     // 7天
    
    private static final KeyExpiryMap<String, Long> userMap
    = new KeyExpiryMap<String, Long>();                                 // 用户查询表[Token-uid]
    
    /**
     * 生成32位Token串（解码后为16个字节数据用于Socket握手）
     */
    public static String generateToken(UserInfo userInfo, String deviceID)
            throws UnsupportedEncodingException {
        // 用户名+设备号保证唯一性，加盐保证随机性
        int salt = Util.getRandom(0, Integer.MAX_VALUE);
        String str = userInfo.username + deviceID + salt;
        // 加密后16个字节
        byte[] data = CryptoUtil.md5(str.getBytes());
        // 编码为32位字符串
        String token = HexUtil.encode(data);
        
        userMap.put(token, userInfo.getUid(), expiryTime);
        User user = UserManager.saveUser(token, deviceID, userInfo);
        if (user != null)
        {
            user.invalidate();
            userMap.remove(user.token);
        }
        
        return token;
    }
    
    /**
     * Token认证
     */
    public static User authenticate(String token) {
        Long uid = userMap.get(token);
        if (uid != null)
        {
            User user = UserManager.getUser(uid);
            if (user == null)
            {
                userMap.remove(token);
            }
            
            return user;
        }
        
        return null;
    }
}