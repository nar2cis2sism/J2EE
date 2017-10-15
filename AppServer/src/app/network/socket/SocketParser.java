package app.network.socket;

import app.bean.User;
import app.servlet.util.Session;
import protocol.java.ProtocolWrapper.ProtocolEntity.ProtocolData;
import protocol.java.stream.Message;
import protocol.java.stream.ack.MessageACK;

public class SocketParser {
    
    public void parse(int cmd, int msgId, ProtocolData data) {
        if (data instanceof Message)
        {
            Message msg = (Message) data;
            parseMessage(cmd, msgId, msg);
        }
    }
    
    private void parseMessage(int cmd, int msgId, Message msg) {
        User user = Session.getUser(Long.parseLong(msg.from));
        if (user != null) user.push(MessageACK.CMD, msgId, new MessageACK());
        
        user = Session.getUser(Long.parseLong(msg.to));
        if (user != null) user.push(Message.CMD, 0, msg);
    }
}