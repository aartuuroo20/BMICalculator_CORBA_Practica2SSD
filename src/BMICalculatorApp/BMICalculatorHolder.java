package BMICalculatorApp;

/**
* BMICalculatorApp/BMICalculatorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bmicalculator.idl
* Saturday, February 25, 2023 12:52:17 PM CET
*/

public final class BMICalculatorHolder implements org.omg.CORBA.portable.Streamable
{
  public BMICalculatorApp.BMICalculator value = null;

  public BMICalculatorHolder ()
  {
  }

  public BMICalculatorHolder (BMICalculatorApp.BMICalculator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BMICalculatorApp.BMICalculatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BMICalculatorApp.BMICalculatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BMICalculatorApp.BMICalculatorHelper.type ();
  }

}
