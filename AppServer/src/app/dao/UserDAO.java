package app.dao;

import static app.dao.DAOManager.getDAO;
import app.dao.DAOManager.BaseDAO;
import app.db.UserInfo;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class UserDAO extends BaseDAO {
    
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