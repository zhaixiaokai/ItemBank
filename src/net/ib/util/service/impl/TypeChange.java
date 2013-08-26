package net.ib.util.service.impl;
import java.sql.Date;
public class TypeChange {
   public TypeChange() {
   }
   //change the string type to the int type
   public    int stringToInt(String intstr)
   {
     Integer integer;
     integer = Integer.valueOf(intstr);
     return integer.intValue();
   }
   //change int type to the string type
   public  String intToString(int value)
   {
     Integer integer = new Integer(value);
     return integer.toString();
   }
   //change the string type to the float type
   public    float stringToFloat(String floatstr)
   {
     Float floatee;
     floatee = Float.valueOf(floatstr);
     return floatee.floatValue();
   }
   //change the float type to the string type
   public  String floatToString(float value)
   {
     Float floatee = new Float(value);
     return floatee.toString();
   }
   //change the string type to the sqlDate type
   public  java.sql.Date stringToDate(String dateStr)
   {
     return   java.sql.Date.valueOf(dateStr);
   }
   //change the sqlDate type to the string type
   public  String dateToString(java.sql.Date datee)
   {
     return datee.toString();
   }
}