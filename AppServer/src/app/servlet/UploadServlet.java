package app.servlet;

import app.bean.User;
import app.servlet.util.TokenManager;
import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.ByteArrayEntity;
import engine.java.http.HttpResponse;
import engine.java.util.file.FileManager;
import engine.java.util.file.FileUtils;
import engine.java.util.string.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传处理器
 */
public class UploadServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();
        url = url.substring(url.lastIndexOf("/"));
        if (url.startsWith("/fileUpload"))
        {
            // 内部上传
            String path = req.getParameter("path");
            if (TextUtils.isEmpty(path)) return;
            
            File file = new File(req.getServletContext().getRealPath(path));
            FileManager.createFileIfNecessary(file);
            if (!FileUtils.copyToFile(req.getInputStream(), file))
            {
                throw new IOException("文件上传失败:" + file);
            }
        }
        else
        {
            String token = req.getHeader("token");
            User user = TokenManager.authenticate(token);
            if (user == null)
            {
                resp.setContentType("text;charset=UTF-8");
                resp.getOutputStream().write("Token认证失败".getBytes("UTF-8"));
                resp.getOutputStream().flush();
            }
            else
            {
                String action = req.getParameter("action");
                if ("avatar".equals(action))
                {
                    // 上传头像
                }
            }
        }
    }
    
    /**
     * 上传文件
     * 
     * @param srcFile 源文件
     * @param desPath 目标文件相对服务器根目录路径
     * @return 是否成功
     */
    public static boolean uploadFile(File srcFile, String desPath) {
        try {
            HttpResponse response = new HttpConnector(AppConfig.SERVER_URL + "fileUpload?path=" + desPath, 
                    new ByteArrayEntity(FileManager.readFile(srcFile)))
            .setName("文件上传")
            .connect();

            return response.getStatusCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}