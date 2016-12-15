package app.dao;

import engine.java.dao.DAOTemplate;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;
import engine.java.db.DatabaseDriver;
import engine.java.util.Singleton;

public class DAOManager {
    
    private static final Singleton<DAOManager> instance
    = new Singleton<DAOManager>() {
        
        @Override
        protected DAOManager create() {
            return new DAOManager();
        }
    };
    
    public static final DAOTemplate getDAO() {
        return instance.get().dao;
    }

    /******************************* 华丽丽的分割线 *******************************/
    
    private static final String HOST = "localhost";
    private static final String DATABASE = "app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    final DAOTemplate dao;
    
    DAOManager() {
        dao = new DAOTemplate(DatabaseDriver.getMysqlDriver().createConnection(
                HOST, DATABASE, USERNAME, PASSWORD));
    }
    
    public static class BaseDAO {
        
        public static DAOTemplate getDAO() {
            return DAOManager.getDAO();
        }

        public static <T> T findItemByProperty(Class<T> cls, String property, Object value) {
            DAOExpression whereClause = DAOExpression.create(property).equal(value);
            return getDAO().find(DAOQueryBuilder.create(cls)
                    .setWhereClause(whereClause), 
                    cls);
        }
    }
}