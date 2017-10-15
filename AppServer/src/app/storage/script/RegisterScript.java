package app.storage.script;

import static app.storage.DAOManager.getDAO;
import app.storage.dao.UserDAO;
import app.storage.db.UserInfo;
import engine.java.util.secure.CryptoUtil;
import engine.java.util.secure.HexUtil;

public class RegisterScript {
    
    /**
     * 注册用户
     */
    public static void register(String username, String password, String nickname, String signature) {
        UserInfo item = UserDAO.getUserByUsername(username);
        if (item != null)
        {
            System.out.println(String.format("用户%s已存在", username));
        }
        else
        {
            item = new UserInfo();
            item.username = username;
            item.password = HexUtil.encode(CryptoUtil.SHA1((password + "000").getBytes()));
            item.nickname = nickname;
            item.signature = signature;
            item.version = item.register_time = System.currentTimeMillis();
            if (!getDAO().save(item))
            {
                System.out.println(String.format("用户%s注册失败", username));
            }
        }
    }
}