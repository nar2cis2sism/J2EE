package app.storage.dao;

import app.storage.DAOManager.BaseDAO;
import app.storage.dao.db.AppUpgradeInfo;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class CommonDAO extends BaseDAO {
    
    /**
     * 获取最新的App信息
     * @param device 设备类型
     */
    public static AppUpgradeInfo getLastestApp(int device) {
        DAOExpression whereClause = DAOExpression.create("device").equal(device);
        
        return getDAO().find(DAOQueryBuilder.create(AppUpgradeInfo.class)
                .setWhereClause(whereClause)
                .setOrderClause(DAOClause.create(new DAOParam("version")), true), 
                AppUpgradeInfo.class);
    }
}