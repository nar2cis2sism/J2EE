package app.storage.dao;

import app.storage.DAOManager.BaseDAO;
import app.storage.db.FriendReflog;
import engine.java.dao.DAOTemplate.DAOExpression;

import java.util.Collection;

public class FriendDAO extends BaseDAO {
    
    /**
     * 获取最新的操作记录时间
     * 
     * @param uid 用户ID
     */
    public static Long getLatestReflogTime(long uid) {
        FriendReflog log = dao.find(FriendReflog.class)
                .select("time")
                .where(DAOExpression.create("user_id").equal(uid))
                .orderDesc("time")
                .get();
        if (log != null)
        {
            return log.time;
        }
        
        return null;
    }
    
    /**
     * 刷选出最新的操作记录
     * 
     * @param uid 用户ID
     * @param timestamp 上次更新的时间戳
     */
    public static Collection<FriendReflog> getLatestReflogs(long uid, long timestamp) {
        DAOExpression where = DAOExpression.create("user_id").equal(uid);
        if (timestamp == 0)
        {
            // 全量操作记录
            where = where.and("op").not().equal(2);
        }
        else
        {
            // 增量操作记录
            where = where.and("time").greaterThan(timestamp);
        }
        
        return dao.find(FriendReflog.class).where(where).orderDesc("time").getAll();
    }
    
    /**
     * @param delete True:删除好友 False:添加好友
     * @return -1:操作失败 or 操作记录变更时间戳
     */
    public static long addFriend(long user_id, long friend_id, boolean delete) {
        boolean success;
        FriendReflog log = dao.find(FriendReflog.class)
                .where(DAOExpression.create("user_id").equal(user_id)
                .and("friend_id").equal(friend_id))
                .get();
        if (log == null)
        {
            log = new FriendReflog();
            log.user_id = user_id;
            log.friend_id = friend_id;
            log.op = delete ? 2 : 1;
            log.time = System.currentTimeMillis();
            success = dao.save(log);
        }
        else
        {
            log.op = delete ? 2 : 1;
            log.time = System.currentTimeMillis();
            success = dao.update(log, "op", "time");
        }
        
        return success ? log.time : -1;
    }
}