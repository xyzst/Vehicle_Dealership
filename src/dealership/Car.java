package dealership;

public class Car {
    private String VIN,
                   make,
                   model;

    private int year,
                mileage;

    private float price;

    /**
     * FIXME -- insert description
     * @param VIN
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     */
    Car (String VIN, String make, String model, int year, int mileage, float price){
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }


}
