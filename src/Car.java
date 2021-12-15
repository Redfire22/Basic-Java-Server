import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//The car class, we use it to define a car
public class Car {

    //Variable
    private String registration;
    private String make;
    private float price;
    private int mileage;
    private boolean forSales;

    //Getter
    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public float getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public boolean isForSales() {
        return forSales;
    }

    //Setter
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setForSales(boolean forSales) {
        this.forSales = forSales;
    }

    //Constructor
    public Car(String registration, String make, float price, int mileage, boolean forSales){
        this.registration = registration;
        this.make = make;
        this.price = price;
        this.mileage = mileage;
        this.forSales = forSales;
    }

    //Default constructor
    public Car(){
        this.registration = "DDD555";
        this.make = "Default";
        this.price = 100;
        this.mileage = 0;
        this.forSales = true;
    }

    //Allow to send a car object though a stream object
    public void writeOutputStream(DataOutputStream out){
        try{
            out.writeUTF(registration);
            out.writeUTF(make);
            out.writeFloat(price);
            out.writeInt(mileage);
            out.writeBoolean(forSales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Allow to receive a car object by a stream object
    public void readInputStream(DataInputStream in){
        try {
            this.registration = in.readUTF();
            this.make = in.readUTF();
            this.price = in.readFloat();
            this.mileage = in.readInt();
            this.forSales = in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A short definition of a car, with only minimal information
    public String toStringReduced(){
        return make + " - " + registration;
    }

    // A complete description of the car
    @Override
    public String toString(){
        return "[registration = "+registration+", make = " + make + ", price = " +  price + ", mileage = " + mileage +", forSale = " + forSales;
    }
}
