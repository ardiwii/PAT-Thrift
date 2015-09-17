import org.apache.thrift.TException;
import if4031.CalculatorService;
public class CalculatorHandler implements CalculatorService.Iface {
	@Override
	public int multiply(int n1, int n2) throws TException {
		System.out.println("Multiply(" + n1 + "," + n2 + ")");
		return n1 * n2;
	}
	@Override
	public int add(int n1, int n2) throws TException {
		System.out.println("Add(" + n1 + "," + n2 + ")");
		return n1 + n2;
	}
}
