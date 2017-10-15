package app.network.http.parser;

import app.bean.User;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.storage.DAOManager;
import app.storage.db.UserInfo;

import org.json.JSONObject;

public class EditUserInfoParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long version = json.optLong("version");
        long latestVersion = user.info.version;
        
        if (version != latestVersion)
        {
            setFailure(416);
            return;
        }
        
        String nickname = json.optString("nickname");
        int gender = json.optInt("gender", -1);
        long birthday = json.optLong("birthday");
        String city = json.optString("city");
        String signature = json.optString("signature");
        String profile = json.optString("profile");

        UserInfo info = user.info;
        if (nickname != null) info.nickname = nickname;
        if (gender != -1) info.isFemale = gender == 1;
        if (birthday != 0) info.birthday = birthday;
        if (city != null) info.city = city;
        if (signature != null) info.signature = signature;
        if (profile != null) info.profile = profile;
        info.version = System.currentTimeMillis();
        
        if (DAOManager.getDAO().update(info))
        {
            JSONObject data = new JSONObject();
            data.put("version", info.version);
            setSuccess(data);
        }
        else
        {
            throw new Exception("修改个人信息");
        }
    }
}