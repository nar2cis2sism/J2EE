package com.project.app.servlet;

import com.project.app.AppConfig;
import com.project.app.bean.User;
import com.project.app.util.TokenManager;
import com.project.server.storage.DAOManager;
import com.project.server.storage.db.UserInfo;

import engine.java.http.HttpConnector;
import engine.java.http.HttpRequest.FileEntity;
import engine.java.http.HttpResponse;
import engine.java.util.StringUtil;
import engine.java.util.common.TextUtils;
import engine.java.util.file.FileManager;
import engine.java.util.file.FileUtils;
import engine.java.util.log.LogFactory.LOG;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.endsWith("/fileUpload"))
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
            String query = req.getQueryString();
            LOG.log(String.format("收到来自%s的请求:%s?%s", req.getRemoteAddr(), url, query));
            
            String token = req.getHeader("token");
            User user = TokenManager.authenticate(token);
            if (user == null)
            {
                return;
            }

//          String action = req.getParameter("action"); // 会报错
            Map<String, String> parameters = StringUtil.parseQueryParameters(query);
            String action = parameters.get("action");
            if ("avatar".equals(action))
            {
                // 上传头像
                UserInfo info = user.info;
                String avatar_url = AppConfig.getAvatarFilePath(info.getUid());
                File file = new File(req.getServletContext().getRealPath(avatar_url));
                FileManager.createFileIfNecessary(file);
                if (!FileUtils.copyToFile(req.getInputStream(), file))
                {
                    throw new IOException("上传头像失败:" + file);
                }
                
                // 更新头像版本号
                info.avatar_ver = System.currentTimeMillis();
                DAOManager.getDAO().update(info, "avatar_ver");
                
                resp.setHeader("crc", String.valueOf(info.avatar_ver));
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
                    new FileEntity(srcFile))
            .setName("文件上传")
            .connect();

            return response.getStatusCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}