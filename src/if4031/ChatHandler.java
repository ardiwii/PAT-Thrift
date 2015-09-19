package if4031;

import org.apache.thrift.TException;
import if4031.ChatService;
import if4031.Channel;
import java.util.ArrayList;
import java.util.List;
public class ChatHandler implements ChatService.Iface {
    
    private ListChannel listChannel = new ListChannel();
    
    @Override
    public boolean send(String nick, String message, String channelName) throws TException {
        String n = "";
        if(!channelName.equals(n)){
            listChannel.addMessageToChannel(nick, message, channelName);
        }
        else{
            listChannel.addMessage(nick, message);
        }
        return true;
    }

    @Override
    public String recv(String nic) throws TException {
        return listChannel.getMessage(nic);
    }

    @Override
    public boolean joinChannel(String nick, String channel) throws TException {
        listChannel.joinChannel(nick, channel);
        return true;
    }

    @Override
    public boolean leaveChannel(String nick, String channel) throws TException {
         return true;
    }
	
}
