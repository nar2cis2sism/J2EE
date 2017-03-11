package app.network.http.parser;

import app.bean.User;
import app.servlet.util.GsonUtil;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.storage.dao.FriendDAO;
import app.storage.dao.UserDAO;
import app.storage.dao.db.FriendReflog;
import app.storage.dao.db.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import protocol.java.json.FriendInfo;

import java.util.ArrayList;

public class QueryFriendListParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long timestamp = json.optLong("timestamp");
        
        FriendReflog[] friends = FriendDAO.getFriends(user.info.getUid(), timestamp);
        if (friends == null || friends.length == 0)
        {
            setSuccess(null);
        }
        else
        {
            JSONObject data = new JSONObject();
            data.put("timestamp", friends[0].time);
            data.put("sync_type", timestamp == 0 ? 0 : 1);
            data.put("sync_status", 0);
            data.put("list", new JSONArray(GsonUtil.toJson(toFriendList(friends))));
            setSuccess(data);
        }
    }
    
    private ArrayList<FriendInfo> toFriendList(FriendReflog[] friends) {
        ArrayList<FriendInfo> list = new ArrayList<>(friends.length);
        for (FriendReflog friend : friends)
        {
            FriendInfo info = new FriendInfo();
            info.friend_id = friend.friend_id;
            info.op = friend.op;
            
            if (friend.op == 2)
            {
                // 删除好友
            }
            else
            {
                UserInfo userInfo = UserDAO.getUserById(info.friend_id);
                if (userInfo == null)
                {
                    // 用户不存在
                    continue;
                }
                
                info.remark = friend.remark;
                info.nickname = userInfo.nickname;
                info.signature = userInfo.signature;
                info.avatar_url = userInfo.avatar_url;
                info.friend_info_ver = userInfo.getVersion();
            }
            
            list.add(info);
        }
        
        return list;
    }
}