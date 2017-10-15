package app.network.http.parser;

import app.servlet.util.RequestDispatcher.AppParser;
import app.servlet.util.TokenManager;
import app.storage.dao.FriendDAO;
import app.storage.dao.UserDAO;
import app.storage.db.UserInfo;

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
        
        JSONObject data = new JSONObject();
        data.put("token", token);
        data.put("uid", item.getUid());
        data.put("user_info_ver", item.getVersion());
        data.put("friend_list_timestamp", FriendDAO.getLatestReflogTime(item.getUid()));
        
        setSuccess(data);
    }
}