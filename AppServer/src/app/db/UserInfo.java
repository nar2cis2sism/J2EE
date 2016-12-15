package app.db;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;
import engine.java.util.Util;

/**
 * 用户资料
 */
@DAOTable
public class UserInfo {
    
    @DAOPrimaryKey(autoincrement=true)
    private long uid;                       // 用户唯一标识
    
    @DAOProperty
    public String username;                 // 注册用户名

    @DAOProperty
    public String password;                 // 注册密码

    @DAOProperty
    public long registerTime;               // 注册时间

    @DAOProperty
    public long lastModified;               // 最后修改时间

    @DAOProperty
    public long lastLoginTime;              // 最近登录时间

    @DAOProperty
    public String loginDeviceID;            // 登录设备号

    @DAOProperty
    public String avatar_url;               // 头像下载地址

    @DAOProperty
    public String avatar_ver;               // 头像版本号

    @DAOProperty
    public String realname;                 // 真实姓名

    @DAOProperty
    public String nickname;                 // 昵称
    
    /**
     * 0：男
     * 1：女
     */
    @DAOProperty
    public int gender;                      // 性别

    @DAOProperty
    public String city;                     // 地区

    @DAOProperty
    public long birthday;                   // 出生日期

    @DAOProperty
    public String resume;                   // 个人简介

    @DAOProperty
    public boolean isAuthenticated;         // 实名认证

    /********************* 好友 *********************/

    @DAOProperty
    public String friend_list;              // 好友列表，用","分隔

    @DAOProperty
    public long friend_list_timestamp;      // 好友列表更新时间戳
    
    public final long getUid() {
        return uid;
    }
    
    /**
     * 包含“用户信息版本”和“头像版本”，用“:”分隔
     */
    public String getVersion() {
        String infoVer = String.valueOf(lastModified);
        String avatarVer = Util.getString(avatar_ver, "");
        return infoVer + ":" + avatarVer;
    }

    public protocol.java.json.UserInfo toProtocol() {
        protocol.java.json.UserInfo item = new protocol.java.json.UserInfo();
        item.user_info_ver = getVersion();
        item.avatar_url = avatar_url;
        item.nickname = nickname;
        item.qrcode = null;
        item.gender = gender;
        item.city = city;
        item.birthday = birthday;
        item.resume = resume;
        item.authentication = isAuthenticated ? 1 : 0;
        return item;
    }
}