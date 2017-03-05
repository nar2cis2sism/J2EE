package app.dao;

import app.bean.db.AppUpgradeInfo;
import app.dao.DAOManager.BaseDAO;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class CommonDAO extends BaseDAO {
    
    public static AppUpgradeInfo getNewlyApp(int device) {
        DAOExpression whereClause = DAOExpression.create("device").equal(device);
        
        return getDAO().find(DAOQueryBuilder.create(AppUpgradeInfo.class)
                .setWhereClause(whereClause)
                .setOrderClause(DAOClause.create(new DAOParam("version")), true), 
                AppUpgradeInfo.class);
    }
}