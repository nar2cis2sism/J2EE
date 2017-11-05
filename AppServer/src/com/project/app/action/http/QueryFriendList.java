package com.project.app.action.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import protocol.java.json.FriendOp;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.dao.FriendDAO;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;
import com.project.util.GsonUtil;

public class QueryFriendList extends TokenParser {
    
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
            if ((op.op = friend.op - 1) == 2)
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