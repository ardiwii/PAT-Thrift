package if4031;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class ChatClient {
    
    private String nickname = "";
    private ChatService.Client client;
    private String debugLog;
    private TTransport transport;
    
//	public static void main(String [] args) {
//            ChatClient chat = new ChatClient();
//	}
        
        public ChatClient(){
            try {
			transport = new TSocket("localhost", 9090);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			client = new ChatService.Client(protocol);
			//perform(client);
        	} catch (TException x) {
			x.printStackTrace();
		}
        }

    public String initNickname(){
        Random random = new Random();
        nickname = "user_" + random.nextInt(10000);
        return nickname;
    }    
        
    public String receiveMessages() throws TException{
        return client.recv(nickname);
    }      
        
    public void Close(){
        transport.close();
    }
    
    public String userInputs(String input) throws TException {
        String[] parsed_msg;
        int response_code = -1;
        parsed_msg = input.split(" ");

        if(input.startsWith("/NICK")){
            if(parsed_msg.length>1){
                nickname = parsed_msg[1];
                return nickname;
            }
            else{
                return "no nickname specified, to change nickname, type \"/NICK <new_nickname>\" ";
                //System.out.println("no nickname specified, to change nickname, type \"/NICK <new_nickname>\" ");
            }
        }
        else if(input.startsWith("/JOIN")){
            if(parsed_msg.length>1){
                //System.out.println("joining channel: " + parsed_msg[1]);
                response_code = client.joinChannel(nickname, parsed_msg[1]);
                if(response_code==1){
                    return "success, you are connected to channel " + parsed_msg[1];
                    //System.out.println("success, you are connected to channel " + parsed_msg[1]);
                }
                else if(response_code==2){
                    return "you have joined that channel";
                    //System.out.println("you have joined that channel");
                }
                else if(response_code==-1){
                    System.out.println("no response");
                    return "no response";
                }
                else{
                    //System.out.println("unknown error");
                    return "unknown error";
                }
            }
            else{
                //System.out.println("no channel name specified, to join channel, type \"/JOIN <channel_name>\" ");
                return "no channel name specified, to join channel, type \"/JOIN <channel_name>\" ";
            }

        }
        else if(input.startsWith("/LEAVE")){
            if(parsed_msg.length>1){
               response_code = client.leaveChannel(nickname, parsed_msg[1]);
                if(response_code==1){
                    return "success, you have left channel " + parsed_msg[1];
                }
                else if(response_code==2){
                    return "you are not in that channel";
                }
                else if(response_code==3){
                    return "channel not found";
                }
                else if(response_code==-1){
                    return "no response";
                }
                else{
                    return "unknown error";
                }
            }
            else{
                return "no channel name specified, to leave a channel, type \"/LEAVE <channel_name>\" ";
            }

        }
        else if(input.startsWith("@")){
            input = input.substring(input.indexOf(" "));
            //System.out.println("to channel: " + parsed_msg[0].substring(1) + " message: " + input);
            response_code = client.send(nickname, input, parsed_msg[0].substring(1));
            if(response_code==1){
                return "message sent to channel: " + parsed_msg[0].substring(1);
            }
            else if(response_code==2){
                return "you are not in that channel, or that channel is not exist";
            }
            else if(response_code==-1){
                return "no response";
            }
            else{
                return "unknown error";
            }
        }
        else if(input.equalsIgnoreCase("/exit")){
            
        }
        else{
            //System.out.println("to all channels: " + " message: " + input);
            response_code = client.send(nickname,input,"");
            if(response_code==1){
                return "message sent to all channel";
            }
            else if(response_code==2){
                return "you are not in that channel, or that channel is not exist";
            }
            else if(response_code==-1){
                return "no response";
            }
            else{
                return "unknown error";
            }
        }
        return "";
    }
}

