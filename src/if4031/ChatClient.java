package if4031;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class ChatClient {
    
    //private static String nickname = "";
    
	public static void main(String [] args) {
		try {
			TTransport transport;
			transport = new TSocket("localhost", 9090);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			ChatService.Client client = new ChatService.Client(protocol);
			perform(client);
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

//static Runnable getMessageRunnable = new Runnable() {
//    @Override
//    public void run() {
//        System.out.println(client.recv(nickname));
//    }
//};
        
private static void perform(ChatService.Client client) throws TException
	{
            Random random = new Random();
            String nickname = "user_" + random.nextInt(10000);
            String input = "";
            String[] parsed_msg;
            Scanner scanner = new Scanner(System.in);
            int response_code = -1;
            
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//            executor.scheduleAtFixedRate(getMessageRunnable, 0, 3, TimeUnit.SECONDS);
            
            while(!input.equalsIgnoreCase("/exit")){
                System.out.print(nickname + ">>");
                input = scanner.nextLine();
                parsed_msg = input.split(" ");
                   
                if(input.startsWith("/NICK")){
                    if(parsed_msg.length>1){
                        nickname = parsed_msg[1];
                    }
                    else{
                        System.out.println("no nickname specified, to change nickname, type \"/NICK <new_nickname>\" ");
                    }
                }
                else if(input.startsWith("/JOIN")){
                    if(parsed_msg.length>1){
                        System.out.println("joining channel: " + parsed_msg[1]);
                        response_code = client.joinChannel(nickname, parsed_msg[1]);
                        if(response_code==1){
                            System.out.println("success, you are connected to channel " + parsed_msg[1]);
                        }
                        else if(response_code==2){
                            System.out.println("you have joined that channel");
                        }
                        else if(response_code==-1){
                            System.out.println("no response");
                        }
                        else{
                            System.out.println("unknown error");
                        }
                    }
                    else{
                        System.out.println("no channel name specified, to join channel, type \"/JOIN <channel_name>\" ");
                    }
                    
                }
                else if(input.startsWith("/LEAVE")){
                    if(parsed_msg.length>1){
                       response_code = client.leaveChannel(nickname, parsed_msg[1]);
                        if(response_code==1){
                            System.out.println("success, you have left channel " + parsed_msg[1]);
                        }
                        else if(response_code==2){
                            System.out.println("you are not in that channel");
                        }
                        else if(response_code==3){
                            System.out.println("channel not found");
                        }
                        else if(response_code==-1){
                            System.out.println("no response");
                        }
                        else{
                            System.out.println("unknown error");
                        }
                    }
                    else{
                        System.out.println("no channel name specified, to leave a channel, type \"/LEAVE <channel_name>\" ");
                    }
                    
                }
                else if(input.startsWith("@")){
                    input = input.substring(input.indexOf(" "));
                    System.out.println("to channel: " + parsed_msg[0].substring(1) + " message: " + input);
                    response_code = client.send(nickname, input, parsed_msg[0].substring(1));
                    if(response_code==1){
                        System.out.println("message sent");
                    }
                    else if(response_code==2){
                        System.out.println("you are not in that channel, or that channel is not exist");
                    }
                    else if(response_code==-1){
                        System.out.println("no response");
                    }
                    else{
                        System.out.println("unknown error");
                    }
                }
                else if(input.equalsIgnoreCase("/exit")){
                    break;
                }
                else{
                    System.out.println("to all channels: " + " message: " + input);
                    response_code = client.send(nickname,input,"");
                    if(response_code==1){
                        System.out.println("message sent");
                    }
                    else if(response_code==2){
                        System.out.println("you are not in that channel, or that channel is not exist");
                    }
                    else if(response_code==-1){
                        System.out.println("no response");
                    }
                    else{
                        System.out.println("unknown error");
                    }
                }
                System.out.println(client.recv(nickname));
            }            
	}
}
