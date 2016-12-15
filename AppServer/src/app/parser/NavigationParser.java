package app.parser;

import app.dao.CommonDAO;
import app.db.AppUpgradeInfo;
import app.servlet.util.RequestDispatcher.AppParser;
import app.socket.SocketManager;

import com.google.gson.Gson;

import engine.java.log.LogFactory.LOG;
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
            throw new Exception("Socket服务器地址错误:address=" + socket_server_url);
        }
        
        JSONObject data = new JSONObject();
        data.put("socket_server_url", socket_server_url);

        AppUpgradeInfo item = CommonDAO.getNewlyApp(device);
        if (item != null)
        {
            VersionInfo newlyVersionInfo = new VersionInfo();
            if (!newlyVersionInfo.parse(item.version))
            {
                throw new Exception("数据库信息错误:version=" + item.version);
            }
            
            if (newlyVersionInfo.compareTo(versionInfo) > 0)
            {
                // 应用有更新
                data.put("upgrade", new JSONObject(new Gson().toJson(item.toProtocol())));
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