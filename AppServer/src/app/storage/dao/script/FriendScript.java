package app.storage.dao.script;

import static app.storage.DAOManager.getDAO;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;
import app.storage.dao.db.FriendReflog;

public class FriendScript {
    
    /**
     * 添加好友
     * @param uid 用户ID
     * @param friend_id 好友ID
     */
    public static void addFriend(long uid, long friend_id) {
        DAOExpression whereClause = DAOExpression.create("user_id").equal(uid)
                               .and(DAOExpression.create("friend_id").equal(friend_id));
        FriendReflog reflog = getDAO().find(DAOQueryBuilder.create(FriendReflog.class)
                .setWhereClause(whereClause), 
                FriendReflog.class);
        if (reflog != null)
        {
            System.out.println("好友已存在");
            return;
        }
        
        reflog = new FriendReflog();
        reflog.user_id = uid;
        reflog.friend_id = friend_id;
        reflog.op = 1;
        reflog.time = System.currentTimeMillis();
        if (getDAO().save(reflog))
        {
            System.out.println("添加好友成功");
        }
        else
        {
            System.out.println("添加好友失败");
        }
    }

    /**
     * 删除好友
     * @param uid 用户ID
     * @param friend_id 好友ID
     */
    public static void deleteFriend(long uid, long friend_id) {
        DAOExpression whereClause = DAOExpression.create("user_id").equal(uid)
                               .and(DAOExpression.create("friend_id").equal(friend_id));
        FriendReflog reflog = getDAO().find(DAOQueryBuilder.create(FriendReflog.class)
                .setWhereClause(whereClause), 
                FriendReflog.class);
        if (reflog == null)
        {
            System.out.println("好友不存在");
            return;
        }
        
        reflog.op = 2;
        reflog.time = System.currentTimeMillis();
        if (getDAO().edit(DAOSQLBuilder.create(FriendReflog.class).setWhereClause(whereClause), reflog, "op", "time"))
        {
            System.out.println("删除好友成功");
        }
        else
        {
            System.out.println("删除好友失败");
        }
    }
}