import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//The database of our server - take the form of a class

public class CarData {

    // A collection of car
    private ArrayList<Car> data = new ArrayList<Car>();

    // The lock, to make sure all the function of this database are Thread safe.
    private Lock lock = new ReentrantLock();

    // The constructor, here we use it to automatically create a list of car for testing purpose.
    public CarData(){
        FillData();
    }

    //--------------------------------------------
    // All these function are thread safe

    // This function will add a car to the database
    void add(Car car){
        lock.lock();
        try{
            data.add(car);
        }finally {
            lock.unlock();
        }
    }

    //This function will search if a certain car is in the database
    boolean search(Car car){
        lock.lock();
        try{
            return data.contains(car);
        }finally {
            lock.unlock();
        }
    }

    //Sell the car with the given registration
    boolean carSold(String regis){
        lock.lock();
        boolean result = false;
        try{
            for(int i= 0; i< data.size(); i++){
                if(data.get(i).getRegistration().equals(regis)){
                    data.get(i).setForSales(false);
                    result = true;
                }
            }
        }finally {
            lock.unlock();
        }
        return result;
    }

    // Retrieve car of the given make, and return them in a collection
    ArrayList<Car> RetrieveMade(String made){
        lock.lock();
        try{
            ArrayList<Car> dt = new ArrayList<Car>();
            for(int i=0; i < data.size(); i++){
                Car c1 = data.get(i);
                if(c1.getMake().equals(made)){
                    dt.add(c1);
                }
            }
            return dt;
        }finally {
            lock.unlock();
        }
    }

    // Retrieve car that are for sale, and return them in a collection
    ArrayList<Car> RetrieveForSale(){
        lock.lock();
        try{
            ArrayList<Car> dt = new ArrayList<Car>();
            for(int i=0; i < data.size(); i++){
                Car c1 = data.get(i);
                if(c1.isForSales()){
                    dt.add(c1);
                }
            }
            return dt;
        }finally {
            lock.unlock();
        }
    }

    //This function fill the database for testing purpose
    private void FillData(){
        //5
        data.add(new Car("16L1234", "Ferrari", 120000, 1000, true));
        data.add(new Car("01LH1234", "Ford Fiesta", 1000, 1000, true));
        data.add(new Car("02D1234", "Ford Focus", 11000, 2000, true));
        data.add(new Car("03WW1234", "Ford Mondeo", 12000, 3000, true));
        data.add(new Car("05KK1234", "Ford Mustang", 14000, 5000, true));
        //10
        data.add(new Car("45IO1485", "Tesla S", 158000, 1200, true));
        data.add(new Car("78D1254", "Hurricane", 1580000, 4500, true));
        data.add(new Car("08FR584", "Les Légendes Du Ciel", 2850000, 2500, true));
        data.add(new Car("94ER5892", "Renault espace", 250650, 5900, true));
        data.add(new Car("87TRU85", "Volkswagen", 156800, 1450, true));
        //15
        data.add(new Car("87RTD45", "Tesla X", 58058000, 540, true));
        data.add(new Car("DASSAULT", "Rafale", 589033600, 150, true));
        data.add(new Car("85EDEN90", "Audi", 156035, 1250, true));
        data.add(new Car("FR59603", "Bugatti", 1526003, 1268, true));
        data.add(new Car("TRUDRH589", "Smart", 15690, 230, true));
    }
}
