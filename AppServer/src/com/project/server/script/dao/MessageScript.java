package com.project.server.script.dao;

import com.project.server.storage.dao.OfflineMessageDAO;
import com.project.server.storage.dao.UserDAO;
import com.project.server.storage.db.UserInfo;

import protocol.socket.req.Message;

public class MessageScript {
    
    /**
     * 发送一条离线消息
     */
    public static void sendOfflineMessage(String sender, String receiver, String content) {
        Message msg = new Message();
        msg.account = sender;
        msg.content = content;
        msg.creationTime = System.currentTimeMillis();
        msg.id = sender + receiver + msg.creationTime;
        
        UserInfo receiver_info = UserDAO.getUserByUsername(receiver);
        OfflineMessageDAO.addOfflineMessage(receiver_info.getUid(), msg);
    }
}