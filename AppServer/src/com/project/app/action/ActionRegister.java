package com.project.app.action;

import static com.project.app.servlet.util.RequestDispatcher.register;
import static com.project.server.network.socket.SocketDispatcher.register;

import com.project.app.action.http.EditUserInfo;
import com.project.app.action.http.GetSmsCode;
import com.project.app.action.http.GetUserInfo;
import com.project.app.action.http.Login;
import com.project.app.action.http.Navigation;
import com.project.app.action.http.QueryFriendList;
import com.project.app.action.http.Register;
import com.project.app.action.socket.MessageParser;
import com.project.app.action.socket.OfflineMessageParser;
import com.project.server.network.socket.SocketDispatcher.SocketParser;

import protocol.socket.req.Message;
import protocol.socket.req.OfflineMessage;
import protocol.util.ProtocolWrapper.ProtocolEntity.ProtocolData;

public class ActionRegister {
    
    public static void init() {
        initHttpAction();
        initSocketAction();
    }
    
    private static void initHttpAction() {
        // 获取导航配置
        register("navigation", Navigation.class);
        // 用户登录
        register("login", Login.class);
        // 获取手机验证码
        register("get_sms_code", GetSmsCode.class);
        // 用户注册
        register("register", Register.class);
        // 获取个人信息
        register("get_user_info", GetUserInfo.class);
        // 修改个人信息
        register("edit_user_info", EditUserInfo.class);
        // 查询好友列表
        register("query_friend_list", QueryFriendList.class);
//        // 搜索联系人
//        register("search_contact", SearchContact.class);
//        // 添加删除好友
//        register("add_friend", AddFriend.class);
    }
    
    private static void initSocketAction() {
        // 聊天消息
        registerSocketAction(Message.class, MessageParser.class);
        // 拉取离线消息
        registerSocketAction(OfflineMessage.class, OfflineMessageParser.class);
    }
    
    private static <D extends ProtocolData, P extends SocketParser<D>> void registerSocketAction(Class<D> d, Class<P> p) {
        register(d.getName().hashCode(), p);
    }
}