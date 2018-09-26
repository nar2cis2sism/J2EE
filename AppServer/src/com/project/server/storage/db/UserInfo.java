package com.project.server.storage.db;

import com.project.app.AppConfig;

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

    /******************************* 注册信息 *******************************/
    
    @DAOProperty
    public String username;                 // 注册用户名（不能重复）

    @DAOProperty
    public String password;                 // 注册密码（加密后的）

    @DAOProperty
    public long register_time;              // 注册时间

    @DAOProperty
    public String email;                    // 绑定邮箱

    @DAOProperty
    public String mobile_phone;             // 用户手机号

    @DAOProperty
    public String nickname;                 // 用户昵称

    @DAOProperty
    public String name;                     // 真实姓名
    
    @DAOProperty
    public boolean isFemale;                // 性别[true:女,false:男]

    @DAOProperty
    public long birthday;                   // 出生日期

    @DAOProperty
    public String region;                   // 所在地区

    @DAOProperty
    public String signature;                // 签名

    @DAOProperty
    public boolean isAuthenticated;         // 实名认证

    @DAOProperty
    public long version;                    // 用户信息版本号

    @DAOProperty
    public long avatar_ver;                 // 头像版本号

    /******************************* 登录信息 *******************************/

    @DAOProperty
    public long last_login_time;            // 最近登录时间

    @DAOProperty
    public String last_login_deviceID;      // 最近登录设备号

    /******************************* 华丽丽的分割线 *******************************/
    
    /**
     * 包含“用户信息版本”与“头像版本”，用“:”分隔
     */
    public String combineVersion() {
        return version + ":" + avatar_ver;
    }

    public protocol.http.UserInfo toProtocol() {
        protocol.http.UserInfo item = new protocol.http.UserInfo();
        item.version = version;
        item.nickname = nickname;
        item.gender = isFemale ? 1 : 0;
        item.birthday = birthday;
        item.region = region;
        item.signature = signature;
        item.authentication = isAuthenticated ? 1 : 0;
        item.avatar_url = AppConfig.getAvatarFilePath(uid);
        return item;
    }

    /******************************* 华丽丽的分割线 *******************************/
    
    public final long getUid() {
        return uid;
    }
    
    /**
     * 获取头像下载地址
     * 
     * @return 没有头像返回Null
     */
    public String getAvatarUrl() {
        return avatar_ver == 0 ? null : AppConfig.getAvatarFilePath(uid);
    }
}