package if4031;

import java.util.Scanner;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ChatClient {
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

private static void perform(ChatService.Client client) throws TException
	{
            String nickname = "<unnamed>";
            String input = "";
            String[] parsed_msg;
            Scanner scanner = new Scanner(System.in);
            
            while(!input.equalsIgnoreCase("/exit")){
                System.out.print(nickname + ">>");
                input = scanner.nextLine();
                parsed_msg = input.split(" ");
                   
                if(input.startsWith("/NICK")){
                    nickname = parsed_msg[1];
                }
                else if(input.startsWith("/JOIN")){
                    System.out.println("joining channel: " + parsed_msg[1]);
                    client.joinChannel(nickname, parsed_msg[1]);
                }
                else if(input.startsWith("/LEAVE")){
                    //TODO : implement leave channel
                }
                else if(input.startsWith("@")){
                    input = input.substring(input.indexOf(" "));
                    System.out.println("to channel: " + parsed_msg[0].substring(1) + " message: " + input);
                    client.send(nickname, input, parsed_msg[0].substring(1));
                }
                else if(input.equalsIgnoreCase("/exit")){
                    break;
                }
                else{
                    System.out.println("to all channels: " + " message: " + input);
                    client.send(nickname,input,"");
                }
                System.out.println(client.recv(nickname));
            }            
	}
}
