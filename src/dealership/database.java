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
                             PRICE = 5,
                             FIELD_LEN = 13,
                             NUM_HEADER_CHARS = 69;

    private static final char HEADER_CHAR = '*';

    private static final String STR_VIN = "VIN",
                                STR_MAKE = "MAKE",
                                STR_MODEL = "MODEL",
                                STR_YEAR = "YEAR",
                                STR_MILEAGE = "MILEAGE",
                                STR_PRICE  = "PRICE (USD)";
                             
    private static final String FILE_PATH = ("cars.txt");
    private ArrayList<Car> vehicle_db = new ArrayList<Car>(SIZE);

    /**
     * printSeparatorLine method simply iterates over a specified amount to output to the screen the separator line.
     * The number of characters to output is determined by NUM_HEADER_CHARS and the header character is easily
     * modified by editing HEADER_CHAR.
     */
    private void printSeparatorLine() {
        System.out.print("\n");
        for (int i = 0; i < NUM_HEADER_CHARS; ++i){
            System.out.print(HEADER_CHAR);
        }
        System.out.print("\n");
    }

    /**
     * printHeaderLine method prints the header (VIN, MAKE, MODEL, YEAR, MILEAGE, PRICE) to the screen. Able to easily
     * add and delete headers, as needed, as well as modify the field length in order to accommodate changing requirements
     * such as requiring a shorter or larger field length via FIELD_LEN + (an integer).
     */
    private void printHeaderLine () {
        System.out.print(""+HEADER_CHAR+" ");
        System.out.printf("%-"+(FIELD_LEN - 6)+"s", STR_VIN);
        System.out.printf("%-"+FIELD_LEN+"s", STR_MAKE);
        System.out.printf("%-"+FIELD_LEN+"s", STR_MODEL);
        System.out.printf("%-"+(FIELD_LEN - 6)+"s", STR_YEAR);
        System.out.printf("%-"+(FIELD_LEN - 2)+"s", STR_MILEAGE);
        System.out.printf("%-"+(FIELD_LEN - 1)+"s   "+HEADER_CHAR+"", STR_PRICE);
    }

    /**
     * printLineOfVehicleInfo iterates linearly through vehicle_db ArrayList, printing out the information contained in
     * each Car object (eg, VIN, Make, Model, Year, Mileage ...).
     */
    private void printLineOfVehicleInfo() {
        for (int i = 0; i < vehicle_db.size(); ++i) {
            System.out.print("\n* ");
            System.out.printf("%-" + (FIELD_LEN - 6) + "s", vehicle_db.get(i).getVIN());
            System.out.printf("%-" + FIELD_LEN + "s", vehicle_db.get(i).getMake());
            System.out.printf("%-" + FIELD_LEN + "s", vehicle_db.get(i).getModel());
            System.out.printf("%-" + (FIELD_LEN - 6) + "d", vehicle_db.get(i).getYear());
            System.out.printf("%-" + (FIELD_LEN - 2) + "d", vehicle_db.get(i).getMileage());
            System.out.printf("$%-" + (FIELD_LEN - 1) + ".2f  "+HEADER_CHAR+"", vehicle_db.get(i).getPrice());
        }
    }

    /**
     * importVehicleData is an ArrayList method which populates from a specified filepath. The 
     * method throws an exception if the input file cannot be retrieved or utilized.
     * 
     * @return successful which is a boolean. A false is returned if an exception is thrown
     */
    public boolean importVehicleData() throws IOException, NumberFormatException {
        Scanner source = null;
        boolean successful = false;

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
            successful = true;
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
            System.out.println("ERROR: \""+FILE_PATH+"\" does NOT exist!");

            successful = false;
        }
        finally {
            if (source != null){
                source.close();
            }
        }
        return successful;
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
    }

    /**
     *  displayInventory method prints the contents of the inventory stored in the database. If
     *  the inventory is empty, then the user is notified by a message printed to system out.
     */
    public void displayInventory (){
        printSeparatorLine();
        printHeaderLine();
        if (vehicle_db.isEmpty()){
            System.out.println("The vehicle inventory is empty.");
        }
        else {
            printLineOfVehicleInfo();
        }
        printSeparatorLine();
    }

    /**
     * addNewVehicle method allows a user to manually create a Car object and add it to the database. The method 
     * checks for correct user input for various fields of a Car object.
     */
    public void addNewVehicle(){
        Scanner in = new Scanner(System.in);
        Car temp = new Car();

        final int MODEL_YEAR_LOW = 1900,
                  MODEL_YEAR_HI = 2017,
                  MAXIMUM_MILEAGE_ALLOWED = 500000;

        int query_int; 
        String query_str; 
        float query_float;
        boolean invalid,
                negative_value,
                alreadyExists = true;
        final boolean IGNORE_OUTPUT = true;

        do {
            System.out.println("\nPlease enter the 5 character VIN (Vehicle Identification Number) of the vehicle: ");
            query_str = in.nextLine();

            if (query_str.length() != VIN_LENGTH ) { 
                System.out.println("\nERROR: Invalid VIN entered, please try again...");
                invalid = true;
            }
            else {
                invalid = false;
                alreadyExists = vehicleSearchByVIN(query_str, IGNORE_OUTPUT);
                if (alreadyExists) {
                    System.out.println("\nERROR: This VIN (#"+query_str+") already exists in the database!");
                    System.out.println("If you wish to add another vehicle, please re-select this menu option" +
                                       " from the main menu.");
                    return;
                }
            }
        } while (invalid);

        temp.setVIN(query_str.toUpperCase());

        System.out.println("\nPlease enter the make of the vehicle (eg, Toyota, Honda, Ford ...): ");
        query_str = in.nextLine();
        temp.setMake(query_str.substring(0,1).toUpperCase() + query_str.substring(1).toLowerCase());

        System.out.println("\nPlease enter the model of the vehicle (eg, S200, F-150, Yaris ...): ");
        query_str = in.nextLine();
        temp.setModel(query_str.substring(0,1).toUpperCase() + query_str.substring(1).toLowerCase());

        do {
            System.out.println("\nPlease enter the vehicle's model year (eg, 2016, 2010, 1999, ...): 1");
            query_int = Integer.parseInt(in.nextLine());

            if (query_int < MODEL_YEAR_LOW || query_int > MODEL_YEAR_HI) {
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

            if (query_int < 0 || query_int > MAXIMUM_MILEAGE_ALLOWED) {
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
        System.out.println("\nThe following entry...");
        printSeparatorLine();
        printHeaderLine();
        System.out.print("\n* ");
        System.out.printf("%-"+(FIELD_LEN - 6)+"s", temp.getVIN());
        System.out.printf("%-"+FIELD_LEN+"s", temp.getMake());
        System.out.printf("%-"+FIELD_LEN+"s", temp.getModel());
        System.out.printf("%-"+(FIELD_LEN - 6)+"d", temp.getYear());
        System.out.printf("%-"+(FIELD_LEN - 2)+"d", temp.getMileage());
        System.out.printf("$%-"+(FIELD_LEN - 1)+".2f  "+HEADER_CHAR+"", temp.getPrice());
        printSeparatorLine();

        System.out.println("\nhas been successfully added to the database!");
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
    public boolean vehicleSearchByVIN (String VIN, boolean ignoreScreenOutput) {
        boolean doesExist = false;

        for (int i = 0; i < vehicle_db.size(); ++i) {
            if (VIN.equalsIgnoreCase(vehicle_db.get(i).getVIN())) {
                if (!ignoreScreenOutput) {
                    System.out.print("\nVEHICLE FOUND! Please refer below ...");
                    printSeparatorLine();
                    printHeaderLine();
                    System.out.print("\n* ");
                    System.out.printf("%-"+(FIELD_LEN - 6)+"s", vehicle_db.get(i).getVIN());
                    System.out.printf("%-"+FIELD_LEN+"s", vehicle_db.get(i).getMake());
                    System.out.printf("%-"+FIELD_LEN+"s", vehicle_db.get(i).getModel());
                    System.out.printf("%-"+(FIELD_LEN - 6)+"d", vehicle_db.get(i).getYear());
                    System.out.printf("%-"+(FIELD_LEN - 2)+"d", vehicle_db.get(i).getMileage());
                    System.out.printf("$%-"+(FIELD_LEN - 1)+".2f  "+HEADER_CHAR+"", vehicle_db.get(i).getPrice());
                    printSeparatorLine();
                }
                doesExist = true;
                break;
             }
        }
        return doesExist;
    }

    /**
     * priceRangeSearch method allows a user to establish a price range, which allows the user to view only Car
     * objects that meet the specified criteria. The method ensures logical price range boundaries. The method 
     * creates an ArrayList 'rangeList' that stores the qualified Car objects. rangeList is printed displaying to 
     * the user desired matches. If there are no matches, the user is notified by a message to system out. 
     */
    public void priceRangeSearch () {
        boolean withinRange = false;
        //ArrayList<Car> rangeList = new ArrayList<Car>(); FIXME -- wasteful of resources?
        ArrayList<Integer> indices = new ArrayList<Integer>();
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
            else if (lower > higher || higher < lower){
                System.out.println("\nYou have entered an invalid range, please try again...");
                invalid_range = true;
            }
            else {
                invalid_range = false;
            }

        } while (invalid_range);

        for (int i = 0; i < vehicle_db.size(); ++i) {
            if ((vehicle_db.get(i).getPrice() <= higher) && (vehicle_db.get(i).getPrice() >= lower)) {
                indices.add(i);
                withinRange = true;
            }
        }
        
        if (!withinRange) {
            System.out.println("\nSorry, there are no vehicle matches between price range: $" +lower+ " - $" +higher+ "in our inventory\n");
        }
        else {
            System.out.println("\nThe following vehicles match your desired criteria ($"+lower+" - $"+higher+")...");
            printSeparatorLine();
            printHeaderLine();
            for (int i = 0; i < indices.size(); ++i) {
                System.out.print("\n* ");
                System.out.printf("%-" + (FIELD_LEN - 6) + "s", vehicle_db.get(indices.get(i)).getVIN());
                System.out.printf("%-" + FIELD_LEN + "s", vehicle_db.get(indices.get(i)).getMake());
                System.out.printf("%-" + FIELD_LEN + "s", vehicle_db.get(indices.get(i)).getModel());
                System.out.printf("%-" + (FIELD_LEN - 6) + "d", vehicle_db.get(indices.get(i)).getYear());
                System.out.printf("%-" + (FIELD_LEN - 2) + "d", vehicle_db.get(indices.get(i)).getMileage());
                System.out.printf("$%-" + (FIELD_LEN - 1) + ".2f  "+HEADER_CHAR+"", vehicle_db.get(indices.get(i)).getPrice());
            }
            printSeparatorLine();
        }
    }
}