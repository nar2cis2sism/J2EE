package com.project.server;

import com.project.app.AppConfig;

public class ServerConfig implements AppConfig {
    
    /** 服务器URL链接 **/
    public static final String SERVER_URL = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    /** App后台URL链接 **/
    public static final String APP_URL = SERVER_URL + "app";
    /** 文件上传URL链接 **/
    public static final String UPLOAD_URL = SERVER_URL + "upload";
}