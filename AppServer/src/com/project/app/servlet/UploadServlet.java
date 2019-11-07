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
import engine.java.util.common.LogFactory.LOG;
import engine.java.util.common.TextUtils;
import engine.java.util.file.FileManager;
import engine.java.util.file.FileUtils;
import engine.java.util.secure.ZipUtil;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
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
            if (FileUtils.copyToFile(req.getInputStream(), file))
            {
                return;
            }
        }
        else if (url.endsWith("/log"))
        {
            // 日志上传
            if (ServletFileUpload.isMultipartContent(req))
            {
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                try {
                    List<FileItem> list = upload.parseRequest(req);
                    for (FileItem item : list)
                    {
                        if (!item.isFormField())
                        {
                            // 取出上传文件的文件名称
                            String fileName = item.getName();
                            // 上传文件
                            String log_url = AppConfig.getLogDirPath();
                            File file = new File(req.getServletContext().getRealPath(log_url), fileName);
                            FileManager.createFileIfNecessary(file);
                            item.write(file);
                            return;
                        }
                    }
                } catch (Exception e) {
                    LOG.log(e);
                }
            }
        }
        else
        {
            String query = req.getQueryString();
            LOG.log(String.format("收到来自%s的请求:%s?%s", req.getRemoteAddr(), url, query));
            
            String token = req.getHeader("token");
            User user = TokenManager.authenticate(token);
            if (user != null)
            {
                UserInfo info = user.info;
                Map<String, String> parameters = StringUtil.parseQueryParameters(query);
//              String action = req.getParameter("action"); // 会报错
                String action = parameters.get("action");
                if ("avatar".equals(action))
                {
                    // 上传头像
                    String avatar_url = AppConfig.getAvatarFilePath(info.getUid());
                    File file = new File(req.getServletContext().getRealPath(avatar_url));
                    FileManager.createFileIfNecessary(file);
                    if (FileUtils.copyToFile(req.getInputStream(), file))
                    {
                        // 更新头像版本号
                        resp.setHeader("crc", String.valueOf(++info.avatar_ver));
                        DAOManager.getDAO().update(info, "avatar_ver");
                        return;
                    }
                    
                }
                else if ("authentication".equals(action))
                {
                    // 实名认证
                    String authentication_url = AppConfig.getAuthenticationDirPath(info.getUid());
                    File file = new File(req.getServletContext().getRealPath(authentication_url), "authentication");
                    FileManager.createFileIfNecessary(file);
                    if (FileUtils.copyToFile(req.getInputStream(), file))
                    {
                        // 解压
                        try {
                            ZipUtil.unzip(file, file.getParent());
                        } catch (Exception e) {
                            LOG.log(e);
                        } finally {
                            FileManager.delete(file);
                        }
                        
                        return;
                    }
                }
            }
            else
            {
                LOG.log("Token认证失败");
            }
        }
        
        resp.setStatus(400);
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