package app.network.http.parser;

import app.bean.User;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.storage.dao.FriendDAO;
import app.storage.dao.UserDAO;
import app.storage.db.FriendReflog;
import app.storage.db.UserInfo;
import app.util.GsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import protocol.java.json.FriendOp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueryFriendListParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long timestamp = json.optLong("timestamp");
        
        Collection<FriendReflog> collection = FriendDAO.getLatestReflogs(user.info.getUid(), timestamp);
        if (collection == null || collection.isEmpty())
        {
            setSuccess(null);
        }
        else
        {
            FriendReflog[] friends = collection.toArray(new FriendReflog[collection.size()]);
            
            JSONObject data = new JSONObject();
            data.put("timestamp", friends[0].time);
            data.put("sync_type", timestamp == 0 ? 0 : 1);
            data.put("sync_status", 0);
            data.put("list", new JSONArray(GsonUtil.toJson(toProtocol(friends))));
            setSuccess(data);
        }
    }
    
    private List<FriendOp> toProtocol(FriendReflog[] friends) {
        List<FriendOp> list = new ArrayList<>(friends.length);
        for (FriendReflog friend : friends)
        {
            FriendOp op = new FriendOp();
            op.uid = friend.friend_id;
            if ((op.op = friend.op) == 2)
            {
                // 删除好友
            }
            else
            {

                UserInfo userInfo = UserDAO.getUserById(op.uid);
                if (userInfo == null)
                {
                    // 用户不存在
                    continue;
                }
                
                op.remark = friend.remark;
                op.nickname = userInfo.nickname;
                op.signature = userInfo.signature;
                op.avatar_url = userInfo.avatar_url;
                op.friend_info_ver = userInfo.getVersion();
            }
            
            list.add(op);
        }
        
        return list;
    }
}