package com.project.server.script;

import java.net.HttpURLConnection;

import com.project.app.servlet.util.EntityUtil;
import com.project.server.ServerConfig;
import com.project.server.script.http.FileContent;

import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.ByteArrayEntity;
import engine.java.http.HttpResponse;

/**
 * 网络接口测试脚本
 * 
 * @author Daimon
 */
public class HttpScript {
    
    public static void main(String[] args) {
        debug("navigation", "获取导航配置");
        debug("login", "用户登录");
        debug("register", "用户注册");
        debug("get_user_info", "获取个人信息");
        debug("edit_user_info", "修改个人信息");
        debug("query_friend_list", "查询好友列表");
        debug("search_contact", "搜索联系人");
        debug("add_friend", "添加删除好友");
    }
    
    private static String debug(String action, String name) {
        try {
        	String request = new String(FileContent.read(action), "UTF-8");
            byte[] data = EntityUtil.toByteArray(request);
            
            HttpResponse response = new HttpConnector(ServerConfig.APP_URL, new ByteArrayEntity(data))
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}