package com.project.app.action.http;

import org.json.JSONObject;

import protocol.http.AddFriendData;
import protocol.http.FriendListData.FriendListItem.FriendInfo;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.dao.FriendDAO;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

public class AddFriend extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        // 联系人账号
        String account = json.optString("account", null);
        // 操作指令
        int op = json.optInt("op", -1);
        
        if (account == null || op == -1)
        {
            setRequestParamError();
            return;
        }
        
        UserInfo contact = UserDAO.getUserByUsername(account);
        if (contact == null)
        {
            setFailure(404);
            return;
        }
        
        long timestamp = FriendDAO.addFriend(user.info, contact, op);
        if (timestamp == -1)
        {
            throw new Exception("添加删除好友");
        }
        
        AddFriendData data = new AddFriendData();
        data.timestamp = timestamp;
        
        if (op == 0)
        {
            contact.toProtocol(data.info = new FriendInfo());
        }
        
        setSuccess(data);
    }
}