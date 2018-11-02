package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.dao.util.Page;

import org.json.JSONObject;

import protocol.http.SearchContactData;
import protocol.http.SearchContactData.ContactData;

import java.util.ArrayList;
import java.util.List;

public class SearchContact extends TokenParser {
    
    private static final int PAGE_SIZE = 20;
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        // 搜索关键字
        String key = json.optString("key", null);
        // 搜索范围
        long range = json.optLong("range");
        
        if (key == null)
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
        
        SearchContactData data = new SearchContactData();
        data.count = count;
        
        Page page = null;
        if (range == 0)
        {
            if (count > PAGE_SIZE)
            {
                page = new Page(PAGE_SIZE, count);
            }
        }
        else
        {
            page = new Page((int) range, count);
            page.setCurrentRecord((int) (range >> 32));
        }
        
        List<UserInfo> list = UserDAO.findByKey(key, page);
        if (list != null && !list.isEmpty())
        {
            data.list = toProtocol(list);
        }
        
        setSuccess(data);
    }
    
    private static List<ContactData> toProtocol(List<UserInfo> infos) {
        List<ContactData> list = new ArrayList<>(infos.size());
        for (UserInfo info : infos)
        {
            ContactData item = new ContactData();
            item.account = info.username;
            item.nickname = info.nickname;
            item.avatar_url = info.getAvatarUrl();
            
            list.add(item);
        }
        
        return list;
    }
}