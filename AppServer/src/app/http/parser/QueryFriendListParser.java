package app.http.parser;

import app.bean.User;
import app.bean.db.FriendReflog;
import app.bean.db.UserInfo;
import app.dao.FriendDAO;
import app.dao.UserDAO;
import app.servlet.util.GsonUtil;
import app.servlet.util.RequestDispatcher.TokenParser;

import org.apache.commons.lang.ArrayUtils;
import org.json.JSONObject;

import protocol.java.json.FriendInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QueryFriendListParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long timestamp = json.optLong("timestamp");
        
        FriendReflog[] logs = FriendDAO.getNewlyReflogs(user.info.getUid(), timestamp);
        if (logs == null || logs.length == 0)
        {
            // 无更新
            setSuccess(null);
        }
        else
        {
            // 好友列表有更新
            JSONObject data = new JSONObject();
            data.put("timestamp", logs[0].time);
            data.put("list", GsonUtil.toJson(toFriendList(logs)));
            setSuccess(data);
        }
    }
    
    private List<FriendInfo> toFriendList(FriendReflog[] logs) {
        List<FriendInfo> list = new ArrayList<FriendInfo>(logs.length);
        
        List<Long> addFriendList = new LinkedList<Long>();
        List<Long> deleteFriendList = new LinkedList<Long>();
        for (FriendReflog log : logs)
        {
            if (log.isFriend)
            {
                addFriendList.add(log.friend_id);
            }
            else
            {
                // 删除好友
                deleteFriendList.add(log.friend_id);
            }
        }
        
        if (!addFriendList.isEmpty())
        {
            long[] uid = ArrayUtils.toPrimitive(addFriendList.toArray(new Long[addFriendList.size()]));
            UserInfo[] friends = UserDAO.getUserByIds(uid);
            for (UserInfo friend : friends)
            {
                FriendInfo info = new FriendInfo();
                info.friend_id = friend.getUid();
                info.nickname = friend.nickname;
                info.avatar_url = friend.avatar_url;
                info.friend_info_ver = friend.getVersion();
                list.add(info);
            }
        }
        
        if (!deleteFriendList.isEmpty())
        {
            for (long friend_id : deleteFriendList)
            {
                FriendInfo info = new FriendInfo();
                info.friend_id = friend_id;
                info.del = 1;
                list.add(info);
            }
        }
        
        return list;
    }
}