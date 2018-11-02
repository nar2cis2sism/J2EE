package com.project.server;

/**
 * 后台配置
 */
public interface ServerConfig {

    /** 测试用途，发布版本时设为false **/
    boolean IS_TESTING      = true;
    
    /** 服务器IP地址 **/
    String SERVER_IP        = "192.168.1.101";
    /** 端口号 **/
    int SERVER_PORT         = 8080;
}