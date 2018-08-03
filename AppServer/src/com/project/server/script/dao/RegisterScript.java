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
        UserInfo item = new UserInfo();
        item.username = username;
        item.password = HexUtil.encode(CryptoUtil.SHA1((password + "000").getBytes()));
        item.nickname = nickname;
        item.signature = signature;
        item.version = item.register_time = System.currentTimeMillis();
        if (!dao.save(item))
        {
            System.out.println(String.format("用户%s注册失败", username));
        }
    }
}