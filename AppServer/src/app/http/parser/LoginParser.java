package app.http.parser;

import app.bean.db.UserInfo;
import app.dao.UserDAO;
import app.servlet.util.RequestDispatcher.AppParser;
import app.servlet.util.TokenManager;

import org.json.JSONObject;

public class LoginParser extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        String username = json.optString("username", null);
        String password = json.optString("password", null);
        String deviceID = json.optString("deviceID", null);
        
        if (username == null || password == null || deviceID == null)
        {
            setRequestParamError();
            return;
        }

        UserInfo item = UserDAO.getUserByUsername(username);
        if (item == null)
        {
            // 用户不存在
            setFailure(404);
            return;
        }
        
        if (!item.password.equals(password))
        {
            // 密码错误
            setFailure(401);
            return;
        }
        
        // 生成用户登录凭证
        String token = TokenManager.generateToken(item, deviceID);
        long uid = item.getUid();
        String user_info_ver = item.getVersion();
        
        JSONObject data = new JSONObject();
        data.put("token", token);
        data.put("uid", uid);
        data.put("user_info_ver", user_info_ver);
        
        setSuccess(data);
    }
}