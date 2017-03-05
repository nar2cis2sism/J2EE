package app.dao.script;

import static app.dao.DAOManager.getDAO;
import app.bean.db.FriendReflog;

public class FriendScript {
    
    /**
     * 添加好友
     * @param uid 用户ID
     * @param friend_id 好友ID
     */
    public static void addFriend(long uid, long friend_id) {
        FriendReflog reflog = new FriendReflog();
        reflog.uid = uid;
        reflog.friend_id = friend_id;
        reflog.op = 1;
        reflog.time = System.currentTimeMillis();
        reflog.isFriend = true;
        getDAO().save(reflog);
    }

    /**
     * 删除好友
     * @param uid 用户ID
     * @param friend_id 好友ID
     */
    public static void deleteFriend(long uid, long friend_id) {
        FriendReflog reflog = new FriendReflog();
        reflog.uid = uid;
        reflog.friend_id = friend_id;
        reflog.op = 2;
        reflog.time = System.currentTimeMillis();
        reflog.isFriend = false;
        getDAO().save(reflog);
    }
}