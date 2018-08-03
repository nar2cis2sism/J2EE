package com.project.app.action.http;

import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.server.storage.dao.CommonDAO;

import org.json.JSONObject;

public class GetSmsCode extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        String mobile_phone = json.optString("mobile_phone", null);
        int type = json.optInt("type", -1);
        
        if (mobile_phone == null || type == -1)
        {
            setRequestParamError();
            return;
        }
        
        if (type == 1 && CommonDAO.isMobileExist(mobile_phone))
        {
            setFailure(415);
            return;
        }
        
        // TODO: 下发手机验证码
        setSuccess(null);
    }
}