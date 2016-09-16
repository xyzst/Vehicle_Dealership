package dealership; 

public class Car {
    private String VIN,
                   make,
                   model;
    private int year,
                mileage;
    private float price;

    Car(){

    }

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

    public void setVIN (String VIN) {
        this.VIN = VIN;
    }

    public void setMake (String make) {
        this.make = make;
    }

    public void setModel (String model) {
        this.model = model;
    }

    public void setYear (int year) {
        this.year = year;
    }

    public void setMileage (int mileage) {
        this.mileage = mileage;
    }

    public void setPrice (float price) {
        this.price = price;
    }

    public String getVIN () {
        return this.VIN;
    }

    public String getMake () {
        return this.make;
    }

    public String getModel () {
        return this.model;
    }

    public int getYear () {
        return this.year;
    }

    public int getMileage () {
        return this.mileage;
    }

    public float getPrice () {
        return this.price;
    }



}