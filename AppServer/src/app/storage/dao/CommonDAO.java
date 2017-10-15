package app.storage.dao;

import engine.java.dao.DAOTemplate.DAOExpression;
import app.storage.DAOManager.BaseDAO;
import app.storage.db.AppUpgradeInfo;

public class CommonDAO extends BaseDAO {
    
    /**
     * 获取最新的App信息
     * 
     * @param device 设备类型
     */
    public static AppUpgradeInfo getLastestApp(int device) {
        return dao.find(AppUpgradeInfo.class)
                .where(DAOExpression.create("device").equal(device))
                .orderDesc("version")
                .get();
    }
}