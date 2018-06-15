package com.project.app.action.http;

import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.app.util.TokenManager;
import com.project.server.storage.dao.FriendDAO;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.util.common.TextUtils;

import org.json.JSONObject;

public class Login extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        String username = json.optString("username", null);
        String password = json.optString("password", null);
        String deviceID = json.optString("deviceID", null);
        
        if (username == null || password == null || deviceID == null)
        {
            setRequestParamError();
            return;
        }

        UserInfo item = UserDAO.getUserByUsername(username);
        if (item == null)
        {
            // 用户不存在
            setFailure(404);
            return;
        }
        
        if (!TextUtils.equals(item.password, password))
        {
            // 密码错误
            setFailure(401);
            return;
        }
        
        // 生成用户登录凭证
        String token = TokenManager.generateToken(item, deviceID);
        
        JSONObject data = new JSONObject();
        data.put("token", token);
        data.put("user_info_ver", item.combineVersion());
        data.put("friend_list_timestamp", FriendDAO.getLatestReflogTime(item.getUid()));
        
        setSuccess(data);
    }
}