package BMICalculatorApp;


/**
* BMICalculatorApp/BMICalculatorOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bmicalculator.idl
* Saturday, February 25, 2023 12:30:22 PM CET
*/

public interface BMICalculatorOperations 
{
  double calculateBMI (double height, double weight);
  double calculateTMB (double height, double weight, double age);
  double calculateTMBf (double height, double weight, double age);
} // interface BMICalculatorOperations
