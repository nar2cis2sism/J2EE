package com.project.app.action.socket;

import com.project.app.bean.User;
import com.project.app.util.UserManager;
import com.project.server.network.socket.SocketDispatcher.SocketParser;
import com.project.server.storage.dao.OfflineMessageDAO;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import protocol.socket.ErrorInfo;
import protocol.socket.ack.MessageACK;
import protocol.socket.req.Message;

public class MessageParser extends SocketParser<Message> {

    @Override
    public void parse(Message msg, User user) throws Exception {
        String account = msg.account;
        UserInfo info = UserDAO.getUserByUsername(account);
        if (info != null)
        {
            // 透传消息，重新设置账号，时间
            msg.account = user.info.username;
            msg.creationTime = System.currentTimeMillis();
            
            user = UserManager.getUser(info.getUid());
            if (user != null && user.push(msg))
            {
                // 用户在线
            }
            else
            {
                // 用户离线
                OfflineMessageDAO.addOfflineMessage(info.getUid(), msg);
            }
            
            setAck(new MessageACK());
        }
        else
        {
            setAck(new ErrorInfo(404));
        }
    }
}