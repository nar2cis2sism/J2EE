package app.storage;

import app.storage.db.AppUpgradeInfo;
import app.storage.db.FriendReflog;
import app.storage.db.UserInfo;
import app.storage.script.AppUploadScript;
import app.storage.script.FriendScript;
import app.storage.script.RegisterScript;
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
                new File("D:\\Work\\Java\\Github\\J2ee\\AppServer\\WebRoot\\WEB-INF\\app-debug2.apk"));
    }
    
    private static void register() {
        RegisterScript.register("18311287987", "yanhao", "闫昊", null);
        RegisterScript.register("15912790679", "tanmengsi", "王晓庆", "一切都会好起来");
        RegisterScript.register("15012345678", "tanmengsi", "Jane", "加油哦");
        RegisterScript.register("18311987987", "tanmengsi", "范永利", null);
        RegisterScript.register("15012395679", "tanmengsi", "李冰涛", "fire in the hole");
        RegisterScript.register("13972798679", "tanmengsi", "*658了*", "分享图片");
        RegisterScript.register("15912395679", "tanmengsi", "Num2", "stranger");
        RegisterScript.register("15972798679", "tanmengsi", "于美珍", "");
        RegisterScript.register("15912390679", "tanmengsi", "陶生", "");
        RegisterScript.register("15972790679", "tanmengsi", "乌托邦", "");
        RegisterScript.register("15012345679", "tanmengsi", "Jess 杨姐", null);
    }
    
    private static void addFriend() {
        for (int i = 2; i <= 11; i++) FriendScript.addFriend(1, i);
    }
}