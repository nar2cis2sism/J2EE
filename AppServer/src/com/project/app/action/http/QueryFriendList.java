package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.DAOManager;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.FriendReflog;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.util.Page;

import org.json.JSONObject;

import protocol.http.FriendListData;
import protocol.http.FriendListData.FriendListItem;
import protocol.http.FriendListData.FriendListItem.FriendInfo;

import java.util.ArrayList;
import java.util.List;

public class QueryFriendList extends TokenParser {
    
    private static final int SYNC_COUNT = 200;
    
    private int sync_type;
    private int sync_status;
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        // 上次更新的时间戳
        long timestamp = json.optLong("timestamp");
        
        List<FriendReflog> logs = getLatestReflogs(user.info.getUid(), timestamp);
        if (logs == null || logs.isEmpty())
        {
            setSuccess(null);
        }
        else
        {
            FriendListData data = new FriendListData();
            data.timestamp = logs.get(logs.size() - 1).time;
            data.sync_type = sync_type;
            data.sync_status = sync_status;
            data.list = toProtocol(logs);
            
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
            where = where.and("op").not().eq(1);
        }
        else
        {
            // 增量操作记录
            where = where.and("time").greaterThan(timestamp);
            sync_type = 1;
        }
        
        DAOQueryBuilder<FriendReflog> builder = DAOManager.getDAO().find(FriendReflog.class)
        .where(where)
        .orderBy("time");
        if (builder.getCount() > SYNC_COUNT)
        {
            // 分批进行处理
            builder.usePage(new Page(SYNC_COUNT, SYNC_COUNT));
            sync_status = 1;
        }
        
        return builder.getAll();
    }
    
    private static List<FriendListItem> toProtocol(List<FriendReflog> logs) {
        ArrayList<FriendListItem> list = new ArrayList<FriendListItem>(logs.size());
        for (FriendReflog log : logs)
        {
            UserInfo user = UserDAO.getUserById(log.friend_id);
            if (user == null)
            {
                // 用户不存在
                continue;
            }
            
            FriendListItem item = new FriendListItem();
            item.account = user.username;
            if ((item.op = log.op) == 1)
            {
                // 删除好友
            }
            else
            {
                user.toProtocol(item.info = new FriendInfo());
            }
            
            list.add(item);
        }
        
        return list;
    }
}