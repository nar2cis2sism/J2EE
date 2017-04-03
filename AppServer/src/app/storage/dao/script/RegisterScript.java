package app.storage.dao.script;

import static app.storage.DAOManager.getDAO;
import app.storage.dao.UserDAO;
import app.storage.dao.db.UserInfo;
import engine.java.dao.DAOTemplate.DAOSQLBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;
import engine.java.util.secure.CryptoUtil;
import engine.java.util.secure.HexUtil;

public class RegisterScript {
    
    /**
     * 注册用户
     */
    public static UserInfo register(String username, String password) {
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
            item.register_time = System.currentTimeMillis();
            if (getDAO().save(item))
            {
                System.out.println("注册成功");
            }
            else
            {
                System.out.println("注册失败");
            }
        }
        
        return item;
    }
    
    public static void editUserInfo(UserInfo item, String nickname, String signature) {
        if (item == null) return;
        item.nickname = nickname;
        item.signature = signature;
        if (getDAO().edit(DAOSQLBuilder.create(UserInfo.class)
                .setWhereClause(DAOExpression.create("username").equal(item.username)), 
                item, "nickname", "signature"))
        {
            System.out.println("资料修改成功");
        }
        else
        {
            System.out.println("资料修改失败");
        }
    }
}