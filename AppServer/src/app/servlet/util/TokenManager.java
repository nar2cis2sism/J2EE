package app.servlet.util;

import app.bean.User;
import app.bean.db.UserInfo;
import app.servlet.AppServlet;
import engine.java.log.LogFactory.LOG;
import engine.java.log.LogUtil;
import engine.java.util.KeyExpiryMap;
import engine.java.util.Util;
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
        if (AppServlet.isTest) return backdoor(userInfo, deviceID);
        
        return _generateToken(userInfo, deviceID);
    }
    
    private static String _generateToken(UserInfo userInfo, String deviceID)
            throws UnsupportedEncodingException {
        // 用户名+设备号保证唯一性，加盐保证随机性
        int salt = Util.getRandom(0, Integer.MAX_VALUE);
        String str = userInfo.username + deviceID + salt;
        // 加密后16个字节
        byte[] data = CryptoUtil.md5(str.getBytes());
        // 编码为32位字符串
        String token = HexUtil.encode(data);
        
        userMap.put(token, userInfo.getUid(), expiryTime);
        User user = Session.saveUser(token, userInfo, deviceID);
        if (user != null)
        {
            user.invalidate();
            userMap.remove(user.token);
        }
        
        return token;
    }
    
    /**
     * Token认证
     * @return 用户信息
     */
    public static User authenticate(String token) {
        LOG.log(LogUtil.getCallerStackFrame(), "认证Token=" + token);
        Long uid = userMap.get(token);
        if (uid != null)
        {
            User user = Session.getUser(uid);
            if (user == null)
            {
                userMap.remove(token);
            }
            
            return user;
        }
        
        return null;
    }
    
    /**
     * 一个测试后门
     */
    private static String backdoor(UserInfo userInfo, String deviceID)
            throws UnsupportedEncodingException {
        String token = null;
        if ("yanhao".equals(userInfo.username))
        {
            User user = Session.getUser(userInfo.getUid());
            if (user != null)
            {
                token = user.token;
            }
            else
            {
                token = "yanhao";
                
                userMap.put(token, userInfo.getUid(), expiryTime);
                Session.saveUser(token, userInfo, deviceID);
            }
        }
        else
        {
            token = _generateToken(userInfo, deviceID);
        }
        
        return token;
    }
}