import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import if4031.CalculatorService;
public class CalculatorServer {
	public static CalculatorHandler handler;
	public static CalculatorService.Processor processor;
	public static void main(String [] args) {
		try {
			handler = new CalculatorHandler();
			processor = new CalculatorService.Processor(handler);
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
	
	public static void simple(CalculatorService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
