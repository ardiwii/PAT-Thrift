package if4031;

import org.apache.thrift.TException;
import if4031.ChatService;
import if4031.Channel;
import java.util.ArrayList;
import java.util.List;
public class ChatHandler implements ChatService.Iface {
    
    private ListChannel listChannel = new ListChannel();
    
    @Override
    public int send(String nick, String message, String channelName) throws TException {
        String n = "";
        boolean succ = false;
        if(!channelName.equals(n)){
            succ = listChannel.addMessageToChannel(nick, message, channelName);
        }
        else{
            listChannel.addMessage(nick, message);
        }
        if(succ){
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public String recv(String nic) throws TException {
        return listChannel.getMessage(nic);
    }

    @Override
    public int joinChannel(String nick, String channel) throws TException {
        listChannel.joinChannel(nick, channel);
        return 1;
    }

    @Override
    public int leaveChannel(String nick, String channel) throws TException {
         return 1;
    }
	
}
