package app.network.http.parser;

import app.bean.User;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.storage.dao.FriendDAO;

import org.json.JSONObject;

public class AddFriendParser extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long uid = json.optLong("uid", -1);
        int op = json.optInt("op", -1);
        if (uid == -1 || op == -1)
        {
            setRequestParamError();
            return;
        }
        
        long timestamp = FriendDAO.addFriend(user.info.getUid(), uid, op == 1);
        if (timestamp == -1)
        {
            throw new Exception("添加删除好友");
        }

        JSONObject data = new JSONObject();
        data.put("timestamp", timestamp);
        setSuccess(data);
    }
}