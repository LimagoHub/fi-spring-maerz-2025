import client.Client;
import common.LoggerProxy;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecurity;

public class Main {
    public static void main(String[] args) {
                                // 1000
        Calculator calculator = new CalculatorImpl();
                                // 2000
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecurity(calculator);
        Client client = new Client(calculator);

        client.go();
    }
}