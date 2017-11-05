package com.project.app.action.http;

import org.json.JSONObject;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.util.GsonUtil;

public class GetUserInfo extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long version = json.optLong("version");
        long latestVersion = user.info.version;
        
        if (latestVersion == 0 || version == latestVersion)
        {
            setSuccess(null);
        }
        else
        {
            // 用户资料有更新
            setSuccess(new JSONObject(GsonUtil.toJson(user.info.toProtocol())));
        }
    }
}