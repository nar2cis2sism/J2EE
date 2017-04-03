package app.storage;

import static app.storage.DAOManager.getDAO;
import static app.storage.dao.script.AppUploadScript.uploadApp;
import static app.storage.dao.script.FriendScript.addFriend;
import static app.storage.dao.script.RegisterScript.register;
import static app.storage.dao.script.RegisterScript.editUserInfo;
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
        editUserInfo(register("18311287987", "yanhao"), "闫昊", null);
        editUserInfo(register("15912790679", "tanmengsi"), "王晓庆", "一切都会好起来");
        editUserInfo(register("15012345678", "tanmengsi"), "Jane", "加油哦");
        editUserInfo(register("18311987987", "tanmengsi"), "范永利", null);
        editUserInfo(register("15012395679", "tanmengsi"), "李冰涛", "fire in the hole");
        editUserInfo(register("13972798679", "tanmengsi"), "*658了*", "分享图片");
        editUserInfo(register("15912395679", "tanmengsi"), "Num2", "stranger");
        editUserInfo(register("15972798679", "tanmengsi"), "于美珍", "");
        editUserInfo(register("15912390679", "tanmengsi"), "陶生", "");
        editUserInfo(register("15972790679", "tanmengsi"), "乌托邦", "");
        editUserInfo(register("15012345679", "tanmengsi"), "Jess 杨姐", null);
    }
    
    private static void friendTest() {
        getDAO().createTable(FriendReflog.class, true);
        for (int i = 2; i <= 11; i++) addFriend(1, i);
    }
}