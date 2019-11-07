package com.project.app;

import com.project.server.ServerConfig;

import java.io.File;

public class AppConfig implements ServerConfig {
    
    /** 服务器URL链接 **/
    public static final String SERVER_URL       = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    /** App后台URL链接 **/
    public static final String APP_URL          = SERVER_URL + "app";
    /** 文件上传URL链接 **/
    public static final String UPLOAD_URL       = SERVER_URL + "upload";
    /** 日志上传URL链接 **/
    public static final String LOG_URL          = SERVER_URL + "log";

    /** APP应用数据存放根目录 **/
    private static final String DATA_PATH       = "data/";
    
    /**
     * @param device 客户端类型
     * @param version 客户端版本号
     * @return 应用程序安装包存储目录
     */
    public static File getAppDir(int device, String version) {
        return new File(DATA_PATH + "app/" + device, version);
    }

    /**
     * @param uid 用户唯一标识
     * @return 用户头像存储文件路径
     */
    public static String getAvatarFilePath(long uid) {
        return DATA_PATH + "avatar/" + uid;
    }

    /**
     * @param uid 用户唯一标识
     * @return 实名认证图片存储目录路径
     */
    public static String getAuthenticationDirPath(long uid) {
        return DATA_PATH + "authentication/" + uid;
    }

    /**
     * @return 日志上传存储目录路径
     */
    public static String getLogDirPath() {
        return DATA_PATH + "log";
    }
}