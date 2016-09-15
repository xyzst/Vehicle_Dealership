package dealership;

import java.io.FileWriter;
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
            //Car data = new Car();
            String line;
            String[] words;

            while (source.hasNextLine()) {
                Car data = new Car(); // FIXME -- loop is creating a new Car object every time but it works!
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
     *  FIXME -- insert description for displayInventory
     */
    public void displayInventory (){
        System.out.print("\n");
        for (int i = 0; i < vehicle_db.size(); ++i){ // FIXME -- need to continue displaying rest of data and making it pretty
            System.out.println(vehicle_db.get(i).getVIN());
        }
    }

    /**
     * FIXME -- insert description for addNewVehicle
     */
    public void addNewVehicle(){
        Scanner in = new Scanner(System.in);
        Car temp = new Car();

        int query_int;
        String query_str;
        char query_char;
        float query_float;
        boolean too_long,
                negative_value;

        do {
            System.out.println("Please enter the 5 digit VIN (Vehicle Identification Number) of the vehicle:");
            query_str = in.nextLine();

            if (query_str.length() > 5 || query_str.length() < 5) { //FIXME - need to get rid of literal
                System.out.println("ERROR: Invalid VIN entered, please try again...");
                too_long = true;
            }
            else {
                too_long = false;
            }
        } while (too_long);
        // FIXME -- could reuse VIN search code here
        temp.setVIN(query_str.toUpperCase());

        System.out.println("Please enter the make of the vehicle (eg, Toyota, Honda, Ford ...):");
        temp.setMake(in.nextLine());

        System.out.println("Please enter the model of the vehicle (eg, S200, F-150, Yaris ...):");
        temp.setModel(in.nextLine());

        do {
            System.out.println("Please enter the vehicle's model year (eg, 2016, 2010, 1999, ...):");
            query_int = Integer.parseInt(in.nextLine());

            if (query_int < 0) {
                System.out.println("ERROR: Invalid model year entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setYear(query_int);

        do {
            System.out.println("Please enter the vehicle's mileage (eg, 50000, 25343, 12345, ...):");
            query_int = Integer.parseInt(in.nextLine());

            if (query_int < 0) {
                System.out.println("ERROR: Invalid mileage entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setMileage(query_int);

        do {
            System.out.println("Please enter the price of the vehicle (eg, 50000, 69999.99, 12500, ...):");
            query_float = Float.parseFloat(in.nextLine());

            if (query_float < 0) {
                System.out.println("ERROR: Invalid vehicle price entered, please try again...");
                negative_value = true;
            }
            else{
                negative_value = false;
            }
        } while (negative_value);
        temp.setPrice(query_float);

        vehicle_db.add(temp);
        System.out.println("The vehicle (VIN: "+temp.getVIN()+", Make: "+temp.getMake()+", " +
                           "Model: "+temp.getModel()+", Year: "+temp.getYear()+", Mileage: "+temp.getMileage()+", " +
                           "Price: $"+temp.getPrice()+") has been successfully added to the inventory list!");
    }
}
