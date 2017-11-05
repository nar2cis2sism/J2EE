package com.project.server.storage.db;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

/**
 * 客户端升级信息
 */
@DAOTable
public class AppUpgradeInfo {

    @DAOPrimaryKey(autoincrement=true)
    private long id;
    
    /**
     * 0：建议升级
     * 1：强制升级
     */
    @DAOProperty
    public int type;                        // 升级类型

    @DAOProperty
    public String name;                     // 版本名称

    @DAOProperty
    public String version;                  // 客户端版本号

    @DAOProperty
    public String url;                      // 升级包下载地址

    @DAOProperty(column="description")
    public String desc;                     // 升级描述
    
    /**
     * IOS:0x01 
     * Android:0x02 
     * 其他:0x03 
     * Pad:0x04
     */
    @DAOProperty
    public int device;                      // 客户端类型（可以组合）

    /******************************* 华丽丽的分割线 *******************************/
    
    public protocol.java.json.AppUpgradeInfo toProtocol() {
        protocol.java.json.AppUpgradeInfo item = new protocol.java.json.AppUpgradeInfo();
        item.type = type;
        item.name = name;
        item.version = version;
        item.url = url;
        item.desc = desc;
        return item;
    }
}