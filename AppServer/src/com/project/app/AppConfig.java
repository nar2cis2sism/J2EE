package com.project.app;

/**
 * 后台配置
 */
public interface AppConfig {

    /** 测试用途，发布版本时设为false **/
    boolean IS_TESTING 		= true;
    /** 服务器IP地址 **/
    String SERVER_IP        = "127.0.0.1";
    /** 端口号 **/
    int SERVER_PORT         = 8080;
}