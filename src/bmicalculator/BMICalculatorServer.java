/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmicalculator;

import BMICalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author hyuko21
 */

class BMICalculatorImpl extends BMICalculatorPOA {
    private ORB orb;
    
    public void setORB(ORB orb_val) {
        this.orb = orb_val;
    }
    public double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }
}

public class BMICalculatorServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            BMICalculatorImpl bmicalcImpl = new BMICalculatorImpl();
            bmicalcImpl.setORB(orb);
            
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(bmicalcImpl);
            BMICalculator bmicalc = BMICalculatorHelper.narrow(ref);
            
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "BMICalculator";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, bmicalc);
            
            System.out.println("BMICalculatorServer ready and waiting ...");
            
            orb.run();
        } catch (InvalidName | AdapterInactive | org.omg.CosNaming.NamingContextPackage.InvalidName | ServantNotActive | WrongPolicy | NotFound | CannotProceed ex) {
            Logger.getLogger(BMICalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("BMICalculatorServer Exiting ...");
    }
}
