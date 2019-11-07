package com.project.server.script.dao;

import com.project.app.AppConfig;
import com.project.app.servlet.UploadServlet;
import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.AppUpgradeInfo;

import java.io.File;

public class AppUploadScript extends BaseDAO {
    
    /**
     * 上传App包
     */
    public static void uploadApp(int type, String name, String version, String desc, int device, File file) {
        // 相对路径
        File uploadFile = new File(AppConfig.getAppDir(device, version), file.getName());
        if (UploadServlet.uploadFile(file, uploadFile.getPath()))
        {
            AppUpgradeInfo info = new AppUpgradeInfo();
            info.type = type;
            info.name = name;
            info.version = version;
            info.url = uploadFile.getPath();
            info.desc = desc;
            info.device = device;
            if (dao.save(info))
            {
                return;
            }
        }

        System.out.println(String.format("%s%s上传失败", name, version));
    }
}