package com.project.server.storage.db;

import com.project.app.AppConfig;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;
import protocol.http.FriendData.FriendInfo;
import protocol.http.FriendListData.FriendListItem;
import protocol.http.UserData;

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

    /******************************* 用户信息 *******************************/

    @DAOProperty
    public String email;                    // 绑定邮箱

    @DAOProperty
    public String mobile_phone;             // 用户手机号

    @DAOProperty
    public String nickname;                 // 用户昵称

    @DAOProperty
    public String name;                     // 真实姓名
    
    /**
     * 0：男
     * 1：女
     */
    @DAOProperty
    public int gender;                      // 性别

    @DAOProperty
    public long birthday;                   // 出生日期

    /**
     * 此字段包含
     * “区域编码(String)”与
     * “地区名称(String)”，
     * 用“:”分隔
     */
    @DAOProperty
    public String region;                   // 所在地区

    @DAOProperty
    public String signature;                // 签名

    /**
     * 0：未认证
     * 1：已认证
     */
    @DAOProperty
    public int authentication;              // 实名认证

    @DAOProperty
    public int version;                     // 用户信息版本号

    @DAOProperty
    public int avatar_ver;                  // 头像版本号

    /******************************* 登录信息 *******************************/

    @DAOProperty
    public long last_login_time;            // 最近登录时间

    @DAOProperty
    public String last_login_deviceID;      // 最近登录设备号

    /******************************* 华丽丽的分割线 *******************************/
    
    /**
     * 用户资料版本
     * 高位表示“头像版本(Int32)”
     * 低位表示“信息版本(Int32)”
     */
    public long combineVersion() {
        return ((long) avatar_ver) << 32 | version;
    }

    public UserData toProtocol() {
        UserData data = new UserData();
        data.version = version;
        data.nickname = nickname;
        data.gender = gender;
        data.birthday = birthday;
        data.region = region;
        data.signature = signature;
        data.authentication = authentication;
        data.avatar_url = AppConfig.getAvatarFilePath(uid);
        return data;
    }

    public void toProtocol(FriendInfo info) {
        info.nickname = nickname;
        info.gender = gender;
        info.region = region;
        info.signature = signature;
        info.avatar_url = AppConfig.getAvatarFilePath(uid);
        info.mobile_phone = mobile_phone;
    }

    public void toProtocol(FriendListItem.FriendInfo info) {
        info.version = combineVersion();
        toProtocol((FriendInfo) info);
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