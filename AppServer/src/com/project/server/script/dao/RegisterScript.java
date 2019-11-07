package com.project.server.script.dao;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.util.secure.CryptoUtil;
import engine.java.util.secure.HexUtil;

public class RegisterScript extends BaseDAO {
    
    /**
     * 注册用户
     */
    public static void register(String username, String password, String nickname, String signature) {
        UserInfo info = new UserInfo();
        info.username = username;
        info.password = HexUtil.encode(CryptoUtil.SHA1((password + "000").getBytes()));
        info.register_time = System.currentTimeMillis();
        
        info.nickname = nickname;
        info.signature = signature;
        info.version++;
        if (!dao.save(info))
        {
            System.out.println(String.format("用户%s注册失败", username));
        }
    }
}