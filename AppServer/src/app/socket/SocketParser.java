package app.socket;

import app.bean.User;
import app.servlet.util.Session;
import protocol.java.ProtocolWrapper.ProtocolEntity.ProtocolData;
import protocol.java.stream.Message;

public class SocketParser {
    
    public void parse(int cmd, int msgId, ProtocolData data) {
        if (data instanceof Message)
        {
            Message msg = (Message) data;
            User user = Session.getUser(msg.to);
            if (user != null)
            {
                user.push(Message.CMD, 0, data);
            }
        }
    }
}