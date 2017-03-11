package app.storage.dao;

import app.storage.DAOManager.BaseDAO;
import app.storage.dao.db.FriendReflog;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class FriendDAO extends BaseDAO {
    
    /**
     * 筛选出最新的操作记录
     * @param uid 用户ID
     * @param lastTime 上次更新时间
     */
    public static FriendReflog[] getFriends(long uid, long lastTime) {
        DAOExpression whereClause = DAOExpression.create("user_id").equal(uid);
        if (lastTime == 0)
        {
            // 全量操作记录
            whereClause = whereClause.and(DAOExpression.create("op").not().equal(2));
        }
        else
        {
            // 增量操作记录
            whereClause = whereClause.and(DAOExpression.create("time").greaterThan(lastTime));
        }
        
        // 根据时间进行排序
        DAOClause orderClause = DAOClause.create(new DAOParam("time"));
        
        return getDAO().find(DAOQueryBuilder.create(FriendReflog.class)
                .setWhereClause(whereClause)
                .setOrderClause(orderClause, true),
                FriendReflog[].class);
    }
}