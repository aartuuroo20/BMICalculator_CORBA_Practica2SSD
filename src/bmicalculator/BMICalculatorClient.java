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

import java.util.HashMap;
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

            System.out.print("Are you man or female?: ");
            String gender = in.nextLine();

            
            System.out.print("height(cm): ");
            double height = in.nextDouble();
            
            System.out.print("weight(kg): ");
            double weight = in.nextDouble();

            System.out.print("Age: ");
            double age = in.nextInt();

            System.out.println("Obtained BMICalculator object: " + bmicalcImpl);
            System.out.println("BMI: " + bmicalcImpl.calculateBMI(height, weight));

            HashMap<String, Integer> listaComidasSubirPeso = new HashMap<>();
            listaComidasSubirPeso.put("Primera comida", 1000);
            listaComidasSubirPeso.put("Segunda comida", 1200);
            listaComidasSubirPeso.put("Tercera comida", 1000);
            listaComidasSubirPeso.put("Cuarta comida", 1200);
            int totalCalorias = 0;
            for (int calorias : listaComidasSubirPeso.values()) {
                totalCalorias += calorias;
            }

            HashMap<String, Integer> listaComidasBajarPeso = new HashMap<>();
            listaComidasBajarPeso.put("Primera comida", 500);
            listaComidasBajarPeso.put("Segunda comida", 500);
            listaComidasBajarPeso.put("Tercera comida", 500);
            int totalCaloriasBajarPeso = 0;
            for (int calorias : listaComidasBajarPeso.values()) {
                totalCaloriasBajarPeso += calorias;
            }

            if(gender == "female"){
                double tmb = bmicalcImpl.calculateTMBf(height, weight, age);
                System.out.println("Your TMB is: " + tmb);

                System.out.println("If you want to gain weight, you need to eat more,  an example of what you should eat is: ");
                System.out.println(listaComidasSubirPeso.keySet() + " " + listaComidasSubirPeso.values() + " calories, " + "total calories: " + totalCalorias + " calories");
                
                System.out.println("If you want to lose weight, you need to eat less,  an example of what you should eat is: ");
                System.out.println(listaComidasBajarPeso.keySet() + " " + listaComidasBajarPeso.values() + " calories, " + "total calories: " + totalCaloriasBajarPeso + " calories");
              
                System.out.println("If you want to mantain weight, you are eating the right amount of calories.");
                
            }else{
                double tmb = bmicalcImpl.calculateTMB(height, weight, age);
                System.out.println("Your TMB is: " + tmb);

                System.out.println("If you want to gain weight, you need to eat more,  an example of what you should eat is: ");
                System.out.println(listaComidasSubirPeso.keySet() + " " + listaComidasSubirPeso.values() + " calories, " + "total calories: " + totalCalorias + " calories");
                
                System.out.println("If you want to lose weight, you need to eat less,  an example of what you should eat is: ");
                System.out.println(listaComidasBajarPeso.keySet() + " " + listaComidasBajarPeso.values() + " calories, " + "total calories: " + totalCaloriasBajarPeso + " calories");
              
                System.out.println("If you want to mantain weight, you are eating the right amount of calories.");
            }

            bmicalcImpl.shutdown();
        } catch (org.omg.CORBA.ORBPackage.InvalidName | NotFound | CannotProceed | InvalidName ex) {
            Logger.getLogger(BMICalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
