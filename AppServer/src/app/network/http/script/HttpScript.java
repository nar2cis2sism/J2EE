package app.network.http.script;

import app.servlet.AppConfig;
import app.util.EntityUtil;
import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.ByteArrayEntity;
import engine.java.http.HttpResponse;
import engine.java.util.io.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

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
            byte[] data = EntityUtil.toByteArray(new String(getFileContent(action), "UTF-8"));
            
            HttpResponse response = new HttpConnector(AppConfig.APP_URL, new ByteArrayEntity(data))
            .setName(name)
            .connect();

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