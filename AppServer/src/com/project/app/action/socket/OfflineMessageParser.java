package com.project.app.action.socket;

import com.project.app.bean.User;
import com.project.server.network.socket.SocketDispatcher.SocketParser;
import com.project.server.storage.dao.OfflineMessageDAO;

import protocol.socket.ack.OfflineMessageACK;
import protocol.socket.req.Message;
import protocol.socket.req.OfflineMessage;

import java.util.List;

public class OfflineMessageParser extends SocketParser<OfflineMessage> {

    @Override
    public void parse(OfflineMessage msg, User user) throws Exception {
        OfflineMessageACK ack = new OfflineMessageACK();
        ack.message = getMessages(OfflineMessageDAO.getOfflineMessageList(user.info.getUid(), msg.timestamp));
        setAck(ack);
    }
    
    private static Message[] getMessages(List<com.project.server.storage.db.OfflineMessage> list) {
        if (list == null || list.isEmpty())
        {
            return null;
        }
        
        Message[] msgs = new Message[list.size()];
        for (int i = 0; i < msgs.length; i++)
        {
            msgs[i] = list.get(i).getMessage();
        }
        
        return msgs;
    }
}