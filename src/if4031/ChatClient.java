package if4031;

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
            String nick = "noob";
            String channel = "chan";
            String channel2 = "";
            client.joinChannel(nick, channel);
            client.send(nick, "ngapain?",channel2);
            System.out.println(client.recv(nick));
            
	}
}
