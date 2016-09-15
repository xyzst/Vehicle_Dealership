package dealership;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FIXME -- need description of class Main
 *
 * @author Darren Rambaud (d_r273)
 * @author Nathan Easton (nle7)
 * @version 9/20/2016
 */
public class Main {
    private static final int SHOW_EXISTING_CAR_RECORDS = 1, // FIXME -- need to add documentation? Revise placement?
                             ADD_NEW_CAR = 2,
                             DELETE_CAR = 3,
                             SEARCH_FOR_CAR_VIN = 4,
                             SHOW_LIST_CARS_RANGE = 5,
                             EXIT_PROGRAM = 6;

    /**
     * FIXME -- need description of method displayMenu
     *
     */
    private void displayMenu(){
        System.out.print("\n"+SHOW_EXISTING_CAR_RECORDS+". Show all existing car records in the database (in any order)." +
                "\n"+ADD_NEW_CAR+". Add a new car record to the database." +
                "\n"+DELETE_CAR+". Delete a car record from a database." +
                "\n"+SEARCH_FOR_CAR_VIN+". Search for a car (given its VIN)." +
                "\n"+SHOW_LIST_CARS_RANGE+". Show a list of cars within a given price range." +
                "\n"+EXIT_PROGRAM+". Exit program.\n"+
                "\nPlease select an option between "+SHOW_EXISTING_CAR_RECORDS+" and "+EXIT_PROGRAM+":");
    }

    /**
     * FIXME -- need description of method selectOption
     *
     */
    private void selectOption(){
        Scanner sc = new Scanner(System.in);
        database db = new database();

        int option = sc.nextInt();

        while (option < SHOW_EXISTING_CAR_RECORDS || option > EXIT_PROGRAM){ // FIXME -- insert exception handling??
            System.out.print("\nYour selection ("+option+") is an invalid option." +
                    "\nPlease try again: ");
            option = sc.nextInt();
        }

        switch (option){
            case SHOW_EXISTING_CAR_RECORDS:
                //FIXME -- need funct
                break;
            case ADD_NEW_CAR:
                //FIXME -- need funct

                break;
            case DELETE_CAR:
                //FIXME -- need funct
                break;
            case SEARCH_FOR_CAR_VIN:
                //FIXME -- need funct
                break;
            case SHOW_LIST_CARS_RANGE:
                //FIXME -- need funct
                break;
            case EXIT_PROGRAM:
                //FIXME -- need funct
                break;
        }
    }

    /**
     * FIXME -- need description of method Main
     *
     * @param  args   a sample parameter for a method
     */
    public static void main (String[] args) throws IOException {
        Main main = new Main();
        database db = new database();
        ArrayList<Car> inventory = new ArrayList<Car>();

        inventory = db.importVehicleData();

        main.displayMenu();
        main.selectOption();

    }
}