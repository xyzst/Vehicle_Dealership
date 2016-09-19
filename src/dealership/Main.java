package dealership;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main is the driver class establishing a user interface.
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
                             EXIT_PROGRAM = 6,
                             VIN_LIMIT = 5;
                             
    private static database db = new database();
    private static Main main = new Main();
    
    /**
     * displayMenu method prints the menu to the system out. 
     */
    private void displayMenu(){
        System.out.println("\nWelcome to the Main Menu!");
        System.out.print("\n     "+SHOW_EXISTING_CAR_RECORDS+". Show all existing car records in the database (in any order)." +
                "\n     "+ADD_NEW_CAR+". Add a new car record to the database." +
                "\n     "+DELETE_CAR+". Delete a car record from a database." +
                "\n     "+SEARCH_FOR_CAR_VIN+". Search for a car (By VIN)." +
                "\n     "+SHOW_LIST_CARS_RANGE+". Show a list of cars within a given price range." +
                "\n     "+EXIT_PROGRAM+". Exit program.\n"+
                "\nPlease select an option between "+SHOW_EXISTING_CAR_RECORDS+" and "+EXIT_PROGRAM+": ");
    }

    private void pressEnter2Continue(){
        Scanner advance = new Scanner(System.in);
        System.out.print("\nPress \"ENTER\" to advance to the main menu ...");
        advance.nextLine();
    }

    /**
     * selectOption method takes user input to allow navigation of program menu. Returns boolean value 
     * when user desires to exit the program.
     * 
     * @return exit Value is changed to true when user intends to terminate the program.
     */
    private boolean selectOption(){
        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt(); //FIX ME: VALIDATE USER INPUT AS AN INT?
        boolean exit = false;

        while (option < SHOW_EXISTING_CAR_RECORDS || option > EXIT_PROGRAM) {
            System.out.print("\n\nYour selection ("+option+") is an invalid option." +
                    "\nPlease try again: ");
            option = sc.nextInt();
        }

        switch (option){
            case SHOW_EXISTING_CAR_RECORDS:
                System.out.print("\nVEHICLE INVENTORY:");
                db.displayInventory();
                pressEnter2Continue();
                exit = false;
                break;
            case ADD_NEW_CAR:
                System.out.print("\nNow proceeding to add a new vehicle to the inventory ...");
                db.addNewVehicle();
                exit = false;
                pressEnter2Continue();
                break;
            case DELETE_CAR:
                System.out.print("\nList of vehicle(s) in the database:");
                db.delByVIN();
                exit = false;
                pressEnter2Continue();
                break;
            case SEARCH_FOR_CAR_VIN:
                System.out.println("\nProceeding to search for a vehicle by VIN# ...");
                String search;
                final boolean IGNORE_OUTPUT = false;
                boolean invalid = false;

                sc.nextLine(); // consuming newline character
                do {
                    System.out.println("Please enter the 5 character VIN of the vehicle you would like to search for: ");
                    search = sc.nextLine();

                    if (search.length() != VIN_LIMIT ) {
                        System.out.println("\nERROR: Invalid VIN entered, please try again...\n");
                        invalid = true;
                    }
                    else {
                        invalid = false;
                    }
                } while (invalid);

                boolean found = db.vehicleSearchByVIN(search, IGNORE_OUTPUT);

                if (!found) {
                    System.out.println("\nSorry, there are no vehicles associated with this VIN (#"+search+").");
                }

                pressEnter2Continue();
                exit = false;
                break;
            case SHOW_LIST_CARS_RANGE:
                System.out.print("\nProceeding to search for vehicles that are within a certain price range...");
                db.priceRangeSearch();
                pressEnter2Continue();
                exit = false;
                break;
            case EXIT_PROGRAM:
                System.out.println("\nGoodbye!");
                exit = true;
                break;
        }
        return exit;
    }

    /**
     * Main method operates the program. The method throws an exception when an input error 
     * occurs. The method reads in input from a file to populate the database. When the 
     * program is terminated, the database is exported to a text file.
     *
     * @param  args   Takes command line arguments from user
     */
    public static void main (String[] args) throws IOException, Exception {
        boolean leave,
                file_import;

        file_import = db.importVehicleData();

        if (file_import){
            System.out.println("Previous vehicle data has been successfully loaded!");
        }
        else {
            System.out.println("Previous vehicle data not detected, will create a new file upon program exit.");
        }

        do {
            main.displayMenu();
            leave = main.selectOption();
        } while (!leave);

        db.exportArrayList2File();
    }
}