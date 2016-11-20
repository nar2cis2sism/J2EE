package app.socket;

import app.bean.User;
import app.servlet.util.Session;
import engine.java.util.io.ByteDataUtil.ByteData;
import protocol.java.stream.Message;

public class SocketParser {
    
    public void parse(int cmd, int msgId, ByteData data) {
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