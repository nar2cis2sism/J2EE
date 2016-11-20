package app.servlet;

import engine.java.http.HttpConnector;
import engine.java.http.HttpResponse;
import engine.java.util.file.FileManager;
import engine.java.util.io.IOUtil;
import engine.java.util.string.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadServlet extends HttpServlet {
    
    public static final String FILE_UPLOAD_URL = AppServlet.SERVER_URL + "fileUpload?path=";
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getParameter("path");
        if (TextUtils.isEmpty(path)) return;
        
        File file = new File(req.getServletContext().getRealPath(path));
        FileManager.createFileIfNecessary(file);
        if (!FileManager.writeFile(file, IOUtil.readStream(req.getInputStream()), false))
        {
            throw new IOException("文件上传失败:" + file);
        }
    }
    
    /**
     * 上传文件
     * @param srcFile 源文件
     * @param desPath 目标文件相对服务器根目录路径
     * @return 是否成功
     */
    public static boolean uploadFile(File srcFile, String desPath) {
        try {
            HttpResponse response = new HttpConnector(FILE_UPLOAD_URL + desPath, FileManager.readFile(srcFile))
            .setRemark("文件上传")
            .connect();

            return response.getStatusCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}