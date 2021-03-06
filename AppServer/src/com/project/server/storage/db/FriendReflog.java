package com.project.server.storage.db;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

/**
 * 好友操作日志
 */
@DAOTable
public class FriendReflog {

    @DAOPrimaryKey(autoincrement=true)
    private long id;
    
    @DAOProperty
    public long user_id;                    // 用户ID

    @DAOProperty
    public long friend_id;                  // 好友ID
    
    /**
     * 0：加为好友
     * 1：删除好友
     */
    @DAOProperty
    public int op;                          // 操作指令

    @DAOProperty
    public long time;                       // 记录时间
}