package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;

import org.json.JSONObject;

public class GetUserInfo extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        // 用户信息版本
        int version = json.optInt("version");
        
        if (user.info.version == version)
        {
            setSuccess(null);
        }
        else
        {
            // 用户信息有更新
            setSuccess(user.info.toProtocol());
        }
    }
}