package app.http.script;

import app.servlet.AppServlet;
import engine.java.http.HttpConnector;
import engine.java.http.HttpResponse;
import engine.java.util.io.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * 网络接口测试脚本
 * @author Daimon
 */
public class HttpScript {
    
    public static void main(String[] args) {
        navigation();
        login();
        getUserInfo();
    }
    
    private static void navigation() {
        debug("navigation", "获取导航");
    }
    
    private static void login() {
        debug("login", "登录");
    }
    
    private static void getUserInfo() {
        debug("get_user_info", "获取个人信息");
    }
    
    private static String debug(String action, String remark) {
        try {
            HttpResponse response = new HttpConnector(AppServlet.APP_URL, getFileContent(action))
            .setRemark(remark)
            .connect();

            int statusCode = response.getStatusCode();
            if (statusCode >= HttpURLConnection.HTTP_OK
            &&  statusCode <  HttpURLConnection.HTTP_MULT_CHOICE)
            {
                // Success
                String msg = AppServlet.read(response.getContent());
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
    
    private static byte[] getFileContent(String fileName) throws IOException {
        InputStream is = null;
        try {
            is = HttpScript.class.getResourceAsStream(fileName);
            if (is == null)
            {
                throw new IOException("No resource:" + fileName);
            }
            
            return IOUtil.readStream(is);
        } finally {
            if (is != null)
                is.close();
        }
    }
}