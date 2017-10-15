package app.network.http.parser;

import app.network.socket.SocketManager;
import app.servlet.AppConfig;
import app.servlet.util.RequestDispatcher.AppParser;
import app.storage.dao.CommonDAO;
import app.storage.db.AppUpgradeInfo;
import app.util.GsonUtil;
import engine.java.common.LogFactory.LOG;
import engine.java.util.string.TextUtils;

import org.json.JSONObject;

public class NavigationParser extends AppParser {

    @Override
    public void parse(JSONObject json) throws Exception {
        int device = json.optInt("device", -1);
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
        
        JSONObject data = new JSONObject();
        data.put("socket_server_url", socket_server_url);
        data.put("upload_server_url", AppConfig.UPLOAD_URL);
        data.put("download_server_url", AppConfig.SERVER_URL);

        AppUpgradeInfo item = CommonDAO.getLastestApp(device);
        if (item != null)
        {
            VersionInfo lastestVersionInfo = new VersionInfo();
            if (!lastestVersionInfo.parse(item.version))
            {
                throw new Exception("数据库信息错误:app.version=" + item.version);
            }
            
            if (lastestVersionInfo.compareTo(versionInfo) > 0)
            {
                // 应用有更新
                data.put("upgrade", new JSONObject(GsonUtil.toJson(item.toProtocol())));
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