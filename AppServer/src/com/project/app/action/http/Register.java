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
        
        if (username == null || password == null)
        {
            setRequestParamError();
            return;
        }

        UserInfo item = UserDAO.getUserByUsername(username);
        if (item != null)
        {
            // 用户已存在
            setFailure(415);
            return;
        }
        
        item = new UserInfo();
        item.username = username;
        item.password = password;
        item.register_time = System.currentTimeMillis();
        if (DAOManager.getDAO().save(item))
        {
            setSuccess(null);
        }
        else
        {
            throw new Exception("用户注册");
        }
    }
}