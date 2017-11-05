package com.project.app.action.socket;

import com.project.app.bean.User;
import com.project.app.util.UserManager;
import com.project.server.network.socket.SocketDispatcher.SocketParser;

import protocol.java.stream.ack.MessageACK;

public class Message extends SocketParser<protocol.java.stream.Message> {

    @Override
    public void parse(protocol.java.stream.Message msg) throws Exception {
        User user = UserManager.getUser(Long.parseLong(msg.to));
        if (user != null) user.push(msg);
        
        setAck(new MessageACK());
    }
}