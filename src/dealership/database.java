package dealership;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 *
 */
public class database {
    /**
     * FIXME -- need description
     */
    public void importVehicleData() throws IOException {
        try {
            Scanner fIn = new Scanner(new FileReader("cars.txt"));
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.out.println("\nERROR: \"cars.txt\" does NOT exist!" +
                               "\nNow creating cars.txt ...");
        }
    }
}
