
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

      ConcatenatorImpl concatenatorImpl = new ConcatenatorImpl();
      org.omg.CORBA.Object ref = rootPOA.servant_to_reference(concatenatorImpl);
      StringConcat.Concatenator href = StringConcat.ConcatenatorHelper.narrow(ref);

      org.omg.CORBA.Object namingObj = orb.resolve_initial_references("NameService");
      NamingContextExt namingContext = NamingContextExtHelper.narrow(namingObj);

      NameComponent[] name = namingContext.to_name("Concatenator");
      namingContext.rebind(name, href);

      rootPOA.the_POAManager().activate();
      orb.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

class ConcatenatorImpl extends StringConcat.ConcatenatorPOA {
  public String concatenate(String str1, String str2) {
    return str1 + str2;
  }
}

