package app.dao;

import app.bean.db.UserInfo;
import app.dao.DAOManager.BaseDAO;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class UserDAO extends BaseDAO {
    
    public static UserInfo[] getUserByIds(long... uid) {
        DAOExpression whereClause = DAOExpression.create("uid").in(uid);
        
        return getDAO().find(DAOQueryBuilder.create(UserInfo.class)
                .setWhereClause(whereClause), 
                UserInfo[].class);
    }
    
    public static UserInfo getUserByUsername(String username) {
        return findItemByProperty(UserInfo.class, "username", username);
    }
    
    public static String getUsernameById(long uid) {
        DAOClause selectionClause = DAOClause.create(new DAOParam("username"));
        DAOExpression whereClause = DAOExpression.create("uid").equal(uid);
        
        UserInfo user = getDAO().find(DAOQueryBuilder.create(UserInfo.class)
                .setSelectionClause(selectionClause)
                .setWhereClause(whereClause), 
                UserInfo.class);
        
        return user != null ? user.username : null;
    }
}