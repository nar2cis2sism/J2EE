package com.project.server.script;

import com.project.app.AppConfig;
import com.project.server.script.http.FileContent;

import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.ByteArrayEntity;
import engine.java.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import protocol.java.EntityUtil;

import java.net.HttpURLConnection;

/**
 * 网络接口测试脚本
 * 
 * @author Daimon
 */
public class HttpScript {
    
    private static String TOKEN;
    
    public static void main(String[] args) {
        doAction("navigation", "获取导航配置");
        login();
//        debug("register", "用户注册");
        doActionWithToken("get_user_info", "获取个人信息");
//        debug("edit_user_info", "修改个人信息");
        doActionWithToken("query_friend_list", "查询好友列表");
//        debug("search_contact", "搜索联系人");
//        debug("add_friend", "添加删除好友");
    }
    
    private static void login() {
        String login = doAction("login", "用户登录");
        if (login != null)
        {
            try {
                JSONObject json = new JSONObject(login);
                JSONObject data = json.optJSONObject("data");
                if (data != null)
                {
                    TOKEN = data.getString("token");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String doAction(String action, String name) {
        try {
            return doRequest(new String(FileContent.read(action), "UTF-8"), name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String doActionWithToken(String action, String name) {
        if (TOKEN == null) throw new NullPointerException(name);
        try {
            String request = new String(FileContent.read(action), "UTF-8");
            JSONObject json = new JSONObject(request);
            json.put("token", TOKEN);
            return doRequest(json.toString(), name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String doRequest(String request, String name) throws Exception {
        byte[] data = EntityUtil.toByteArray(request);
        
        HttpResponse response = new HttpConnector(AppConfig.APP_URL, new ByteArrayEntity(data))
        .setName(name)
        .connect();

        System.out.println(request);
        int statusCode = response.getStatusCode();
        if (statusCode >= HttpURLConnection.HTTP_OK
        &&  statusCode <  HttpURLConnection.HTTP_MULT_CHOICE)
        {
            // Success
            String msg = EntityUtil.toString(response.getContent());
            System.out.println("服务器返回--" + statusCode + ":" + msg);
            return msg;
        }
        else
        {
            System.out.println("服务器返回--" + statusCode + ":" + response.getReasonPhrase());
        }
    
        return null;
    }
}