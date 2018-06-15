package com.project.app.action.socket;

import com.project.app.bean.User;
import com.project.app.util.UserManager;
import com.project.server.network.socket.SocketDispatcher.SocketParser;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import protocol.java.stream.ErrorInfo;
import protocol.java.stream.ack.MessageACK;
import protocol.java.stream.req.Message.MessageBody;

public class Message extends SocketParser<protocol.java.stream.req.Message> {

    @Override
    public void parse(protocol.java.stream.req.Message msg) throws Exception {
        UserInfo info = UserDAO.getUserByUsername(msg.to);
        if (info != null)
        {
            fixMessage(msg);
            User user = UserManager.getUser(info.getUid());
            if (user != null)
            {
                // 用户在线
                user.push(msg);
                setAck(new MessageACK());
            }
            else
            {
                // 用户离线
            }
        }
        else
        {
            setAck(new ErrorInfo(404));
        }
    }
    
    private void fixMessage(protocol.java.stream.req.Message msg) {
        // 透传消息，重新设置时间
        MessageBody[] body = msg.body;
        if (body != null)
        {
            for (MessageBody b : body)
            {
                b.creationTime = System.currentTimeMillis();
            }
        }
    }
}