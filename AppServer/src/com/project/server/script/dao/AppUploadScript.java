package com.project.server.script.dao;

import static com.project.server.storage.DAOManager.getDAO;

import java.io.File;

import com.project.app.servlet.UploadServlet;
import com.project.server.ServerConfig;
import com.project.server.storage.db.AppUpgradeInfo;

import engine.java.dao.DAOTemplate.DAOExpression;

public class AppUploadScript {
    
    /**
     * 上传App包
     */
    public static void uploadApp(int type, String name, String version, String desc, int device, File file) {
        AppUpgradeInfo item = getDAO().find(AppUpgradeInfo.class)
                .where(DAOExpression.create("version").equal(version)
                .and("device").equal(device))
                .get();
        if (item != null)
        {
            System.out.println(String.format("已有版本号为%s的安装包", version));
        }
        else
        {
            // 相对路径
            File uploadFile = new File(getApkDir(device, version), file.getName());
            if (UploadServlet.uploadFile(file, uploadFile.getPath()))
            {
                item = new AppUpgradeInfo();
                item.type = type;
                item.name = name;
                item.version = version;
                item.url = ServerConfig.SERVER_URL + uploadFile.getPath();
                item.desc = desc;
                item.device = device;
                if (getDAO().save(item))
                {
                    return;
                }
            }
            
            System.out.println(String.format("App%s%s上传失败", name, version));
        }
    }
    
    private static File getApkDir(int device, String version) {
        // 相对路径
        return new File("apk/" + device, version);
    }
}