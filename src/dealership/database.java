package dealership;

import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * database class stores dealership inventory in the form of Cars objects.
 * 
 * @author Darren Rambaud (d_r273)
 * @author Nathan Easton (nle7)
 * @version 9/20/2016
 */
public class database {
    private static final int SIZE = 3,
                             VIN = 0,
                             MAKE = 1,
                             MODEL = 2,
                             YEAR = 3,
                             MILEAGE = 4,
                             VIN_LENGTH = 5,
                             PRICE = 5;
                             
    private static final String FILE_PATH = ("cars.txt");
    private ArrayList<Car> vehicle_db = new ArrayList<Car>(SIZE);

    /**
     * importVehicleData is an ArrayList method which populates from a specified filepath. The 
     * method throws an exception if the input file cannot be retrieved or utilized.
     * 
     * @return vehicle_db ArrayList variable holding inventory of Car objects
     */
    public ArrayList<Car> importVehicleData() throws IOException {
        Scanner source = null;
        try {
            source = new Scanner(new FileReader(FILE_PATH));
            String line;
            String[] words;

            while (source.hasNextLine()) {
                Car data = new Car();
                line = source.nextLine();
                words = line.split(" ");

                data.setVIN(words[VIN]);
                data.setMake(words[MAKE]);
                data.setModel(words[MODEL]);
                data.setYear(Integer.parseInt(words[YEAR]));
                data.setMileage(Integer.parseInt(words[MILEAGE]));
                data.setPrice(Float.parseFloat(words[PRICE]));
                vehicle_db.add(data);
            }
        }
        catch (IOException ex){
            System.err.print(ex.getMessage());
            System.out.println("\nERROR: \"cars.txt\" does NOT exist!");
        }
        finally {
            if (source != null){
                source.close();
            }
        }
        return vehicle_db;
    }

    /**
     * exportArrayList2File method creates a file, which prints the contents of the database (ArrayList) and 
     * closes the created file. A message is printed to system out following completion.
     */
    public void exportArrayList2File() throws Exception {
        PrintWriter fOut = new PrintWriter(FILE_PATH);

        for (int i = 0; i < vehicle_db.size(); ++i) {
            fOut.println(""+vehicle_db.get(i).getVIN()+" "+vehicle_db.get(i).getMake()+" " +
                    ""+vehicle_db.get(i).getModel()+" "+vehicle_db.get(i).getYear()+" " +
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+""); //FIX ME: Format price 2 Decimal places
        }

        fOut.close();
        System.out.println("\nGoodbye!");
    }

    /**
     *  displayInventory method prints the contents of the inventory stored in the database. If
     *  the inventory is empty, then the user is notified by a message printed to system out.
     */
    public void displayInventory (){
        System.out.print("\n==============================================================================\n");

        if (vehicle_db.isEmpty()){
            System.out.println("The vehicle inventory is empty.");
        }
        else {
            for (int i = 0; i < vehicle_db.size(); ++i) {    
                System.out.println("\n|  "+vehicle_db.get(i).getVIN()+" "+vehicle_db.get(i).getMake()+" " +
                    ""+vehicle_db.get(i).getModel()+" "+vehicle_db.get(i).getYear()+" " +
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+""); //FIX ME: Format price 2 Decimal places
            }
        }
        System.out.print("\n==============================================================================\n");
    }

    /**
     * addNewVehicle method allows a user to manually create a Car object and add it to the database. The method 
     * checks for correct user input for various fields of a Car object.
     */
    public void addNewVehicle(){
        Scanner in = new Scanner(System.in);
        Car temp = new Car();

        int query_int; 
        String query_str; 
        float query_float;
        boolean too_long,
                negative_value;

        do {
            System.out.println("\nPlease enter the 5 character VIN (Vehicle Identification Number) of the vehicle: ");
            query_str = in.nextLine();

            if (query_str.length() != VIN_LENGTH ) { 
                System.out.println("\nERROR: Invalid VIN entered, please try again...\n");
                too_long = true;
            }
            else {
                too_long = false;
            }
        } while (too_long);
        temp.setVIN(query_str.toUpperCase()); //Maintain continuity among user inputs

        System.out.println("\nPlease enter the make of the vehicle (eg, Toyota, Honda, Ford ...): ");
        query_str = in.nextLine();
        temp.setMake(query_str.toUpperCase());//Maintain continuity among user inputs

        System.out.println("\nPlease enter the model of the vehicle (eg, S200, F-150, Yaris ...): ");
        query_str = in.nextLine();
        temp.setModel(query_str.toUpperCase());//Maintain continuity among user inputs

        do {
            System.out.println("\nPlease enter the vehicle's model year (eg, 2016, 2010, 1999, ...): 1");
            query_int = Integer.parseInt(in.nextLine());

            if (query_int < 1900 || query_int > 2017) {
                System.out.println("\nERROR: Invalid model year entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setYear(query_int);

        do {
            System.out.println("\nPlease enter the vehicle's mileage (eg, 50000, 25343, 12345, ...):");
            query_int = Integer.parseInt(in.nextLine());

            if (query_int < 0 || query_int > 500000) {
                System.out.println("\nERROR: Invalid mileage entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setMileage(query_int);

        do {
            System.out.println("\nPlease enter the price of the vehicle (eg, 50000, 69999.99, 12500, ...):");
            query_float = Float.parseFloat(in.nextLine());

            if (query_float <= 0) {
                System.out.println("\nERROR: Invalid vehicle price entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setPrice(query_float);

        vehicle_db.add(temp);
        System.out.println("\nThe vehicle: \n\n(VIN: "+temp.getVIN()+", Make: "+temp.getMake()+", " +
                           "Model: "+temp.getModel()+", Year: "+temp.getYear()+", Mileage: "+temp.getMileage()+", " +
                           "Price: $"+temp.getPrice()+") \n\nhas been successfully added to the inventory list!"); //FIX ME: OUTPUT ALIGNMENT, !!!PRICE IN TWO DECIMAL DIGITS!!!
    }

    /**
     * delByVIN method allows user to seek a Car object by searching for the VIN number and delete 
     * that object from the inventory. The method checks for correct user input format. If the Car 
     * object is found the user is notified of successful removal. Otherwise, the user is informed 
     * if the object is not found nor successfully removed.
     */
    public void delByVIN () {
        Scanner in = new Scanner(System.in);
        String query;
        boolean found = false,
                invalid;

        do {
            displayInventory();
            System.out.println("\nPlease enter the VIN of the vehicle you wish to remove:");
            query = in.nextLine();
            
            if (query.length() != VIN_LENGTH) { 
                System.out.println("\nERROR: Invalid VIN entered, please try again...");
                invalid = true;
            } else {
                invalid = false;
            }
        } while (invalid);

        for (int i = 0; i < vehicle_db.size(); ++i) {
             if (query.equalsIgnoreCase(vehicle_db.get(i).getVIN())){
                 vehicle_db.remove(i);
                 found = true;
                 break;
             }
             else {
                 found = false;
             }
        }

        if (found) {
            System.out.println("\nVehicle has been successfully removed!");
        }
        else {
            System.out.println("\nVehicle not found in the inventory list.");
        }
    }

    /**
     * vehicleSearchByVIN method allows a user to view a Car object within the inventory by searching
     * for a specific VIN number. The method checks for correct user input format. If the object is
     * found, the object is displayed on system out. If the object is not found in the database, 
     * the user is notified.
     */
    public void vehicleSearchByVIN () {
        Scanner in = new Scanner(System.in);
        boolean doesExist = false,
                invalid;
        String query;
        
        do {
            displayInventory();
            System.out.println("\nPlease enter the 5-digit VIN of the vehicle you wish to view:");
            query = in.nextLine();
            
            if (query.length() != VIN_LENGTH) { 
                System.out.println("\nERROR: Invalid VIN format entered, please try again...");
                invalid = true;
            } else {
                invalid = false;
            }
        } while (invalid);

        for (int i = 0; i < vehicle_db.size(); ++i) {
            if (query.equalsIgnoreCase(vehicle_db.get(i).getVIN())){
                System.out.print("\n==============================================================================\n");
                System.out.println("\n|  "+vehicle_db.get(i).getVIN()+" "+vehicle_db.get(i).getMake()+" " +
                    ""+vehicle_db.get(i).getModel()+" "+vehicle_db.get(i).getYear()+" " +
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+""); ////FIX ME: Format price 2 Decimal places
                System.out.print("\n==============================================================================\n");
                doesExist = true;
                break;
             }
        }
        
        if (!doesExist)
            System.out.println("\nSorry, the VIN #"+query+", does not exist in this vehicle inventory.\n");
    }

    /**
     * priceRangeSearch method allows a user to establish a price range, which allows the user to view only Car
     * objects that meet the specified criteria. The method ensures logical price range boundaries. The method 
     * creates an ArrayList 'rangeList' that stores the qualified Car objects. rangeList is printed displaying to 
     * the user desired matches. If there are no matches, the user is notified by a message to system out. 
     */
    public void priceRangeSearch () {
        boolean withinRange = false;
        ArrayList<Car> rangeList = new ArrayList<Car>();
        Scanner sc = new Scanner(System.in);
        int lower,
            higher;
        boolean invalid_range;

        do {
            System.out.println("\nPlease enter the MINIMUM price threshold: ");
            lower = Integer.parseInt(sc.nextLine());

            System.out.println("\nPlease enter the MAXIMUM price threshold: ");
            higher = Integer.parseInt(sc.nextLine());

            if (lower < 0 || higher < 0) {
                invalid_range = true;
                System.out.println("\nYou have entered negative values for the price threshold(s), please try again ...");
            }

            if (lower > higher || higher < lower){
                System.out.println("\nYou have entered an invalid range, please try again...");
                invalid_range = true;
            }
            else {
                invalid_range = false;
            }

        } while (invalid_range);

        for (int i = 0; i < vehicle_db.size(); ++i) {
            if ((vehicle_db.get(i).getPrice() <= higher) && (vehicle_db.get(i).getPrice() >= lower)) {
                rangeList.add(vehicle_db.get(i));
                withinRange = true;
            }
        }
        
        if (!withinRange)
            System.out.println("\nSorry, there are no vehicle matches between price range: $" +lower+ " - $" +higher+ "in our inventory\n");
   
        else{    
        System.out.print("\n==============================================================================\n");

        for (int i = 0; i < rangeList.size(); ++i){
            System.out.println("\n|  "+rangeList.get(i).getVIN()+" "+rangeList.get(i).getMake()+" " +
                    ""+rangeList.get(i).getModel()+" "+rangeList.get(i).getYear()+" " +
                    ""+rangeList.get(i).getMileage()+" "+rangeList.get(i).getPrice()+""); //FIX ME: Format price 2 Decimal places
        }
        
        System.out.print("\n==============================================================================\n");
        }
    }


}