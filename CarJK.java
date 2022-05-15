/*
* Name:     Jacob Klever
*
* Course:   CS-12, Spring 2021
*
* Date:     05/15/2021
*
* Filename: CarJK.java
*
* Purpose:  Learn how to create a class and 
* impliment safe data transfer/error checking
*
*/

public class CarJK {

   // instance variables
   private String make;
   private String model;
   private int year;
   private double odometer;
   private double tankLevel;
   private double tankSize;
   private CS12Date saleDate;
   private double salePrice;
   
   // constants ---------------------------------
   
   private final double TOL = 0.0001;
    
   // other class data -------------------------- 
   double tripMiles;
   double tripGallons;
   double tripMPG;
    
   // data above here 
    
   //============================================
    
   // methods below here
    
    
   // CONSTRUCTORS ------------------------------
   
   
   // default constructor
   public CarJK() {
      make = "Sierra";
      model = "Wolverine";
      year = UtilsJK.today().getYear();
      odometer = 0.0;
      tankLevel = 0.0;
      tankSize = 0.0;
      saleDate = UtilsJK.today();
      salePrice = 0.0;
      
      // added vars are initilized
      tripMiles = 0.0;
      tripGallons = 0.0;
      tripMPG = 25.0;
   }
   
   // full constructor
   public CarJK(String make, String model, int year,
                double odometer,double tankLevel, 
                double tankSize, CS12Date saleDate, 
                double salePrice) {
      this(); 
      setMake(make);
      setModel(model);
      setYear(year); 
      setOdometer(odometer);
      // tank size must be set first. Need a bucket FIRST in order to fill it
      setTankSize(tankSize);
      setTankLevel(tankLevel);
      setSaleDate(saleDate);
      setSalePrice(salePrice);       
   }
   
   // make/model/year constructor
   public CarJK(String make, String model, int year) {
   
      // initializes default values
      this();
      
      // updates default values
      setMake(make);
      setModel(model);
      setYear(year);
   
   }
   
   // make/model/odometer constructor
   public CarJK(String make, String model, double odometer) {
   
      // initializes default values
      this();
      
      // updates default values
      setMake(make);
      setModel(model);
      setOdometer(odometer);
   
   }
   
   // DISPLAY METHODS ------------------------------
   
   //=================================================
   // String version of objet data
   public String toString() {
      return String.format("%s  %s  %d  %,.1f  %.3f  %.1f  %s  %,.2f", make, model
                           , year, odometer, tankLevel, tankSize, saleDate, salePrice);
             
   }
   
   // formatted version of object data
   public void print() {
      System.out.printf("%-40s%s%n","Make:", make);
      System.out.printf("%-40s%s%n","Model:", model);
      System.out.printf("%-40s%d%n","Year:", year);
      System.out.printf("%-40s%,.1f%n","Odometer [miles]:", odometer);
      System.out.printf("%-40s%.3f%n","tank level [gals]:" , tankLevel);
      System.out.printf("%-40s%.1f%n","tank size [gals]:", tankSize);
      System.out.printf("%-40s%1s%n","last sale date:", getSaleDate());
      System.out.printf("%-40s%,.2f%n","last sale price [$]:", salePrice);
      System.out.println("-------------------------------------------------");
      System.out.printf("%-40s%d%n", "age [yrs]:", getAge());
      System.out.printf("%-40s%s%n","status:", getStatus());
      System.out.printf("%-40s%,.2f%n", "depreciated value [$]:", getValue(5));
      System.out.printf("%-40s%.1f%n", "remaining fuel [%]:", getFuelPct());
      System.out.printf("%-40s%.1f%n", "full travel range [miles]:", getFullRange());
      System.out.printf("%-40s%.1f%n", "remaining travel range [miles]:", getTripRange());
      System.out.printf("%-40s%.1f%n", "current MPG [miles/gals]:", getMPG());
      System.out.println("-------------------------------------------------");
      System.out.printf("%-40s%.1f%n", "travel since last fill-up [miles]:", tripMiles);
      System.out.printf("%-40s%.3f%n", "fuel since last fill-up [gals]:", tripGallons);
   }
   
   // overloaded version of print, accepts a label string
   public void print(String message) {
      UtilsJK.message(message, true);
      print();
      
   }
   
   // display for sale 
   public void display() {
      System.out.printf("FOR SALE: %s %d %s %s%n",getStatus() , year, make, model);
      System.out.printf("%.1f gal tank %.1f MPG %,.2f miles%n", tankSize, getMPG() , odometer); 
      System.out.printf("last sold %s $%,.2f est. value $%,.2f%n", saleDate, salePrice, getValue(8));
   
   }
   
   // ACCESSORS / MUTATORS ------------------------------
   
   //=================================================
   // make accessor
   public String getMake() {
      return make;
   }
   
   // make mutator
   public void setMake(String make) {
      this.make = make;
   }
   
   // overloaded make mutator version, prompt for data
   public void setMake(boolean inputMode) {
      String data = UtilsJK.readString("Enter car make > ", inputMode);
      setMake(data);
   }
   
   //=================================================
   // model accessor
   public String getModel() {
      return model;
   }
   
   // model mutator
   public void setModel(String model) {
      this.model = model;
   }
   
   // overloaded model mutator version, prompt for data
   public void setModel(boolean inputMode) {
      String data = UtilsJK.readString("Enter car model > ", inputMode);
      setModel(data);
   }
   
   //=================================================
   // year accessor
   public int getYear() {
      return year;
   }
   
   // year mutator
   public void setYear(int year) {
      
      // check if year is out of range
      if(year <= 1900) {
         System.out.printf("ERROR: setYear(): year %d must be >= 1900, model year left unchanged%n", year);
      }
      
      // sets default year
      else {
         this.year = year;
      }
   }
   
   // overloaded year mutator version, prompt for data
   public void setYear(boolean inputMode) {
      int year;
      
      year = UtilsJK.readInt("Enter model year > ", inputMode);
      setYear(year);
   }
   
   //=================================================
   // odometer accessor
   public double getOdometer() {
      return odometer;
   }
   
   // odometer mutator
   public void setOdometer(double odometer) {
   
      // check for negative input
      if(odometer < 0.0) {
         System.out.printf("ERROR: setOdometer(): can't set odometer to negative value %.1f, odometer value is unchanged%n", odometer);
      }
      
      // can't lower odometer past current miles
      else if(odometer < getOdometer()) {
         System.out.printf("ERROR: setOdometer(): can't move odometer back to %.1f that's illegal!, odometer value is unchanged%n", odometer);
      }
      
      // set default odometer
      else {
         this.odometer = odometer;
      }
      
   }
   
   // overloaded odometer mutator version, prompt for data
   public void setOdometer(boolean inputMode) {
      double data = UtilsJK.readDouble("Enter odometer [miles]: ", inputMode);
      setOdometer(data);
   }
   
   //=================================================
   // tankLevel accessor
   public double getTankLevel() {
      return tankLevel;
   }
   
   // tankLevel mutator
   public void setTankLevel(double tankLevel) {
      
      //check for out-of-bounds inputs
      if(tankLevel > tankSize) {
      
         System.out.printf("ERROR: setTankLevel: tank level %.1f must be <= tank size %.1f, tank level is unchanged%n", tankLevel, tankSize);
      } 
      
      // check for negative input
      else if(tankLevel < 0.0) {
         System.out.printf("ERROR: setTankLevel(): can't set tank level to negative value %.1f, tank level is unchanged%n", tankLevel);
      }
      
      // sets default tankLevel
      else {
         this.tankLevel = tankLevel;
      }
   }
   
   // overloaded tankLevel mutator version, prompt for data
   public void setTankLevel(boolean inputMode) {
      double data = UtilsJK.readDouble("Enter current tank level [gals]: ", inputMode);
      setTankLevel(data);
   }
   
   //=================================================
   // tankSize accessor
   public double getTankSize() {
      return tankSize;
   }
   
   // tankSize mutator
   public void setTankSize(double tankSize) {
      
      // check for negative input
      if(tankSize < 0.0){
         System.out.printf("ERROR: setTankSize(): can't set tank size to negative value %.1f, tank size is unchanged%n", tankSize);
      }
      
      // check tankSize is greater than tankLevel
      else if(tankLevel > tankSize) {
         System.out.printf("ERROR: setTankSize(): tank size %.1f must be >= tank level %.1f, tank size is unchanged%n", tankSize, tankLevel);
      }
      
      // sets default tankSize
      else {
         this.tankSize = tankSize;
      }
   }
   
   // overloaded tankSize mutator version, prompt for data
   public void setTankSize(boolean inputMode) {
      double data = UtilsJK.readDouble("Enter total tank size [gals]: ", inputMode);
      setTankSize(data);
   }
   
   //=================================================
   // saleDate accessor
   
  // go over user class data safety for how to impliment date checking
  
   public CS12Date getSaleDate() {
      
      // data safe transfer of getSaleDate
      CS12Date tempSaleDate = new CS12Date();
      
      tempSaleDate.setMonth(this.saleDate.getMonth());
      tempSaleDate.setDay(this.saleDate.getDay());
      tempSaleDate.setYear(this.saleDate.getYear());
      
      return tempSaleDate;
   }
   
   // saleDate mutator
   public void setSaleDate(CS12Date saleDate) {
      
      // data safe transfer of setSaleDate
      (this.saleDate).setMonth(saleDate.getMonth());
      (this.saleDate).setDay(saleDate.getDay());
      (this.saleDate).setYear(saleDate.getYear());
     
   }
   
   // overloaded saleDate mutator version, prompt for data
   public void setSaleDate(boolean inputMode) {
      
      // data safe transfer of user inputs
      saleDate.setMonth(UtilsJK.readInt("Enter month of sale date: ", inputMode));
      saleDate.setDay(UtilsJK.readInt("Enter day of sale date: ", inputMode));
      saleDate.setYear(UtilsJK.readInt("Enter year of sale date: ", inputMode));
   
   }
   
   //=================================================
   // salePrice accessor
   public double getSalePrice() {
      return salePrice;
   }
   
   // salePrice mutator
   public void setSalePrice(double salePrice) {
      
      // check for negative inputs
      if(salePrice < 0.0) {
         System.out.printf("ERROR: setSalePrice(): sale price can't be %.1f must be >= 0.00, sale price is unchanged%n", salePrice);
      }
      
      // set value back to default
      else {
         this.salePrice = salePrice;
      }
   }
   
   // overloaded salePrice mutator version, prompt for data
   public void setSalePrice(boolean inputMode) {
      double data = UtilsJK.readDouble("Enter sale price [$]: ", inputMode);
      setSalePrice(data);
   }
   
   
   // EQUIVALANCE METHODS ------------------------------
 
   //================================================= 
   // standard interface for equals()
   public boolean equals(Object obj) {
      
      CarJK car;
      boolean status = true;
      
      // check objs are of the same type
      if( !(obj instanceof CarJK) ) {
         //stop no need to check any further
         status = false;
      }// end if
      
      else {
         // typecast  into intended object type
         car = (CarJK) obj;
         
         // check field-by-field on ALL fields can be expanded using &&
         if ( (car.getMake().equals(this.make)) &&
              (car.getModel().equals(this.model)) &&
              (car.getYear() == this.year) &&
              (Math.abs(car.getYear()-this.year) <= TOL) &&
              (Math.abs(car.getOdometer() - this.odometer) <= TOL) &&
              (Math.abs(car.getTankLevel() - this.tankLevel) <= TOL) &&
              (Math.abs(car.getTankSize() - this.tankSize) <= TOL) &&
              (car.getSaleDate().equals(this.saleDate)) &&
              (Math.abs(car.getSalePrice() - this.salePrice) <= TOL))   {
              
            status = true;
         }// end if
         
         // return false if values don't pass the && logic
         else {
            status = false;
         }
         
      }// end else
      
      return status;
      
   } // end equals
   
   // derived data accessors------------------------------
 
   //=====================================================
   
   // getAge
   public int getAge() {   
      CS12Date age = new CS12Date(1, 1, getYear());
      return UtilsJK.getAge(age);
   }
   
   // getStatus checks if car is new/used/prototype
   public String getStatus() {
      String status;
      
      // checks if car is new
      if(getAge() == 0) {
         status = "New";
      }
      
      // checks if car release date is in the future
      else if(getAge() < 0) {
         status = "Prototype";
      }
      
      // check if car is used
      else {
         status = "Used";
      }
      
      return status;
   
   } // end getStatus()
   
   
   // getValue checks for errors and calcs depValue
   public double getValue(int numYears) {
      
      double depValue;
      depValue = 0.0; 
      
      //sets value to last sale price if age is negative
      if (numYears < 0) {
         System.out.printf("ERROR: getValue(): depreciation schedule must be positive, can't be %d, no depreciation%n", numYears);
         depValue = getSalePrice();
      }
      
      // set depValue to salePrice if the age is in the future
      else if (getAge() < 0) {
         depValue = getSalePrice();
      }
      
       // check for out-of-bounds years   
      else if((numYears != 8) && (numYears != 5)) {
         System.out.printf("ERROR: getValue: depreciation schedule can't be %d yrs, must be 5 | 8 yrs ONLY, no depreciation%n", numYears);
         depValue = getSalePrice();
      }
      
      // checks is depValue is negative and returns depValue or 0.0 whichever is greater
      else if((depValue < 0.0)) {
       //use math.max to find the greater of two values
         depValue = Math.max(depValue, 0.0);
      }
        
      // catches negative depValue in X test
      else if(getAge() > 8) {
         depValue = depValue;
      }
      
      // calcs depreciation value
      else {
         depValue = getSalePrice() - getAge() * (getSalePrice()/numYears);      
      }
      
      return depValue;
   }
   
   
   // error checking and calcs fuel %
   public double getFuelPct() {
      
      double fuelPct;
      
      // checks if tankSize is greater than zero
      if(tankSize > 0.0) {
         fuelPct = (tankLevel / tankSize) * 100;
      }
      
      // set fuelPct otherwise
      else {
         fuelPct = 0.0;
      }
       
      return fuelPct;
   }
   
   // calcs FuelRange
   public double getFullRange() {
      
      return tankSize * getMPG();
   }
   
   // calcs TripRange
   public double getTripRange() {
   
      return tankLevel * getMPG();
   }
   
   // calcs car MPG
   public double getMPG() {
   
      // checks gallons is positive
      if(tripGallons > 0) {
         tripMPG = tripMiles / tripGallons;
      }
      
      return tripMPG;
   }
   
   
   // utility methods------------------------------
 
   //==============================================
   
   // updates each variable 
   public void update(boolean inputMode) {
      setMake(inputMode);
      setModel(inputMode);
      setYear(inputMode);
      setOdometer(inputMode);
      // tank size must be set first
      setTankSize(inputMode);
      setTankLevel(inputMode);
      setSaleDate(inputMode);
      setSalePrice(inputMode);
   }
   
   // drive given miles at given mpg an error checking
   public void drive(double miles, double mpg) {  
         
      // check if MPG is negative
      if(mpg < 0.0) {
         System.out.printf("ERROR: drive(m,mpg): can't have negative MPG %.1f, no drive taken%n", mpg);
      }
      
      // checks miles if input miles are positive
      else if(miles < 0.0) {
         System.out.printf("ERROR: drive(m,mpg): can't drive negative distance %.1f miles, no drive taken%n", miles);
      }
      
      // checks if drive can be completed with current MPG and fuel level
      else if(tripGallons >= tankLevel) {
         System.out.printf("ERROR: drive(m,g): Can't drive %.1f miles at %.1f MPG with current gas, no drive taken%n", miles, mpg);
      }
      
      //error check enough gas to complete trip use tripRange
      else if(miles > getTripRange()) {
         System.out.printf("ERROR: drive(m,g): can't drive %.1f miles at %.1f MPG with current gas, no drive taken%n", miles, mpg);
      }
      
      // updates that variables
      else {
         odometer += miles; //chng odometer
         tripMiles += miles; //tripMiles
         tripGallons += (miles / mpg);  //tripgal fuel consumed
         tankLevel -= (miles / mpg); //chng tanklevel
      }
   }
   
   // drive overloaded
   public void drive(double miles) {
   
      // check there is enough gas to complete trip use tripRange
      if(miles > getTripRange()) {
         System.out.printf("ERROR: drive(m): not enough gas to drive %.1f miles at current latest MPG, no drive taken%n", miles);
      }
      
      // check for negative input
      else if(miles < 0.0) {
         System.out.printf("ERROR: drive(m): can't drive negative distance %.1f miles, no drive taken%n", miles);
      }
      
      // calls overloaded drive(m,mpg)
      else {
         drive(miles, tripMPG);
      }
   }
   
   // reset variables and error checking
   public void fuel(double gallons) {
   
      // checks if fuel added would exceed tankSize
      if(gallons + tankLevel > tankSize) {
         System.out.printf("ERROR: fuel(): adding %.1f gallons would exceed tank limit, no change to gas level%n", gallons);
      }
      
      // check for negative inputs
      else if(gallons < 0) {
         System.out.printf("ERROR: fuel(): gallons added can't be %.1f, must be > 0.0, no change to gas tank%n", gallons);
      }
      
      // updates variables
      else {
         tankLevel += gallons;  // increment by fuel added
         tripGallons = 0.0; //reset tipgallons to zero hard code
         tripMiles = 0.0; //reset odometer to zero hard code
      }
   }
   
   // sell car at set price and date with error checking
   public void sell(double price, CS12Date date) {
   
      // check for negative price
      if(price < 0.0) {
         System.out.printf("ERROR: sell(): cannot sell for negative price $%.1f, no sale made%n", price);
      }
      
      // update date and price
      else {
         setSaleDate(date); //set saleDate to input date
         setSalePrice(price); // set sale price form getValue()
      }
   }
   
   
   // unit test code
    
   // Test driver for the class methods 
   public static void main (String [] args) {
      
      //=================================================
      // Test 1 creat 4 car objects
      CarJK c1 = new CarJK(); // default constructor
      CarJK c2 = new CarJK("Honda", "Odyssey", 2010, 100000.0, 17.5,
                           20.0, new CS12Date(5,1,2010), 27995.99); // full constructor
                           
      CarJK c3 = new CarJK("Toyota", "Prius", 2018); // make/model/year constructor
      CarJK c4 = new CarJK("Tesla","Model S", 123.4); // make/model/odometer constructor
      
      UtilsJK.message("Test 1: create 4 Car objects, using all 4 constructor forms", true);
      UtilsJK.message("(no visible outputs)\n", false);
      
      //=================================================
      // Test 2 toString
      UtilsJK.message("Test 2: display all created objects - using toString and overloaded print", true);
      UtilsJK.blank();
      
      // default contstructor car1
      System.out.print(c1 + "\n");
      c1.print("Default constructor - car1"); 
      UtilsJK.blank();
      UtilsJK.pause();
      
      // full constructor car2
      System.out.print(c2 + "\n");
      c2.print("full constructor - car2");
      UtilsJK.blank();
      UtilsJK.pause();
      
      // make/model/year constructor
      System.out.print(c3 + "\n");
      c3.print("make/model/year constructor - car3");
      UtilsJK.blank();
      UtilsJK.pause();
      
      // make/model/odometer constructor
      System.out.print(c4 + "\n");
      c4.print("make/model/odometer constructor - car4");
      UtilsJK.blank();
      UtilsJK.pause();
      
      //=================================================
      // Test 3 test accessors
      UtilsJK.message("Test 3: test all accessors - print ONE object and extract each field alone using gets", true);
      UtilsJK.blank();
      UtilsJK.message("Starting car", true);
      
      // print default values 
      System.out.printf("%-40s%s%n","Make:", c2.getMake());
      System.out.printf("%-40s%s%n","Model:", c2.getModel());
      System.out.printf("%-40s%d%n","Year:", c2.getYear());
      System.out.printf("%-40s%,.1f%n","Odometer [miles]:", c2.getOdometer());
      System.out.printf("%-40s%.1f%n","tank level [gals]:" , c2.getTankLevel());
      System.out.printf("%-40s%.1f%n","tank size [gals]:", c2.getTankSize());
      System.out.printf("%-40s%s%n","last sale date:", c2.getSaleDate());
      System.out.printf("%-40s%,.2f%n","last sale price [$]:", c2.getSalePrice());
      UtilsJK.blank();
   
      // test mutators, set new values   
      c2.setMake("DMC");
      c2.setModel("DeLorean");
      c2.setYear(1981);
      c2.setOdometer(6500.2);
      c2.setTankLevel(10.1);    
      c2.setTankSize(13.5); 
      c2.setSaleDate( new CS12Date(6,1,1982));
      c2.setSalePrice(11999.99);
      
      // print individually using accessors
      UtilsJK.message("all instance vars displayed using seperate gets", false);
      UtilsJK.blank();
      
      System.out.printf("%-40s%s%n","Make alone:", c2.getMake());
      System.out.printf("%-40s%s%n","Model alone:", c2.getModel());
      System.out.printf("%-40s%d%n","Year alone:", c2.getYear());
      System.out.printf("%-40s%,.1f%n","Odometer alone:", c2.getOdometer());
      System.out.printf("%-40s%.1f%n","tank level alone:" , c2.getTankLevel());
      System.out.printf("%-40s%.1f%n","tank size alone:", c2.getTankSize());
      System.out.printf("%-40s%s%n","sale date alone:", c2.getSaleDate());
      System.out.printf("%-40s%,.2f%n","sale price alone:", c2.getSalePrice());
      UtilsJK.pause();
      
      // Test 4 test all mutators
      UtilsJK.message("Test 4 test all mutators - print default object befor/after updating each field", true);
      UtilsJK.blank();
      UtilsJK.message("default car as starting point", true);
      c1.print();
      
      UtilsJK.blank();
      UtilsJK.message("all instance var induvidually updated using sets (made-up data)...", false);
      
      // update default values
      c1.setMake("DMC");
      c1.setModel("DeLorean");
      c1.setYear(1981);
      c1.setOdometer(6500.2);
      c1.setTankLevel(10.1);    
      c1.setTankSize(13.5); 
      c1.setSaleDate( new CS12Date(6,1,1982));
      c1.setSalePrice(11999.99);
      
      UtilsJK.blank();
      c1.print("default car updated");
      UtilsJK.pause();
      
      // Test 5 test prompting mutators
      UtilsJK.message("Test 5: test all prompting mutators - print default object befor/after updating each field",true);
      UtilsJK.blank();
      c1.print("starting default car");
      UtilsJK.blank();
      
      // update values from user inputs
      UtilsJK.message("update each field - not this can be throw away data for now!", false);
      c1.setMake(false);
      c1.setModel(false);
      c1.setYear(false);
      c1.setOdometer(false);
      c1.setTankLevel(false);    
      c1.setTankSize(false); 
      c1.setSaleDate(false);
      c1.setSalePrice(false);
      
      UtilsJK.blank();
      c1.print("after all udates");
      UtilsJK.pause();
      
      // Test 6 test equals
      UtilsJK.message("Test 6: test equals - same objs, different objs and different object types",true);
      
      // test against same car - should be true
      System.out.printf("%-40s%b%n","test car1 vs car1:", c1.equals(c1));
      
      // test against different car
      System.out.printf("%-40s%b%n","test car1 vs car2:", c1.equals(c2));
      
      //test against a string
      System.out.printf("%-40s%b%n","test car1 vs any string:", c1.equals(c2.getMake()));
      
      // test against different object
      CS12Date temp = new CS12Date(4,9,2001);
      System.out.printf("%-40s%b%n","test car1 vs any CS12Date:", c1.equals(temp));
      
      UtilsJK.blank();
      UtilsJK.message("End of week 1 testing", true);
            
   } // end main
    
} // end class