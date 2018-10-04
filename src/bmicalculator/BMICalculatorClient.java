/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmicalculator;

import BMICalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hyuko21
 */
public class BMICalculatorClient {
    static BMICalculator bmicalcImpl;
    
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "BMICalculator";
            bmicalcImpl = BMICalculatorHelper.narrow(ncRef.resolve_str(name));
            
            Scanner in = new Scanner(System.in);
            
            System.out.print("height: ");
            double height = in.nextDouble();
            
            System.out.print("weight: ");
            double weight = in.nextDouble();
            
            System.out.println("Obtained BMICalculator object: " + bmicalcImpl);
            System.out.println("BMI: " + bmicalcImpl.calculateBMI(height, weight));
            
        } catch (org.omg.CORBA.ORBPackage.InvalidName | NotFound | CannotProceed | InvalidName ex) {
            Logger.getLogger(BMICalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
