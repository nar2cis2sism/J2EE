package web.app.storage.dao;

import web.app.storage.DAOManager.BaseDAO;
import web.app.storage.db.User;

public class UserDAO extends BaseDAO {
    
    public static User getUser(long id) {
        return findItemByProperty(User.class, "id", id);
    }
    
    /**
     * 根据用户名获取用户信息
     */
    public static User getUserByUsername(String username) {
        return findItemByProperty(User.class, "username", username);
    }
}