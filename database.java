package dealership;

import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * FIXME -- insert descipt for database class
 *
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
     * FIXME -- need description
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
     * FIXME -- add documentation to exportArrayList2File
     */
    public void exportArrayList2File() throws Exception {
        PrintWriter fOut = new PrintWriter(FILE_PATH);
        
        fOut.println("\n==============================================================================\n");

        for (int i = 0; i < vehicle_db.size(); ++i) {
            fOut.println(""+vehicle_db.get(i).getVIN()+" "+vehicle_db.get(i).getMake()+" " +
                    ""+vehicle_db.get(i).getModel()+" "+vehicle_db.get(i).getYear()+" " +
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+"");
        }
        
        fOut.println("\n==============================================================================\n");

        fOut.close();
        System.out.println("\nGoodbye!");
    }

    /**
     *  FIXME -- insert description for displayInventory
     */
    public void displayInventory (){
        System.out.print("\n==============================================================================\n");

        if (vehicle_db.isEmpty()){
            System.out.println("The vehicle inventory is empty.");
        }
        else {
            for (int i = 0; i < vehicle_db.size(); ++i) { // FIXME -- need to continue displaying rest of data and making it prett   
                System.out.println("\n|  "+vehicle_db.get(i).getVIN()+" "+vehicle_db.get(i).getMake()+" " +
                    ""+vehicle_db.get(i).getModel()+" "+vehicle_db.get(i).getYear()+" " +
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+"");
            }
        }
        System.out.print("\n==============================================================================\n");
    }

    /**
     * FIXME -- insert description for addNewVehicle
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

            if (query_str.length() != VIN_LENGTH ) { //FIXME - need to get rid of literal
                System.out.println("\nERROR: Invalid VIN entered, please try again...\n");
                too_long = true;
            }
            else {
                too_long = false;
            }
        } while (too_long);
        // FIXME -- could reuse VIN search code here??
        temp.setVIN(query_str.toUpperCase());

        System.out.println("\nPlease enter the make of the vehicle (eg, Toyota, Honda, Ford ...): ");
        query_str = in.nextLine();
        temp.setMake(query_str.toUpperCase());

        System.out.println("\nPlease enter the model of the vehicle (eg, S200, F-150, Yaris ...): ");
        query_str = in.nextLine();
        temp.setModel(query_str.toUpperCase());

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
                           "Price: $"+temp.getPrice()+") \n\nhas been successfully added to the inventory list!"); //FIX ME: OUTPUT ALIGNMENT, PRICE IN TWO DECIMAL DIGITS
    }

    /**
     * FIXME -- insert documentation
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
     * FIXME -- insert description for vehicleSearchByVIN
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
                    ""+vehicle_db.get(i).getMileage()+" "+vehicle_db.get(i).getPrice()+"");
                System.out.print("\n==============================================================================\n");
                doesExist = true;
                break;
             }
        }
        
        if (!doesExist)
            System.out.println("\nSorry, the VIN #"+query+", does not exist in this vehicle inventory.\n");
    }

    /**
     * FIXME -- insert description for priceSearch
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
                    ""+rangeList.get(i).getMileage()+" "+rangeList.get(i).getPrice()+"");
        }
        
        System.out.print("\n==============================================================================\n");
        }
    }


}