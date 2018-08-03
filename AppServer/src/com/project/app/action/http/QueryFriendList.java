package com.project.app.action.http;

import com.project.app.AppConfig;
import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.DAOManager;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;
import com.project.util.GsonUtil;

import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.util.Page;

import org.json.JSONArray;
import org.json.JSONObject;

import protocol.http.FriendSync;

import java.util.ArrayList;
import java.util.List;

public class QueryFriendList extends TokenParser {
    
    private static final int SYNC_COUNT = 200;
    
    private int sync_type;
    private int sync_status;
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long timestamp = json.optLong("timestamp");
        
        List<FriendReflog> logs = getLatestReflogs(user.info.getUid(), timestamp);
        if (logs == null || logs.isEmpty())
        {
            setSuccess(null);
        }
        else
        {
            JSONObject data = new JSONObject();
            data.put("timestamp", logs.get(logs.size() - 1).time);
            data.put("sync_type", sync_type);
            data.put("sync_status", sync_status);
            data.put("list", new JSONArray(GsonUtil.toJson(toProtocol(logs))));
            setSuccess(data);
        }
    }
    
    /**
     * 筛选出最新的操作记录
     * 
     * @param uid 用户ID
     * @param timestamp 上次更新的时间戳
     */
    private List<FriendReflog> getLatestReflogs(long uid, long timestamp) {
        DAOExpression where = DAOExpression.create("user_id").eq(uid);
        if (timestamp == 0)
        {
            // 全量操作记录
            where = where.and("action").not().eq(1);
        }
        else
        {
            // 增量操作记录
            where = where.and("time").greaterThan(timestamp);
            sync_type = 1;
        }
        
        DAOQueryBuilder<FriendReflog> builder = DAOManager.getDAO().find(FriendReflog.class).where(where).orderBy("time");
        if (builder.getCount() > SYNC_COUNT)
        {
            // 分批进行处理
            builder.usePage(new Page(SYNC_COUNT, Integer.MAX_VALUE));
            sync_status = 1;
        }
        
        return builder.getAll();
    }
    
    private static List<FriendSync> toProtocol(List<FriendReflog> logs) {
        List<FriendSync> list = new ArrayList<FriendSync>(logs.size());
        for (FriendReflog log : logs)
        {
            UserInfo user = UserDAO.getUserById(log.friend_id);
            if (user == null)
            {
                // 用户不存在
                continue;
            }
            
            FriendSync item = new FriendSync();
            item.account = user.username;
            if ((item.action = log.action) == 1)
            {
                // 删除好友
            }
            else
            {
                item.nickname = user.nickname;
                item.signature = user.signature;
                item.avatar_url = AppConfig.getAvatarFilePath(user.getUid());
            }
            
            list.add(item);
        }
        
        return list;
    }
}