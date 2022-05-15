/*
 * Name:      Jacob Klever 
 *
 * Course:     CS-12, Spring 2021
 *
 * Date:       5/15/2021
 *
 * Filename:   UtilsJK.java
 *
 * Purpose:    add a readString/ readDouble methods and learn to use
 *             new print/message/blank methods
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class UtilsJK {

   //---------------------------------------------------------------------------
   // user input methods, by firstChartype
   //---------------------------------------------------------------------------
    
   // get an int value
   public static int readInt(String prompt, boolean guiMode) {
    
      // declare firstChar and objects
      Scanner input;
      String str;
      String garbage;
      boolean failed = true;
      int firstInt = 0;
      
      // logic branch for scanner or gui
      if (guiMode) {
      
         //type-safe check int value from input
         while (failed == true) {
         
            //test for bad inputs here
            try {
            
               str = JOptionPane.showInputDialog(null, prompt);
               firstInt = Integer.parseInt(str);
               failed = false;
               
            }// end try
            
            //code is run if error is found
            catch(NumberFormatException nfe){
            }
               
         }//end while
         
      }// end if
         
      // prompt for an input int value using Scanner mode, for guiMode=false case
      else {
      
         input = new Scanner(System.in);
         System.out.print(prompt);
         
         //type-safe check int value from input
         while( !input.hasNextInt() ) {
         
            //throw away bad input
            garbage = input.nextLine();
            System.out.print(prompt);
            
         }// end while
         
         firstInt = input.nextInt();
         
      }// end else
      
      // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
      return firstInt; 
    
   }// end readInt()
   
   
   // get double value
   public static double readDouble(String prompt, boolean guiMode) {
    
      // declare firstChar and objects
      Scanner input;
      String str;
      String garbage;
      boolean failed = true;
      double firstDouble = 0.0;
      
      // logic branch for scanner or gui
      if (guiMode) {
      
         //type-safe check int value from input
         while (failed == true) {
         
            //test for bad inputs here
            try {
            
               str = JOptionPane.showInputDialog(null, prompt);
               firstDouble = Double.parseDouble(str);
               failed = false;
               
            }
            
            //code is run if error is found
            catch(NumberFormatException nfe){
            }
               
         }//end while
         
      }// end if
         
      // prompt for an input int value using Scanner mode, for guiMode=false case
      else {
      
         input = new Scanner(System.in);
         System.out.print(prompt);
         
         //type-safe check int value from input
         while( !input.hasNextDouble() ) {
         
            //throw away bad input
            garbage = input.nextLine();
            System.out.print(prompt);
            
         }
         
         firstDouble = input.nextDouble();
         
      }
      
      // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
      return firstDouble; 
    
   }// end readDouble()
   
   
   // get a Char value
   public static char readChar(String prompt, boolean guiMode) {
    
      // declare data and objects
      Scanner input;
      char firstChar;
      String garbage;
      boolean flag = true;
    
      // logic branch for scanner or gui
      if (guiMode) {
         firstChar = JOptionPane.showInputDialog(null, prompt).charAt(0);
         
      }
         
      // prompt for an input int value using Scanner mode, for guiMode=false case
      else {
         input = new Scanner(System.in);
         System.out.print(prompt);
         firstChar = input.nextLine().charAt(0);
         
      }
      
      // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
      return firstChar;  
       
   }//end readChar  
   
   
   // get a String value
   public static String readString(String prompt, boolean guiMode) {
    
      // declare data and objects
      Scanner input;
      String firstString;
      String garbage;
      boolean flag = true;
    
      // logic branch for scanner or gui
      if (guiMode) {
         firstString = JOptionPane.showInputDialog(null, prompt);
         
      }
         
      // prompt for an input int value using Scanner mode, for guiMode=false case
      else {
         input = new Scanner(System.in);
         System.out.print(prompt);
         firstString = input.nextLine();
         
      }
      
      // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
      return firstString;  
       
   }//end readString 

    
   //---------------------------------------------------------------------------
   // age-related methods
   //---------------------------------------------------------------------------
    
   // returns the age as of some REFERENCE date (2-input overloaded form, MODS NEEDED)
   public static int getAge(CS12Date dateBd, CS12Date dateRef) {
      
      int age = -1; // starting default value
      int bdMonth = dateBd.getMonth();
      int bdDay = dateBd.getDay();
      int bdYear = dateBd.getYear();
      int refMonth = dateRef.getMonth();
      int refDay = dateRef.getDay();
      int refYear = dateRef.getYear();       
        
      // first, check for all possible FUTURE birthdates
        
      // case 1: check for future bithday years
      if (bdYear > refYear) {
         age = -1;
         //System.out.println("\nError: provided birthdate " + dateBd + " is after reference date " + dateRef + ": age = " + age);
      }
                
      // case 2: check for future birth months in the SAME year
      else if((bdMonth > refMonth) && (bdYear == refYear)) {
         age = -1;
         //System.out.println("\nError: provided birthdate " + dateBd + " is after reference date " + dateRef + ": age = " + age);
      }
        
      // case 3: check for future birth days in the SAME month in the SAME year
      else if((bdDay > refDay) && (bdMonth == refMonth) && (bdYear == refYear)) {
         age = -1;
         //System.out.println("\nError: provided birthdate " + dateBd + " is after reference date " + dateRef + ": age = " + age);
      }
        
      // if we get this far, a person has been "born" and has a BD within any 12 month year        
      else {
        
         // case 4: check for birthdays in future months
         if (bdMonth > refMonth) {
            age = (refYear - bdYear) - 1;
         }
            
         // case 5: check for birthdays on future days in the SAME month
         else if ((bdDay > refDay) && (bdMonth == refMonth)) {
            age = (refYear - bdYear) - 1;
         }
            
         // case 6: all other birthdays
         else {
            age = (refYear - bdYear);
         }
        
      }
        
      // return ONE common age here, not multiple return statements above!
      return age;
        
   }
   
   // end 2-input overloaded version
    
   //---------------------------------------------------------------------------
   // date-related methods 
   //---------------------------------------------------------------------------
   
   // returns the age as of TODAY'S date (1-input overloaded form, NO CHANGES NEEDED, DO NOT EDIT)
   public static int getAge(CS12Date dateBd) {
      int age;
      CS12Date dateToday = today();
        
      // calls overloaded version of above method, using TODAY as the reference date
      age = getAge(dateBd, dateToday);  
      return age;
        
   }// end 1-input overloaded version
    
    
   // returns today's date as a CS12Date (NO CHANGES NEEDED, DO NOT EDIT)
   public static CS12Date today() {
    
      // simply return a default date, which is today
      return new CS12Date();
        
   }
   
   //---------------------------------------------------------------------------
   // execution related methods 
   //---------------------------------------------------------------------------  
     
   // pauses execution until any key is pressed, specific prompt
   public static void pause(String message) {
      // ignores any returned value
      UtilsJK.readString(message, false);  //<<====== UNCOMMENT THIS LINE once your own readString() is working
      System.out.println();   // add a blank line
   }
    
   // pauses execution until any key is pressed, generic prompt
   public static void pause() {
      pause("Press <Enter> to continue... ");
   }

   // display related methods --------------------------------------------------

   // prints a specified character N times
   public static void spacer(char ch, int num) {
      // print specified char N times
      for (int i=0; i<num; i++) {
         System.out.print(ch);
      }
      System.out.println();
   }
    
    // prints a default character N times
   public static void spacer(int num) {
      final char SEP_CHAR = '=';   // default separator character
        
      // overloaded call to the other version
      spacer(SEP_CHAR, num);
   }
    
   // prints a default spacer, = x40 wide
   public static void spacer() {
      final char SEP_CHAR = '=';   // default separator character
      final int SEP_NUM = 40;      // default separator width
        
      // overloaded call to the other version
      spacer(SEP_CHAR, SEP_NUM);
   }
   
       
   // print a message with or without surrounding bounding box
   public static void message(String msg, boolean box) {
      // print message w/o boxing
      if (!box) {
         System.out.println(msg);
      }
        
      // prints message WITH boxing, using length of desired message
      else {
         spacer('=', msg.length());
         System.out.println(msg);
         spacer('=', msg.length());
      }
   }
    
   // print one blank line
   public static void blank() {
      System.out.println();
   }
    
   // overloaded version, prints N blank lines
   public static void blank(int num) {
      for (int i=1; i<=num; i++) {
         blank();
      }
   }
   
   // test message printing
   public static void main(String [] args) {
    
      UtilsJK.message("This is a bordered message", true);
      UtilsJK.message("This is a regular message, followed by 1 blank line", false);
      UtilsJK.blank();
      UtilsJK.pause();
        
      UtilsJK.message("One more bordered message", true);
      UtilsJK.message("Another regular message, followed by 2 blank lines", false);
      UtilsJK.blank(2);
        
   } // end main
       
} // end class