package com.project.server.script;

import com.project.server.script.dao.AppUploadScript;
import com.project.server.script.dao.FriendScript;
import com.project.server.script.dao.RegisterScript;
import com.project.server.storage.DAOManager;
import com.project.server.storage.db.AppUpgradeInfo;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.DAOTemplate;

import java.io.File;

/**
 * 数据库测试脚本
 * 
 * @author Daimon
 */
public class DAOScript {
    
    public static void main(String[] args) {
        createTable();
        
        uploadApp();
        register();
        addFriend();
    }
    
    private static void createTable() {
        DAOTemplate dao = DAOManager.getDAO();
        dao.createTable(AppUpgradeInfo.class, true);
        dao.createTable(UserInfo.class, true);
        dao.createTable(FriendReflog.class, true);
    }
    
    private static void uploadApp() {
        AppUploadScript.uploadApp(0, "约吧", "2.0.0", "版本更新", 2, 
                new File("D:\\Project\\Github\\J2ee\\AppServer\\WebRoot\\WEB-INF\\upgrade.apk"));
    }
    
    private static void register() {
        RegisterScript.register("18311287987", "18311287987", "荣狄王", null);
        RegisterScript.register("15912790679", "18311287987", "王晓庆", "一切都会好起来");
        RegisterScript.register("15012345678", "18311287987", "Jane", "加油哦");
        RegisterScript.register("18311987987", "18311287987", "范永利", null);
        RegisterScript.register("15012395679", "18311287987", "李冰涛", "fire in the hole");
        RegisterScript.register("13972798679", "18311287987", "*658了*", "分享图片");
        RegisterScript.register("15912395679", "18311287987", "Num2", "stranger");
        RegisterScript.register("15972798679", "18311287987", "于美珍", "");
        RegisterScript.register("15912390679", "18311287987", "陶生", "");
        RegisterScript.register("15972790679", "18311287987", "乌托邦", "");
        RegisterScript.register("15012345679", "18311287987", "Jess 杨姐", null);
    }
    
    private static void addFriend() {
        FriendScript.addFriend("18311287987", "15912790679");
        FriendScript.addFriend("18311287987", "15012345678");
        FriendScript.addFriend("18311287987", "18311987987");
        FriendScript.addFriend("18311287987", "15012395679");
        FriendScript.addFriend("18311287987", "13972798679");
        FriendScript.addFriend("18311287987", "15912395679");
        FriendScript.addFriend("18311287987", "15972798679");
        FriendScript.addFriend("18311287987", "15912390679");
        FriendScript.addFriend("18311287987", "15972790679");
        FriendScript.addFriend("18311287987", "15012345679");
    }
}