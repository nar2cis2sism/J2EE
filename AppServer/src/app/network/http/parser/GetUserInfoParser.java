package app.network.http.parser;

import app.bean.User;
import app.servlet.util.GsonUtil;
import app.servlet.util.RequestDispatcher.TokenParser;
import engine.java.util.string.TextUtils;

import org.json.JSONObject;

public class GetUserInfoParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        String user_info_ver = json.optString("user_info_ver", null);
        String version = user.info.getVersion();
        
        if (version != null
        && (TextUtils.isEmpty(user_info_ver) || !user_info_ver.equals(version)))
        {
            // 用户资料有更新
            setSuccess(new JSONObject(GsonUtil.toJson(user.info.toProtocol())));
        }
        else
        {
            setSuccess(null);
        }
    }
}