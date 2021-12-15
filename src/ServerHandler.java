import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

// This is the class that will handle request,
// One instance is created by connection, and run in its own thread.
public class ServerHandler implements Runnable {

    //Basic required data, the database and the socket
    Socket s;
    CarData data;

    //Constructor
    public ServerHandler(Socket socket, CarData data) {
        s = socket;
        this.data = data;
    }

    // The run function from the Runnable interface, what will this class do one the thread is started :
    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(s.getInputStream());

            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            int id = 0;

            int opt = in.readInt();

            //Depending on what the client ask for, a different function will be executed
            switch (opt){
                //Add a car (sale person)
                case 0:
                    Car c = new Car();
                    c.readInputStream(in);
                    data.add(c);
                    out.writeBoolean(true);
                    id = in.readInt();
                    System.out.println(c.toStringReduced() + " added successfully ! --- Action performed by user : "+id);
                    break;
                    // Buy a car
                case 1:
                    String regis = in.readUTF();
                    data.carSold(regis);
                    out.writeBoolean(true);
                    id = in.readInt();
                    System.out.println("Car " + regis + " was brought successfully by user : "+id);
                    break;
                    // View car by make
                case 2:
                    String make = in.readUTF();
                    id = in.readInt();
                    ArrayList<Car> listCar = data.RetrieveMade(make);
                    out.writeInt(listCar.size());
                    for(int i = 0; i < listCar.size(); i++){
                        listCar.get(i).writeOutputStream(out);
                    }
                    System.out.println("Car of make = " + make + " was viewed successfully by user : "+id);
                    break;
                    //View car only for sales
                case 3:
                    id = in.readInt();
                    ArrayList<Car> listCarSale = data.RetrieveForSale();
                    out.writeInt(listCarSale.size());
                    for(int i=0; i<listCarSale.size(); i++){
                        listCarSale.get(i).writeOutputStream(out);
                    }
                    System.out.println("Car for Sales were viewed successfully by user : "+id);
                    break;
            }
            s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }
}
