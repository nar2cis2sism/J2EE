package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.util.Page;
import engine.java.util.common.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class SearchContact extends TokenParser {
    
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
            if (strs.length != 2)
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
        
        List<UserInfo> list = UserDAO.findByKey(key, page);
        if (list != null && !list.isEmpty())
        {
            JSONArray array = new JSONArray();
            for (UserInfo info : list)
            {
                JSONObject item = new JSONObject();
                item.put("account", info.username);
                item.put("nickname", info.nickname);
                item.put("avatar_url", info.getAvatarUrl());
                array.put(item);
            }
            
            data.put("list", array);
        }
        
        setSuccess(data);
    }
}