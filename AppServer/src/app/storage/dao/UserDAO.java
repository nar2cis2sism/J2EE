package app.storage.dao;

import app.storage.DAOManager.BaseDAO;
import app.storage.dao.db.UserInfo;

public class UserDAO extends BaseDAO {
    
    /**
     * 根据用户名获取用户信息
     */
    public static UserInfo getUserByUsername(String username) {
        return findItemByProperty(UserInfo.class, "username", username);
    }
    
    /**
     * 根据用户ID获取用户信息
     */
    public static UserInfo getUserById(long uid) {
        return findItemByProperty(UserInfo.class, "uid", uid);
    }
}