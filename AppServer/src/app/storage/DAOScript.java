package app.storage;

import static app.storage.DAOManager.getDAO;
import static app.storage.dao.script.AppUploadScript.uploadApp;
import static app.storage.dao.script.FriendScript.addFriend;
import static app.storage.dao.script.RegisterScript.register;
import app.storage.dao.db.AppUpgradeInfo;
import app.storage.dao.db.FriendReflog;
import app.storage.dao.db.UserInfo;

import java.io.File;

/**
 * 数据库测试脚本
 * @author Daimon
 */
public class DAOScript {
    
    public static void main(String[] args) {
        uploadAppTest();
        registerTest();
        friendTest();
    }
    
    private static void uploadAppTest() {
        getDAO().createTable(AppUpgradeInfo.class, true);
        uploadApp(0, "约吧", "1.0.0", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\app-debug2.apk"), "", 2);
        uploadApp(0, "约吧", "2.0.0", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\app-debug2.apk"), "版本更新", 2);
    }
    
    private static void registerTest() {
        getDAO().createTable(UserInfo.class, true);
        register("18311287987", "yanhao");
        register("15012345678", "tanmengsi");
    }
    
    private static void friendTest() {
        getDAO().createTable(FriendReflog.class, true);
        addFriend(1, 2);
    }
}