package com.project.server.script.dao;

import static com.project.server.storage.DAOManager.getDAO;

import com.project.app.AppConfig;
import com.project.app.servlet.UploadServlet;
import com.project.server.storage.db.AppUpgradeInfo;

import engine.java.dao.DAOTemplate.DAOExpression;

import java.io.File;

public class AppUploadScript {
    
    /**
     * 上传App包
     */
    public static void uploadApp(int type, String name, String version, String desc, int device, File file) {
        AppUpgradeInfo item = getDAO().find(AppUpgradeInfo.class)
                .where(DAOExpression.create("version").eq(version)
                .and("device").eq(device))
                .get();
        if (item != null)
        {
            getDAO().remove(item);
        }
        
        // 相对路径
        File uploadFile = new File(AppConfig.getAppDir(device, version), file.getName());
        if (UploadServlet.uploadFile(file, uploadFile.getPath()))
        {
            item = new AppUpgradeInfo();
            item.type = type;
            item.name = name;
            item.version = version;
            item.url = uploadFile.getPath();
            item.desc = desc;
            item.device = device;
            if (getDAO().save(item))
            {
                return;
            }
        }
        
        System.out.println(String.format("%s%s上传失败", name, version));
    }
}