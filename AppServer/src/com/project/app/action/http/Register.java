package com.project.app.action.http;

import org.json.JSONObject;

import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.server.storage.DAOManager;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

public class Register extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        // 用户名
        String username = json.optString("username", null);
        // 密码
        String password = json.optString("password", null);
        // 注册方式
        int type = json.optInt("type", -1);
        // 手机号码注册时为短信验证码
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
        if (type == 0)
        {
            info.mobile_phone = username;
            info.version++;
        }
        
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