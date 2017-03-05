package app.dao.script;

import static app.dao.DAOManager.getDAO;
import app.bean.db.UserInfo;
import app.dao.UserDAO;
import engine.java.util.secure.CryptoUtil;
import engine.java.util.secure.HexUtil;

public class RegisterScript {
    
    /**
     * 注册用户
     */
    public static void register(String username, String password) {
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
            item.registerTime = System.currentTimeMillis();
            if (getDAO().save(item))
            {
                System.out.println("注册成功");
            }
            else
            {
                System.out.println("注册失败");
            }
        }
    }
}