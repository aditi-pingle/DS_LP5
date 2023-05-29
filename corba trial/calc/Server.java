import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import java.util.Properties;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Policy;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.*;
import org.omg.PortableServer.Servant;

public class Server {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

      CalculatorImpl calculatorImpl = new CalculatorImpl();
      org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calculatorImpl);
      Calculator.CalculatorInterface href = Calculator.CalculatorInterfaceHelper.narrow(ref);

      org.omg.CORBA.Object namingObj = orb.resolve_initial_references("NameService");
      NamingContextExt namingContext = NamingContextExtHelper.narrow(namingObj);

      NameComponent[] name = namingContext.to_name("Calculator");
      namingContext.rebind(name, href);

      rootPOA.the_POAManager().activate();
      orb.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class CalculatorImpl extends Calculator.CalculatorInterfacePOA {
  public float add(float num1, float num2) {
    return num1 + num2;
  }

  public float subtract(float num1, float num2) {
    return num1 - num2;
  }

  public float multiply(float num1, float num2) {
    return num1 * num2;
  }

  public float divide(float num1, float num2) {
    return num1 / num2;
  }
}

