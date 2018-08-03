package com.project.app.action.http;

import org.json.JSONObject;

import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.server.storage.DAOManager;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

public class Register extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        String username = json.optString("username", null);
        String password = json.optString("password", null);
        int type = json.optInt("type", -1);
        String passport = json.optString("passport");
        
        if (username == null || password == null || type == -1)
        {
            setRequestParamError();
            return;
        }

        UserInfo info = UserDAO.getUserByUsername(username);
        if (info != null)
        {
            // 用户已存在
            setFailure(415);
            return;
        }
        
        info = new UserInfo();
        info.username = username;
        info.password = password;
        info.register_time = System.currentTimeMillis();
        if (DAOManager.getDAO().save(info))
        {
            setSuccess(null);
        }
        else
        {
            throw new Exception("用户注册");
        }
    }
}