/*
 * Name:         Rob Lapkass
 *
 * Course:       CS-12, Spring 2021
 *
 * Date:         4/28/21
 *
 * Filename:     CarTest2.java
 *
 * Purpose:      Original version of Car II test code, with tests in modular format by methods.
 *               User is now able to select specific tests for execution.
 *
 * History:      11/11/20 rev.0 Original release developed for Car II, released ~11/24/20
 *               12/2/20  rev.1 Corrected minor labeling issue on test case A2, want +50 miles 
 *               05/01/21 rev.2 Multiple changes based upon initial Fall 2020 experiences, enabled N3 test (prototype)
 *
 * Instructions: IMPORTANT: *First* make sure there are "stub" methods for ALL 
 *                  new required API method interfaces.
 *               To do this, just append provided "stub" code interfaces into your Car class, 
 *                  at end but before main().
 *               This test code MUST still be able to compile and run. 
 *
 *               void methods (x6) return no value
 *               int methods (x1) can return a 0 for now
 *               double methods (x5) can return a 0.0 for now
 *               String methods (x1) can return some bogus string for now
 *         
 *      ------>  1) Append placeholder methods for ALL 13 *added* API methods (see above)
 *               2) Change CarJK --> CarFL, by doing global find-replace (Ctrl-F)
 *               3) Change UtilsJK --> UtilsFL, by doing global find-replace (Ctrl-F)
 *               4) Compile and make sure there no errors, missing methods, API is satisfied fully
 *               5) Then, run this program, select various options, and carefully inspect results!
 *
 *               Then, check against this test code frequently while writing your code!
 *               This code MUST always compile against the API, so you can
 *               iteratively run it as you develop and test each of the new methods.
 */

public class CarTest2RL {

    // these values are used multiply within this file
    // this is the ONLY reason these are "global" as shown here
    
    public static void main(String [] args) {
   
        // input option and reusable menu
        char option;
        String menu = "-------------------\n" +
                      "Car Testing Options\n" +
                      "-------------------\n" +
                      " 1) Display format tests (KEY TEST) [F]\n" +
                      " 2) Numeric tests (KEY TEST)        [N]\n" +
                      " --------------------------------------\n" +
                      " 3) Derived data nominal data tests [D]\n" +
                      " 4) Actions nominal data tests      [A]\n" +
                      " 5) Update method tests             [U]\n" +
                      " --------------------------------------\n" +
                      " 6) Derived data bad data tests     [X]\n" +
                      " 7) Actions bad data tests          [Z]\n" +
                      " 8) Mutator checking bad data tests [M]\n" +
                      " 9) Constructors bad data tests     [C]\n" +
                      "10) Date data safe transfer tests   [S]\n" +
                      "---------------------------------------\n" + 
                      "Quit                                [Q]\n" +
                      "Enter option: ";
        
        // main program loop
        do  {
        
            // priming read
            option = UtilsJK.readChar(menu, false);
            
            // switch on options from above read
            switch (option) {
            
                // test display methods output formatting
                case 'F':
                case 'f':
                    runDisplayFormatTests();
                    break;
                
                // test detailed numerics
                case 'N':
                case 'n':
                    runNumericsTests();
                    break;
                
                // test derived data methods with nominal data
                case 'D':
                case 'd':
                    runDerivedDataNominalTests();
                    break;
                    
                // test various car actions with nominal data
                case 'A':
                case 'a':
                    runActionNominalTests();
                    break;
                    
                // test update of all instance variables
                case 'U':
                case 'u':
                    runUpdateTests();
                    break;
                
                // test derived data methods with bad data
                case 'X':
                case 'x':
                    runDerivedMethodsBadDataTests();
                    break;
                    
                // test various car actions with bad data
                case 'Z':
                case 'z':
                    runActionBadDataTests();
                    break;
                
                // test mutator data checking with bad data
                case 'M':
                case 'm':
                    runMutatorChecksBadDataTests();
                    break;
                    
                // test constructors with bad data
                case 'C':
                case 'c':
                    runConstructorsBadDataTests();
                    break;
                    
                // test date data safe transfers
                case 'S':
                case 's':
                    runDateDataSafeTests();
                    break;
                    
                // quit program
                case 'Q':
                case 'q':
                    message("\nTest program terminating upon command, later!", false);
                    break;
                                             
                default:
                    // all unrecognized options
                    System.out.println("Unrecognized option " + option + ", please try again\n");
                    break;
                    
            } // end switch
            
        } while ((option != 'Q') && (option != 'q'));         
                 
    } // end main
    
    //========================================================================================

    // test display methods output formatting [F]
    private static void runDisplayFormatTests() {
        // car with nice round numbers
        int year = UtilsJK.today().getYear();
        CarJK test = new CarJK("Honda", "Pilot", (year-2), 50000.0, 10.0, 20.0, 
                                new CS12Date(7, 18, (year-2)), 30000.00);
                             
        define("Car Display Format Tests",
               "checks numerical formatting of toString(), print(), and display()",
               "output formats should closely resemble those shown in sample outputs",
               "toString(), print(), display() format specifiers");
              
        // F1: test toString()
        message("F1: toString(): want to see SPACE-separated data, ONLY 8 instance vars,", false);
        message("odometer: 1 dec. place, 3-place commas", false);
        message("tank level: 3 dec. place / tank size: 1 dec. place", false);
        message("sale price: 2 dec. place, 3-place commas", false);
        blank();
        System.out.println(test);
        blank();
        pause();
        
        // F2: test print()
        message("F2: print(): want to see neatly aligned RHS data,", false);
        message("all instance vars with LABELS and [UNITS], same formats as toString(),", false);
        message("append 7 added derived data accessors with LABELS and [UNITS],", false);
        message("value has 2-dec and 3-pl commas, miles/pct/MPG are 1-dec pl. (5-yr depreciation)", false);
        message("added MPG internal variables shown are optional", false);   
        blank();
        test.print("Case F2: test Car");
        blank();
        pause();
        
        // F3: test display()
        message("F3: display(): want to see 3-line output, same formats as other two", false);
        message("Use your own labeling and verbage, as long as this data present and makes sense:", false);
        message("line 1: status, model year, make, model", false);
        message("line 2: tank size, MPG, odometer", false);
        message("line 3: sale date, sale price, value (8-yr depreciation)", false);
        message("car value should DIFFER from that in print()!", false);
        blank();
        test.display();
        blank();
        pause();
        
    }

    //---------------------------------------------------------------------------
    
    // test detailed numerics [N]
    private static void runNumericsTests() {
        
        // recycled for all tests
        CarJK test;
        int year = UtilsJK.today().getYear();
        
        define("Car Numerics Tests",
               "checks numerical values of several different Cars as described",
               "numerical values should match *exactly* versus published test code outputs",
               "all derived data accessors, depending on which value(s) are off");
        
        // N1: new default car
        test = new CarJK();
        message("N1: want to see exact numerical match to this default Car, extra MPG data optional", false);   
        message("current year, 0 values, today's date, age=0, new status, 25 MPG to start", false);  
        message("IF shown, MPG miles and gallons are both 0.0 to start", false); 
        blank();
        message(test.toString(), false);
        test.print("Case N1: default Car with added data");
        blank();
        message("display() should show same data, EXCEPT 8 yr depreciation:", false);
        blank();
        test.display();
        blank();
        pause();
        
        // N2: used car with nice round numbers
        test = new CarJK("Ford", "Explorer", (year - 1), 50000.0, 20.0, 30.0,
                         new CS12Date(4, 3, (year - 1)), 50000.0);
        test.drive(100, 20.0);
        message("N2: want to see exact numerical match to this used Car, extra MPG data optional", false);   
        message("1 yr old, depreciated to $40K, 50% tank, 50K miles", false);
        message("start at 50K miles, then driving 100 miles @ 20 MPG", false);
        blank();
        message(test.toString(), false);
        test.print("Case N2: 1 yr old Car after some driving");
        blank();
        message("display() should show same data, EXCEPT 8 yr depreciation:", false);
        blank();
        test.display();
        blank();
        pause();
        
        // N3: prototype car, nice round numbers
        //skip testing prototype car for now, trips lots of age errors... next time?      
        test = new CarJK("DeLorean", "Time Machine", (year + 4), 5.0, 0.0, 0.0,
                         new CS12Date(7, 18, (year + 4)), 1000000.00);
        message("N3: want to see exact match to this future car, extra MPG data optional", false);
        message("future car, no depreciation, flux capacitor needs no fuel", false);
        blank();
        message(test.toString(), false);
        test.print("Case N3: future car, negative age, prototype, 0% fuel");
        blank();
        message("display() should show same data, EXCEPT 8 yr depreciation:", false);
        blank();
        test.display();
        blank();
        pause();
        
    }
    
    //---------------------------------------------------------------------------
    
    // test derived data methods with nominal data [D]
    private static void runDerivedDataNominalTests() {
    
        int year = UtilsJK.today().getYear();
        CarJK test = new CarJK("Honda", "Pilot", (year-2), 50000.0, 10.0, 20.0, 
                                new CS12Date(7, 18, (year-2)), 30000.00);
                                
        define("Car Derived Data Nominal Tests",
               "checks new get methods using nominal data",
               "methods named per API, correct standalone results",
               "all derived data accessors, depending on which value(s) are off");
        
        // D1: display outputs for all derived data accessors alone
        test.print("Case D1: starting Car");
        blank();
        pause();
        
        message("and now the added get methods alone (both inputs for getValue):", false);
        message("getAge() alone:        " + test.getAge(), false);
        message("getStatus() alone:     " + test.getStatus(), false);
        message("getValue(5) alone:     " + test.getValue(5), false);
        message("getValue(8) alone:     " + test.getValue(8), false);
        message("getFuelPct() alone:    " + test.getFuelPct(), false);
        message("getFullRange() alone:  " + test.getFullRange(), false);
        message("getTripRange() alone:  " + test.getTripRange(), false);
        message("getMPG() alone:        " + test.getMPG(), false);
        blank();
        pause();       
        
    }
    
    //---------------------------------------------------------------------------
    
    // test various car actions with nominal data [A]
    private static void runActionNominalTests() {
    
        CarJK test;
        int year = UtilsJK.today().getYear();
        
        define("Car Action Nominal Tests",
               "check car actions using normal, correct data",
               "car actions increment miles, decrement gas, and change other values correspondingly",
               "drive(), fuel(), and sell() methods");
               
        // A0: drive(m) from known starting point
        test = new CarJK();
        test.setYear(year-1);
        test.setTankSize(20);
        test.setTankLevel(20);
        test.setSalePrice(50000.00);
        test.setSaleDate(new CS12Date(7, 18, year-1)); 
        test.print("Case A0: start with a full tank and no miles");
        blank();
        pause();
        
        // A1: drive car
        message("A1: drive car 100 miles @ default 25 MPG, +100 miles, -4 gals, 80% fuel,", false);
        message("range down, MPG still 25", false);
        blank();
        test.drive(100.0);
        test.print("Case A1: after first drive:");
        blank();
        pause();       
        
        // A2: drive(m, g) from known starting point
        message("A2: next, drive car 50 miles @ 50 MPG (downhill w/tail wind?)", false);
        message("ranges UP as MPG up, +50 miles, -1 gal, MPG up to 30", false);
        blank();
        test.drive(50.0, 50.0);
        test.print("Case A2: after second drive:");
        blank();
        pause();
        
        // A3: fuel(g) from known starting point
        message("A3: refuel car, fill back up, 100% full, ranges and MPG same", false);
        message("note that travel miles and gallons now reset", false);
        blank();
        test.fuel(5.000);
        test.print("Case A3: after fill back up the 5 gals burned");
        blank();
        pause();
        
        // A4: sell from know starting point
        message("A4: now, sell this car for 40K, price/date/value change, all else stays same", false);
        message("list car for sale first, then sell on Christmas Day", false);
        blank();
        test.display();
        blank();
        test.sell(40000.00, new CS12Date(12, 25, year));
        test.print("Case A4: sell car for $40K");
        blank();
        pause();
        
    }
    
    //---------------------------------------------------------------------------
    
    // test update of all instance variables [U]
    private static void runUpdateTests() {
    
        CarJK test = new CarJK();
                               
        define("Car Update Tests",
               "show default car, update() it, show new prompted values",
               "all prompted values are reflected in updated car",
               "update(), regular and prompting mutators");
        blank();
        message("be sure to run this 2x: once with tank level < tank size,", false);
        message("once with tank level > tank size, to trip error message(s)", false);
        blank();
        
        // U1: test all updates, start with known nominal values
        // starting car
        test.print("Case U1: default starting values:");
        blank();
        test.update(false);
        blank();
        
        // updated car
        test.print("updated values, make sure all match your inputs");
        blank();
        pause();
     }
    
    //---------------------------------------------------------------------------
    
    // test derived data methods with bad data [X]
    private static void runDerivedMethodsBadDataTests() {
    
        CarJK test = new CarJK();
        int year = test.getSaleDate().getYear();
        
        define("Car Derived Data Methods Bad Data Tests",
               "check derived data accessors with bad data situations",
               "error messages generated as indicated, object remains unchanged",
               "the various added get methods as noted");
        blank();
        
        // make the car old, give it a round number price
        test.setYear(year - 10);
        test.setSaleDate(new CS12Date(1, 1, year-10));
        test.setSalePrice(50000.00);
        test.print("Case X* starting car");
        blank();
        pause();

        // X1: test fully depreciated value limited to 0.0 (silent)            
        message("X1: note fully depreciated car, value limited to 0.0 (silent)", false);
        blank();

        // X2: test divide by zero on percent full tank (silent)   
        message("X2: note divide by zero on fuel pct full, pct full limited to 0.0 (silent)", false);   
        blank();
        
        // X3: test divide by zero on MPG calculation (silent)
        message("X3: note 0 miles current tank, MPG uses last value (silent)", false);
        blank();
        pause();
        
        // X4: test negative input to getValue()
        message("X4: negative input to getValue() should show error", false);
        test.getValue(-1);
        blank();
        
        // X5: test bad years to getValue() only one with input    
        message("X5: non-5 or non-8 depreciation years should show error", false);
        test.getValue(6);
        blank();
        pause();
        
        // object unchanged
        test.print("original object is unchanged");
        blank();
        pause();
        
    }

    //---------------------------------------------------------------------------

    // test various car actions with bad data [Z]
    private static void runActionBadDataTests() {

        CarJK test = new CarJK();
        test.setTankSize(20.0);
        test.setTankLevel(10.0);
        test.setSalePrice(50000.00);

        define("Car Action Bad Data Tests",
               "check car actions with bad data or actions that can't happen",
               "each action should trigger an error message, leave object unchanged",
               "both drive() methods, fuel(), sell()");
               
        test.print("Case Z: starting state of car");
        blank();
        pause();
                
        // Z1: check negative inputs to drive, fuel, sell
        message("Z1: test negative inputs to actions, each triggers error, 5 errors total", false);
        test.drive(-100, 50.0);
        test.drive(100, -50.0);
        test.drive(-100);
        test.fuel(-10.000);
        test.sell(-40000.00, new CS12Date());
        blank();
        pause();
        
        // Z2: check not enough gas to drive for both drive() methods
        message("Z2: test not enough gas to take trip, each method, 2 errors total", false);
        test.drive(400, 30.0);
        test.drive(300);
        blank();
        pause();
        
        // Z3: check exceeding tank capacity for fuel()
        message("Z3: test trying to fill with too much gas, 1 error message", false);
        test.fuel(12.000);
        blank();
        pause();
        
        
        // Z4: check positive sale price for sell
        message("Z4: test that sale price must be positive, 1 error message", false);
        test.sell(-40000.00, new CS12Date());
        blank();
        pause();
        
        // Z5 check that a zero sale price IS ok (donation)
        message("Z5: test that a zero sales price (donation) IS ok, no error", false);
        test.sell(0.00, new CS12Date());
        blank();
        test.print("Case Z: final car state unchanged EXCEPT FOR donated price of 0 and value");
        blank();
        pause();
    }

    //---------------------------------------------------------------------------

    // test mutator data checking with bad data [M]
    private static void runMutatorChecksBadDataTests() {
    
        CarJK test;
        int year = UtilsJK.today().getYear();
        
        define("Car Mutator Bad Data Tests",
               "check that mutators reject bad data per program spec",
               "want to see individual error msgs for each test case as noted",
               "the various set...() mutator checks");
        
        test = new CarJK("Toyota", "Tundra", year, 10000.0, 15.0, 30.0,
                         new CS12Date(2, 1, year), 40000.00);
        
        test.print("Case M*: starting state of car");
        blank();
        pause();
                               
        // M1: check year >= limit
        message("M1: test year less that limit, error msg expected", false);
        test.setYear(1899);
        blank();
        
        // M2: check odometer >= 0.0
        message("M2: odometer with negative value, error msg expected", false);
        test.setOdometer(-100.0);
        blank();
        
        // M3: check tank level >= 0.0
        message("M3: tank level with negative value, error msg expected", false);
        test.setTankLevel(-15.0);
        blank();
        
        // M4: check tank size >= 0.0
        message("M4: tank size with negative value, error msg expected", false);
        test.setTankSize(-30.0);
        blank();
        
        // M5: check sale price >= 0.0
        message("M5: sale price with negative value, error msg expected", false);
        test.setSalePrice(-40000.0);
        blank();
        
        // M6: try to change tank level > tank size
        message("M6: can't set tank level above tank size, error msg expected", false);
        test.setTankLevel(40.0);
        blank();
        
        // M7: try to change tank size < tank level
        message("M7: can't set tank size below tank level, error msg expected", false);
        test.setTankSize(10.0);
        blank();
        
        // M8: can't reset odometer down once > 0
        message("M8: can't lower odometer once set > 0, that's illegal! error msg expected", false);
        test.setOdometer(9999.0);
        blank();
        pause();
        
        test.print("in the end, car is left unchanged by all above sets");
        pause();
        
    }

    //---------------------------------------------------------------------------

    // test constructors with bad data [C]
    private static void runConstructorsBadDataTests() {
    
        CarJK test;
        
        define("Car Constructor Bad Data Tests",
               "check that when presented with bad data, mutators reject changes and don't permit garbage data",
               "default values for all mutator forms",
               "use of checking mutators inside full and 2 overloaded constructors");
               
        // C1: try to set up full constructor with neg info, should trip errors, no changes
        message("C1: test full constructor, should see 5 error msgs and default numbers", false);
        test = new CarJK("Ford", "Explorer", 1899, -10000.0, -30.0, -30.0, new CS12Date(), -50000.00);
        test.print("Case C1: full constructor, no changes from numeric defaults");
        pause();
        
        // C2: try to set up make/model/year constructor with neg info, should trip errors, no changes
        message("C2: test make/model/year constructor, should see 1 error msg and default numbers", false);
        test = new CarJK("Ford", "Explorer", 1899);
        test.print("Case C2: make/model/year constructors, no changes from numeric defaults");
        pause();
        
        // C3: try to set up make/model/odometer constructor with neg info, should trip errors, no changes
        message("C3: test make/model/odometer constructor, should see 1 error msg and default numbers", false);
        test = new CarJK("Ford", "Explorer", -50000.0);
        test.print("Case C3: make/model/odometer constructors, no changes from numeric defaults");
        pause();
        
        // TODO: do we need to repeat all mutator checks here too??
    }

    //---------------------------------------------------------------------------

    // test date data safe transfers [S]
    private static void runDateDataSafeTests() {
    
        CarJK test = new CarJK();
        CS12Date tempDate;
        int numDays = 3;
        int year = UtilsJK.today().getYear();
        CS12Date MEAS_DATE = new CS12Date(11, 1, year);
        
        define("Car Date Date Safe Tests",
               "show that sale date is properly isolated from external changes, as noted",
               "instance variable sale date should NOT be changing via external date used to set/get it",
               "setSaleDate() and getSaleDate() are using true date copies, setSaleDate() used in full constructor");
        
        // S1: check set/get on date via an object  
        test = new CarJK("Sierra", "Wolverine", year, 5000.0, 15.0, 30.0, MEAS_DATE, 40000.00);      
        test.print("Case S1: Starting state of test car, note the date");  
        pause();

        // extract sale date
        message("getSaleDate() should return us a copy of the sale date: ", false);
        tempDate = test.getSaleDate();
        System.out.println(tempDate);
        pause();
      
        // advanced extracted date
        message("Advance that external date copy by " + numDays + " days: ", false);
        tempDate.laterDate(numDays);
        System.out.println(tempDate);
        pause();
      
        // should not affect date internal to object
        test.print("But the Car object sale date should be unchanged:");
        pause();
      
        // advance extracted data again
        message("Advance the external date copy by " + numDays + " more days to: ", false);
        tempDate.laterDate(numDays);
        System.out.println(tempDate);
        pause();
      
        // update object with the external date
        test.setSaleDate(tempDate);
        test.print("Update the Car object using that sale date and setSaleDate():");
        pause();
      
        // advance external date some more
        message("Now update the external date copy to: ", false);
        tempDate.laterDate(numDays);
        System.out.println(tempDate);
        pause();
      
        // should not affect date internal to object
        test.print("But the Car object sale date should not be changed:");
        pause();

        // S2: check set/get via full constructor
        message("S2: Test data safe transfer on sale date, using full constructor...", true);
        blank();
        tempDate.setYear(MEAS_DATE.getYear());
        tempDate.setMonth(MEAS_DATE.getMonth());
        tempDate.setDay(MEAS_DATE.getDay());
        message("Case S2: Create a new Car using this external date: " + tempDate, false);
        test = new CarJK("Sierra", "Wolverine", year, 5000.0, 15.0, 30.0, tempDate, 40000.00);
        test.print("Starting state of test Car, note the sale date");  
        pause();
      
        // update external date, but Car sale date should be unchanged
        tempDate.laterDate(numDays);
        message("Update the external date copy by " + numDays + " more days to: " + tempDate, false);
        test.print("But the Car object sale date should be unchanged:");
        pause();

    }

    //---------------------------------------------------------------------------
       
    // test related methods ---------------------------
    
    // display a standard formatted title and purpose of a test, and what to check if it doesn't pass
    private static void define(String title, String purpose, String expected, String check) {
        UtilsJK.blank();
        UtilsJK.message(title, true);
        UtilsJK.message("Test purpose:        " + purpose, false);
        UtilsJK.message("Expected result:     " + expected, false);
        UtilsJK.message("If it fails, check:  " + check, false);
        UtilsJK.blank();
        UtilsJK.pause();
    }
    
    // this is simply a wrapper shortcut to YOUR OWN version
    private static void message(String msg, boolean mode) {
        UtilsJK.message(msg, mode);
    }
    
    // this is simply a wrapper shortcut to YOUR OWN version
    private static void blank() {
        UtilsJK.blank();
    } 
    
    // this is simply a wrapper shortcut to YOUR OWN version
    private static void pause() {
        UtilsJK.pause();
    } 
   

} // end class