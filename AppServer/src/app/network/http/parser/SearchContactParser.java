package app.network.http.parser;

import app.bean.User;
import app.servlet.util.RequestDispatcher.TokenParser;
import app.storage.dao.UserDAO;
import app.storage.db.UserInfo;
import engine.java.dao.Page;
import engine.java.util.string.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

public class SearchContactParser extends TokenParser {
    
    private static final int PAGE_SIZE = 20;
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        String key = json.optString("key");
        String range = json.optString("range");
        if (TextUtils.isEmpty(key))
        {
            setRequestParamError();
            return;
        }
        
        int count = (int) UserDAO.findCountByKey(key);
        if (count == 0)
        {
            setSuccess(null);
            return;
        }
        
        Page page = null;
        if (TextUtils.isEmpty(range))
        {
            if (count > PAGE_SIZE)
            {
                page = new Page(PAGE_SIZE, count);
            }
        }
        else
        {
            String[] strs = range.split(":");
            if (strs == null || strs.length != 2)
            {
                setRequestParamError();
                return;
            }
            
            try {
                page = new Page(Integer.parseInt(strs[1]), count);
                page.setCurrentRecord(Integer.parseInt(strs[0]));
            } catch (Exception e) {
                setRequestParamError();
                return;
            }
        }
        
        JSONObject data = new JSONObject();
        data.put("count", count);
        
        Collection<UserInfo> collection = UserDAO.findByKey(key, page);
        if (collection != null && !collection.isEmpty())
        {
            JSONArray list = new JSONArray();
            for (UserInfo info : collection)
            {
                JSONObject item = new JSONObject();
                item.put("uid", info.getUid());
                item.put("nickname", info.nickname);
                item.put("avatar_url", info.avatar_url);
                list.put(item);
            }
            
            data.put("list", list);
        }
        
        setSuccess(data);
    }
}