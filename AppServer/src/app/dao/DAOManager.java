package app.dao;

import app.db.AppUpgradeInfo;
import engine.java.dao.DAOTemplate;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
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

        public static <T> T findItemByProperty(Class<T> cls, String property, Object value) {
            DAOExpression whereClause = DAOExpression.create(property).equal(value);
            return getDAO().find(DAOQueryBuilder.create(cls)
                    .setWhereClause(whereClause), 
                    cls);
        }
    }
    
    public static class CommonDAO extends BaseDAO {
        
        public static AppUpgradeInfo getNewlyApp(int device) {
            DAOExpression whereClause = DAOExpression.create("device").equal(device);
            
            return getDAO().find(DAOQueryBuilder.create(AppUpgradeInfo.class)
                    .setWhereClause(whereClause)
                    .setOrderClause(DAOClause.create(new DAOParam("version")), true), 
                    AppUpgradeInfo.class);
        }
    }
}