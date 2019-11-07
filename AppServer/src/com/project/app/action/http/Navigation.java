package com.project.app.action.http;

import com.project.app.AppConfig;
import com.project.app.servlet.util.RequestDispatcher.AppParser;
import com.project.server.network.socket.SocketManager;
import com.project.server.storage.dao.CommonDAO;
import com.project.server.storage.db.AppUpgradeInfo;

import engine.java.util.common.LogFactory.LOG;
import engine.java.util.common.TextUtils;

import org.json.JSONObject;

import protocol.http.NavigationData;

public class Navigation extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        // 客户端类型
        int device = json.optInt("device", -1);
        // 客户端版本号
        String version = json.optString("version", null);
        
        if (device == -1 || version == null)
        {
            setRequestParamError();
            return;
        }
        
        VersionInfo versionInfo = new VersionInfo();
        if (!versionInfo.parse(version))
        {
            setRequestParamError();
            return;
        }
        
        String socket_server_url = SocketManager.getAddress();
        if (TextUtils.isEmpty(socket_server_url))
        {
            throw new Exception("Socket服务器未启动");
        }
        
        NavigationData data = new NavigationData();
        data.socket_server_url = socket_server_url;
        data.upload_server_url = AppConfig.UPLOAD_URL;
        data.download_server_url = AppConfig.SERVER_URL;

        AppUpgradeInfo info = CommonDAO.getLastestApp(device);
        if (info != null)
        {
            VersionInfo lastestVersionInfo = new VersionInfo();
            if (!lastestVersionInfo.parse(info.version))
            {
                throw new Exception("数据库信息错误:app.version=" + info.version);
            }
            
            if (lastestVersionInfo.compareTo(versionInfo) > 0)
            {
                // 应用有更新
                data.upgrade = info.toProtocol();
            }
        }
        
        setSuccess(data);
    }
    
    private static class VersionInfo implements Comparable<VersionInfo> {
        
        public int major;
        public int minor;
        public int fixed;
        
        public boolean parse(String version) {
            try {
                String[] strs = version.split("\\.");
                major = Integer.parseInt(strs[0]);
                minor = Integer.parseInt(strs[1]);
                fixed = Integer.parseInt(strs[2]);
                return true;
            } catch (Exception e) {
                LOG.log(e);
                return false;
            }
        }

        @Override
        public int compareTo(VersionInfo o) {
            if (major != o.major)
            {
                return major - o.major;
            }
            
            if (minor != o.minor)
            {
                return minor - o.minor;
            }
            
            return fixed - o.fixed;
        }
    }
}