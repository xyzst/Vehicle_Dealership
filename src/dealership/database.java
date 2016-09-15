package dealership;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
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

            while (source.hasNextLine()){
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
            ex.printStackTrace();
            System.out.println("\nERROR: \"cars.txt\" does NOT exist!");
        }
        finally{
            if (source != null){
                source.close();
            }
        }

        return vehicle_db;
    }
}
