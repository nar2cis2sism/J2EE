package com.project.app;

import com.project.server.ServerConfig;

public interface AppConfig extends ServerConfig {
    
    /** 服务器URL链接 **/
    String SERVER_URL           = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    /** App后台URL链接 **/
    String APP_URL              = SERVER_URL + "app";
    /** 文件上传URL链接 **/
    String UPLOAD_URL           = SERVER_URL + "upload";
}