package com.project.server.script;

import com.project.server.script.dao.AppUploadScript;
import com.project.server.script.dao.FriendScript;
import com.project.server.script.dao.MessageScript;
import com.project.server.script.dao.RegisterScript;
import com.project.server.storage.DAOManager;
import com.project.server.storage.db.AppUpgradeInfo;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.OfflineMessage;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.DAOTemplate;

import org.apache.commons.lang.SystemUtils;

import java.io.File;

/**
 * 数据库测试脚本
 * 
 * @author Daimon
 */
public class DAOScript extends MockData {
    
    public static void main(String[] args) {
        createTable();
        
        uploadApp();
        register();
        addFriend();
        sendMessage();
        
        System.out.println();
        System.out.println("OK");
    }
    
    private static void createTable() {
        DAOTemplate dao = DAOManager.getDAO();
        dao.createTable(AppUpgradeInfo.class, true);
        dao.createTable(UserInfo.class, true);
        dao.createTable(FriendReflog.class, true);
        dao.createTable(OfflineMessage.class, true);
    }
    
    private static void uploadApp() {
        AppUploadScript.uploadApp(0, "约吧", "2.0.0", "版本更新", 2, 
                new File(SystemUtils.getUserDir(), "WebRoot/WEB-INF/upgrade.apk"));
    }
    
    private static void register() {
        register(荣狄王);
        register(王晓庆);
        register(Jane);
        register(范永利);
        register(李冰涛);
        register(_658了);
        register(Num2);
        register(于美珍);
        register(陶生);
        register(乌托邦);
        register(Jess_杨姐);
        register(老婆);
    }
    
    private static void register(UserInfo info) {
        RegisterScript.register(info.username, info.password, info.nickname, info.signature);
    }
    
    private static void addFriend() {
        addFriend(王晓庆);
        addFriend(Jane);
        addFriend(范永利);
        addFriend(李冰涛);
        addFriend(_658了);
        addFriend(Num2);
        addFriend(于美珍);
        addFriend(陶生);
        addFriend(乌托邦);
        addFriend(Jess_杨姐);
    }
    
    private static void addFriend(UserInfo info) {
        FriendScript.addFriend(荣狄王.username, info.username);
    }
    
    private static void sendMessage() {
        sendMessage(李冰涛, "我是李冰涛");
        sendMessage(乌托邦, "我是乌托邦");
    }
    
    private static void sendMessage(UserInfo info, String content) {
        MessageScript.sendOfflineMessage(info.username, 荣狄王.username, content);
    }
}

class MockData {
    
    public static final UserInfo 荣狄王;
    public static final UserInfo 王晓庆;
    public static final UserInfo Jane;
    public static final UserInfo 范永利;
    public static final UserInfo 李冰涛;
    public static final UserInfo _658了;
    public static final UserInfo Num2;
    public static final UserInfo 于美珍;
    public static final UserInfo 陶生;
    public static final UserInfo 乌托邦;
    public static final UserInfo Jess_杨姐;
    public static final UserInfo 老婆;
    
    static
    {
        荣狄王 = generateUser("18318066253", "123456", "荣狄王", "人生没有后悔药 既然选错了路 哭着也要走下去");
        王晓庆 = generateUser("15912790679", "123456", "王晓庆", "一切都会好起来");
        Jane = generateUser("15012345678", "123456", "Jane", "加油哦");
        范永利 = generateUser("18311987987", "123456", "范永利", null);
        李冰涛 = generateUser("15012395679", "123456", "李冰涛", "fire in the hole");
        _658了 = generateUser("13972798679", "123456", "*658了*", "分享图片");
        Num2 = generateUser("15912395679", "123456", "Num2", "stranger");
        于美珍 = generateUser("15972798679", "123456", "于美珍", "");
        陶生 = generateUser("15912390679", "123456", "陶生", "");
        乌托邦 = generateUser("15972790679", "123456", "乌托邦", "");
        Jess_杨姐 = generateUser("15012345679", "123456", "Jess 杨姐", null);
        老婆 = generateUser("13872530618", "123456", "老婆", null);
    }
    
    private static UserInfo generateUser(String username, String password, String nickname, String signature) {
        UserInfo info = new UserInfo();
        info.username = username;
        info.password = password;
        info.nickname = nickname;
        info.signature = signature;
        return info;
    }
}