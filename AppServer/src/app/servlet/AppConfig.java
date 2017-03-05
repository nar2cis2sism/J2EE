package app.servlet;

/**
 * 后台配置
 */
public interface AppConfig {
    
    /** 服务器IP地址 **/
    public static final String SERVER_IP        = "192.168.1.101";
    /** 端口号 **/
    public static final int SERVER_PORT         = 8080;
    
    
    
    /** 服务器URL链接 **/
    public static final String SERVER_URL = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    /** App后台URL链接 **/
    public static final String APP_URL = SERVER_URL + "app";
    /** 文件上传URL链接 **/
    public static final String FILE_UPLOAD_URL = SERVER_URL + "fileUpload?path=";
}
