package com.project.server.script;

import com.project.app.AppConfig;

import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.ByteArrayEntity;
import engine.java.http.HttpResponse;
import engine.java.util.StringUtil;
import engine.java.util.file.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

import protocol.util.EntityUtil;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * 网络接口测试脚本
 * 
 * @author Daimon
 */
public class HttpScript {
    
    private static boolean log = true;
    
    private static String TOKEN;
    
    public static void main(String[] args) {
        doAction("navigation");
        login();
        doAction("get_sms_code");
        doAction("register");
        doActionWithToken("get_user_info");
        doActionWithToken("edit_user_info");
        doActionWithToken("get_friend_info");
        doActionWithToken("query_friend_list");
        doActionWithToken("search_contact");
        doActionWithToken("add_friend");
        doActionWithToken("logout");
    }
    
    private static void login() {
        String login = doAction("login");
        if (login != null)
        {
            try {
                JSONObject json = new JSONObject(login);
                JSONObject data = json.getJSONObject("data");
                TOKEN = data.getString("token");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String doAction(String action) {
        try {
            return doRequest(getRequest(action));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String doActionWithToken(String action) {
        if (TOKEN == null)
        {
            log = false;
            login();
            log = true;
        }
        
        try {
            JSONObject json = new JSONObject(getRequest(action));
            json.put("token", TOKEN);
            return doRequest(json.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getRequest(String action) throws IOException {
        return StringUtil.toString(FileManager.readFile(HttpScript.class, "http/" + action));
    }

    private static String doRequest(String request) throws Exception {
        if (log) System.out.println("请求--" + request);
        byte[] data = EntityUtil.toByteArray(request);
        
        HttpResponse response = new HttpConnector(AppConfig.APP_URL, new ByteArrayEntity(data)).connect();

        int statusCode = response.getStatusCode();
        if (statusCode >= HttpURLConnection.HTTP_OK
        &&  statusCode <  HttpURLConnection.HTTP_MULT_CHOICE)
        {
            // Success
            String msg = EntityUtil.toString(response.getContent());
            if (log) System.out.println("响应--" + msg);
            return msg;
        }
        else
        {
            if (log) System.out.println("服务器返回--" + statusCode + ":" + response.getReasonPhrase());
        }
    
        return null;
    }
}