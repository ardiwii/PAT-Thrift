package if4031;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import if4031.ChatService;
import org.apache.thrift.server.TThreadPoolServer;
public class ChatServer {
	public static ChatHandler handler;
	public static ChatService.Processor processor;
	public static void main(String [] args) {
		try {
			handler = new ChatHandler();
			processor = new ChatService.Processor(handler);
			Runnable simple = new Runnable() {
				public void run() {
				simple(processor);
				}
			};
			new Thread(simple).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public static void simple(ChatService.Processor processor) {
		try {
                    TServerTransport serverTransport = new TServerSocket(9090);
                    
                    TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

                    System.out.println("Starting the simple server...");
                    server.serve();
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
	}
}
