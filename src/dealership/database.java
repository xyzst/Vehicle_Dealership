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
    private static final int SIZE = 10;

    /**
     * FIXME -- need description
     */
    public ArrayList<Car> importVehicleData() throws IOException {
        Scanner fIn = null;
        Car data = new Car();
        ArrayList<Car> temporary = new ArrayList<Car>(SIZE);

        try {
            fIn = new Scanner(new FileReader("cars.txt"));




        }
        catch (IOException ex){
            ex.printStackTrace();
            System.out.println("\nERROR: \"cars.txt\" does NOT exist!");
        }
        finally{
            if (fIn != null){
                fIn.close();
            }
        }

        return temporary;
    }
}
