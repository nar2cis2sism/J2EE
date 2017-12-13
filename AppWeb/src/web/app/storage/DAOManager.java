package web.app.storage;

import engine.java.dao.DAOTemplate;
import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.db.DatabaseDriver;
import engine.java.util.extra.Singleton;

public final class DAOManager {
    
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
    private static final String DATABASE = "testweb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    private final DAOTemplate dao;
    
    private DAOManager() {
        dao = new DAOTemplate(DatabaseDriver.getMysqlDriver().createConnection(
                HOST, DATABASE, USERNAME, PASSWORD));
    }
    
    public static class BaseDAO {
        
        protected static final DAOTemplate dao = getDAO();
        
        public static <T> T findItemByProperty(Class<T> cls, String property, Object value) {
            return dao.find(cls).where(DAOExpression.create(property).equal(value)).get();
        }
    }
}