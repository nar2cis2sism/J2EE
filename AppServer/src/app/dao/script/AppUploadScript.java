package app.dao.script;

import static app.dao.DAOManager.getDAO;
import app.bean.db.AppUpgradeInfo;
import app.servlet.FileUploadServlet;
import engine.java.dao.DAOTemplate.DAOQueryBuilder;
import engine.java.dao.DAOTemplate.DAOSQLBuilder.DAOExpression;

import java.io.File;

public class AppUploadScript {
    
    /**
     * 上传App包
     * @see AppUpgradeInfo
     */
    public static void uploadApp(int type, String name, String version, File file, String desc, int device) {
        DAOExpression whereClause = DAOExpression.create("version").equal(version)
                               .and(DAOExpression.create("device").equal(device));
        
        AppUpgradeInfo item = getDAO().find(DAOQueryBuilder.create(AppUpgradeInfo.class)
                .setWhereClause(whereClause), 
                AppUpgradeInfo.class);
        if (item != null)
        {
            System.out.println(String.format("已有版本号为%s的安装包", version));
        }
        else
        {
            // 相对路径
            File uploadFile = new File(getApkDir(version), file.getName());
            if (FileUploadServlet.uploadFile(file, uploadFile.getPath()))
            {
                item = new AppUpgradeInfo();
                item.type = type;
                item.name = name;
                item.version = version;
                item.url = FileUploadServlet.FILE_UPLOAD_URL + uploadFile.getPath();
                item.desc = desc;
                item.device = device;
                if (getDAO().save(item))
                {
                    System.out.println("App上传成功");
                    return;
                }
            }
            
            System.out.println("App上传失败");
        }
    }
    
    private static File getApkDir(String version) {
        // 相对路径
        return new File("apk", version);
    }
}