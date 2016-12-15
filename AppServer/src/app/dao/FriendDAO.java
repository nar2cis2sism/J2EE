package app.dao;

import app.dao.DAOManager.BaseDAO;
import app.db.FriendReflog;
import engine.java.dao.DAOTemplate.DAOClause;
import engine.java.dao.DAOTemplate.DAOClause.DAOParam;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

public class FriendDAO extends BaseDAO {
    
    public static FriendReflog[] getNewlyReflogs(long uid, long lastTime) {
        // 筛选出最新的操作记录
        DAOClause selectionClause = DAOClause.create(new DAOParam("time").max()).add(new DAOParam("*"));
        // 增量操作记录
        DAOExpression whereClause = DAOExpression.create("uid").equal(uid)
                .and(DAOExpression.create("time").greaterThan(lastTime));
        if (lastTime == 0)
        {
            // 全量更新
            whereClause = whereClause.and(DAOExpression.create("isFriend").equal(true));
        }
        
        // 针对好友进行分组
        DAOClause groupClause = DAOClause.create(new DAOParam("friend_id"));
        // 根据时间进行排序
        DAOClause orderClause = DAOClause.create(new DAOParam("time"));
        
        return getDAO().find(DAOQueryBuilder.create(FriendReflog.class)
                .setSelectionClause(selectionClause)
                .setWhereClause(whereClause)
                .setGroupClause(groupClause)
                .setOrderClause(orderClause, true),
                FriendReflog[].class);
    }
}