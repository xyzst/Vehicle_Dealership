package dealership;

/**
 * Car is a class for objects of Car type.
 * 
 * @author Darren Rambaud (d_r273)
 * @author Nathan Easton (nle7)
 * @version 9/19/2016
 */
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
     * Constructor for Car class that initializes fields for a new Car object.
     * 
     * @param VIN initializes corresponding VIN number for Car object
     * @param make initializes corresponding make for Car object
     * @param model initializes corresponding model for Car object
     * @param year initializes corresponding year for Car object
     * @param mileage initializes corresponding mileage count for Car object
     * @param price initializes corresponding cost for Car object
     */
    Car (String VIN, String make, String model, int year, int mileage, float price){
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }
    
    /**
     * Sets VIN field for Car object as specified by VIN
     *
     * @param VIN is a String
     */
    public void setVIN (String VIN) {
        this.VIN = VIN;
    }
    
    /**
     * Sets make field for Car object as specified by make
     * @param make is a String
     */
    public void setMake (String make) {
        this.make = make;
    }

    /**
     * Sets model field for Car object.
     * @param model is a String
     */
    public void setModel (String model) {
        this.model = model;
    }

    /**
     * Mutator for the class variable year
     * @param year is an int
     */
    public void setYear (int year) {
        this.year = year;
    }
    
    /**
     * Sets mileage for Car object.
     */
    public void setMileage (int mileage) {
        this.mileage = mileage;
    }
    
    /**
     * Sets price field for Car object.
     * @param price is a float
     */
    public void setPrice (float price) {
        this.price = price;
    }
    
    /**
     * Returns VIN field for Car object.
     * 
     * @return VIN Value for VIN field
     */
    public String getVIN () {
        return this.VIN;
    }
    
    /**
     * Returns Make field for Car object.
     * 
     * @return Make, a string, for make field
     */
    public String getMake () {
        return this.make;
    }
    
    /**
     * Returns Model field for Car object.
     * 
     * @return model Value for Model field
     */
    public String getModel () {
        return this.model;
    }
    
    /**
     * Returns Year field for Car object.
     * 
     * @return year Value for Year field
     */
    public int getYear () {
        return this.year;
    }
    
    /**
     * Returns Mileage field for Car object.
     * 
     * @return mileage Value for Mileage field
     */
    public int getMileage () {
        return this.mileage;
    }
    
    /**
     * Returns Price field for Car object.
     * 
     * @return price Value for Price field, a float
     */
    public float getPrice () {
        return this.price;
    }
}