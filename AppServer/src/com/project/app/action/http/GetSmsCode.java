package com.project.app.action.http;

import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.server.storage.dao.CommonDAO;

import org.json.JSONObject;

public class GetSmsCode extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        // 手机号
        String mobile_phone = json.optString("mobile_phone", null);
        // 号码验重
        int duplication = json.optInt("duplication", -1);
        
        if (mobile_phone == null || duplication == -1)
        {
            setRequestParamError();
            return;
        }
        
        if (duplication == 1 && CommonDAO.isMobileExist(mobile_phone))
        {
            setFailure(415);
            return;
        }
        
        // TODO: 下发手机验证码
        setSuccess(null);
    }
}