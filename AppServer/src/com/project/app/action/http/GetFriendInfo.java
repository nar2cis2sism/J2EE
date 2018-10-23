package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import org.json.JSONObject;

import protocol.http.FriendData;
import protocol.http.FriendData.FriendInfo;

public class GetFriendInfo extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        // 好友账号
        String account = json.optString("account", null);
        // 好友资料版本
        long version = json.optLong("version");
        
        if (account == null)
        {
            setRequestParamError();
            return;
        }
        
        UserInfo friend = UserDAO.getUserByUsername(account);
        if (friend == null)
        {
            setFailure(404);
            return;
        }
        
        long latestVersion = friend.combineVersion();
        if (latestVersion == version)
        {
            setSuccess(null);
            return;
        }
        
        FriendData data = new FriendData();
        data.version = latestVersion;
        
        if ((int) version != friend.version)
        {
            // 好友信息有更新
            friend.toProtocol(data.info = new FriendInfo());
        }
        
        setSuccess(data);
    }
}