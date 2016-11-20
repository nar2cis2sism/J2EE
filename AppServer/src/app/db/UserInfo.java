package app.db;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

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
    public long lastModified;               // 最后修改时间

    @DAOProperty
    public String avatar_url;               // 头像下载地址

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
    public long birthday;                   // 出生日期

    @DAOProperty
    public String resume;                   // 个人简介

    /**
     * 0：未认证
     * 1：已认证
     */
    @DAOProperty
    public int authentication;              // 实名认证
    
    public protocol.java.json.UserInfo toProtocol() {
        protocol.java.json.UserInfo item = new protocol.java.json.UserInfo();
        item.user_info_ver = getVersion();
        item.uid = uid;
        item.username = username;
        item.avatar_url = avatar_url;
        item.realname = realname;
        item.nickname = nickname;
        item.gender = gender;
        item.birthday = birthday;
        item.resume = resume;
        item.authentication = authentication;
        return item;
    }
    
    public final long getUid() {
        return uid;
    }
    
    public String getVersion() {
        return lastModified == 0 ? null : String.valueOf(lastModified);
    }
}