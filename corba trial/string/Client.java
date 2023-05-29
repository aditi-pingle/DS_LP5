import org.omg.CORBA.*;
import org.omg.CosNaming.*;

/*

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*; 
import java.io.*;

*/


public class Client {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);

      org.omg.CORBA.Object namingObj = orb.resolve_initial_references("NameService");
      NamingContextExt namingContext = NamingContextExtHelper.narrow(namingObj);

      NameComponent[] name = namingContext.to_name("Concatenator");
      StringConcat.Concatenator concatenator = StringConcat.ConcatenatorHelper.narrow(namingContext.resolve(name));

      String result = concatenator.concatenate("Hello, ", "CORBA!");
      
      /*System.out.println("Enter String 1 = ");

      BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
      String str1= br1.readLine();
      
      System.out.println("Enter String 2 = ");

      BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      String str2= br.readLine();
      String result = concatenator.concatenate(str1, str2);
      */
      
      
      
      System.out.println("Concatenation Result: " + result);

      orb.shutdown(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

