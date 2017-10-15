package app.network.http.parser;

import app.bean.User;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.util.GsonUtil;

import org.json.JSONObject;

public class GetUserInfoParser extends TokenParser {
    
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