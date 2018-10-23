package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.app.util.TokenManager;

import org.json.JSONObject;

public class Logout extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        user.setSocketConnection(null);
        TokenManager.invalidate(user.token);
        
        setSuccess(null);
    }
}