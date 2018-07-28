package com.project.app.action.http;

import com.project.app.bean.User;
import com.project.app.servlet.util.RequestDispatcher.TokenParser;
import com.project.server.storage.DAOManager;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.util.extra.ChangeStatus;

import org.json.JSONObject;

public class EditUserInfo extends TokenParser {
    
    @Override
    public void parse(JSONObject json, User user) throws Exception {
        long version = json.optLong("version", -1);
        if (version == -1)
        {
            setRequestParamError();
            return;
        }
        
        if (version != user.info.version)
        {
            setFailure(416);
            return;
        }
        
        String nickname = json.optString("nickname", null);
        int gender = json.optInt("gender", -1);
        long birthday = json.optLong("birthday", -1);
        String region = json.optString("region", null);
        String signature = json.optString("signature", null);

        UserInfo info = user.info;
        ChangeStatus status = new ChangeStatus();
        
        if (nickname != null)
        {
            info.nickname = nickname;
            status.setChanged("nickname", true);
        }
        
        if (gender != -1)
        {
            info.isFemale = gender == 1;
            status.setChanged("isFemale", true);
        }
        
        if (birthday != -1)
        {
            info.birthday = birthday;
            status.setChanged("birthday", true);
        }
        
        if (region != null)
        {
            info.region = region;
            status.setChanged("region", true);
        }
        
        if (signature != null)
        {
            info.signature = signature;
            status.setChanged("signature", true);
        }
        
        info.version = System.currentTimeMillis();
        status.setChanged("version", true);
        
        if (DAOManager.getDAO().update(info, status.getChangedProperties()))
        {
            JSONObject data = new JSONObject();
            data.put("version", info.version);
            setSuccess(data);
        }
        else
        {
            // 恢复数据
            user.info = UserDAO.getUserById(info.getUid());
            throw new Exception("修改个人信息");
        }
    }
}