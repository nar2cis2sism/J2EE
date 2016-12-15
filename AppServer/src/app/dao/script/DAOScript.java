package app.dao.script;

import static app.dao.script.AppUploadScript.uploadApp;
import static app.dao.script.RegisterScript.register;
import static app.dao.script.FriendScript.addFriend;
import static app.dao.DAOManager.getDAO;
import app.db.AppUpgradeInfo;
import app.db.FriendReflog;
import app.db.UserInfo;

import java.io.File;

/**
 * 数据库测试脚本
 * @author Daimon
 */
public class DAOScript {
    
    public static void main(String[] args) {
//        uploadAppTest();
//        registerTest();
//        friendTest();
    }
    
    private static void uploadAppTest() {
        getDAO().createTable(AppUpgradeInfo.class, true);
        uploadApp(0, "撩我", "1.0.0", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\app-debug2.apk"), "", 2);
        uploadApp(0, "撩我", "2.0.0", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\app-debug2.apk"), "版本更新", 2);
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