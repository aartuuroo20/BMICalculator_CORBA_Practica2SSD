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

            System.out.print("Are you men or female?: ");
            String gender = in.nextLine();

            
            System.out.print("height(cm): ");
            double height = in.nextDouble();
            
            System.out.print("weight(kg): ");
            double weight = in.nextDouble();

            System.out.print("Age: ");
            double age = in.nextInt();

            System.out.println("Obtained BMICalculator object: " + bmicalcImpl);
            System.out.println("BMI: " + bmicalcImpl.calculateBMI(height, weight));

            if(gender == "female"){
                System.out.println("TMB: " + bmicalcImpl.calculateTMBf(height, weight, age));
            }else{
                System.out.println("TMB: " + bmicalcImpl.calculateTMB(height, weight, age));
            }


            bmicalcImpl.shutdown();
        } catch (org.omg.CORBA.ORBPackage.InvalidName | NotFound | CannotProceed | InvalidName ex) {
            Logger.getLogger(BMICalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
