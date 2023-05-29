import org.omg.CORBA.*;
import org.omg.CosNaming.*;


//import java.util.*;

public class Client {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);

      org.omg.CORBA.Object namingObj = orb.resolve_initial_references("NameService");
      NamingContextExt namingContext = NamingContextExtHelper.narrow(namingObj);

      NameComponent[] name = namingContext.to_name("Calculator");
      Calculator.CalculatorInterface calculator = Calculator.CalculatorInterfaceHelper.narrow(namingContext.resolve(name));

      float num1 = 10.5f;
      float num2 = 5.5f;
      
 /*     Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
	System.out.print("Enter first number- ");  
	float num1= sc.nextFloat();  
	System.out.print("Enter second number- ");  
	float num2= sc.nextFloat();  

*/

      float sum = calculator.add(num1, num2);
      System.out.println("Sum: " + sum);

      float difference = calculator.subtract(num1, num2);
      System.out.println("Difference: " + difference);

      float product = calculator.multiply(num1, num2);
      System.out.println("Product: " + product);
      
      float division = calculator.divide(num1,num2);
      System.out.println("Division: " + division);

orb.shutdown(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
    }
