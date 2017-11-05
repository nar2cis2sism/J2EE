package com.project.server.storage.dao;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.AppUpgradeInfo;

import engine.java.dao.DAOTemplate.DAOExpression;

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