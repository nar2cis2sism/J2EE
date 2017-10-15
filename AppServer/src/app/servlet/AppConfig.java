package app.servlet;

/**
 * 后台配置
 */
public interface AppConfig {

    /** 测试用途，发布版本时设为false **/
    public static final boolean IS_TESTING = true;
    /** 服务器IP地址 **/
    public static final String SERVER_IP        = "127.0.0.1";
    /** 端口号 **/
    public static final int SERVER_PORT         = 8080;
    
    
    
    /** 服务器URL链接 **/
    public static final String SERVER_URL = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    /** App后台URL链接 **/
    public static final String APP_URL = SERVER_URL + "app";
    /** 文件上传URL链接 **/
    public static final String UPLOAD_URL = SERVER_URL + "upload";
}
