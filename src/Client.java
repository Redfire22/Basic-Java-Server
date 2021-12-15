import java.io.*;
import java.net.*;
import java.util.Scanner;

//The client class, this class is used to connect to the server
//It allows the client to do several action displayed on a console interface

public class Client {

    //Variables
    //The port of the server, must be the same as the Server.java file
    final private static int port = 1234;
    //This variable to define if this is a sale person of not
    static boolean salePerson = true;
    //ID of the client, hardcoded for testing purpose, work as an identifier for the server, to know which has performed
    // what action.
    static int id = 5489;

    //The main function will display the interface, and give the client the choice of what to do
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while(choice != 5){
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Welcome Client - What would you like to do ?");
            System.out.println("Sales person = " + salePerson);
            System.out.println("Current user = " + id);
            System.out.println("--------------------------------------");
            System.out.println("Choice 1 : Add a car (sale only)");
            System.out.println("Choice 2 : Buy a car");
            System.out.println("Choice 3 : View data - Make");
            System.out.println("Choice 4 : View data - forSale");
            System.out.println("Choice 5 : Quit");

            choice = scanner.nextInt();

            String registration;
            String make;
            float price;
            int mileage;

            switch (choice){
                //Add a car
                case 1:
                    Scanner addScan = new Scanner(System.in);
                    System.out.println("----------------------------------------------------------------------------------");
                    if(salePerson) {
                        System.out.println("Add a car");
                        System.out.println("--------------------------------------");
                        System.out.println("Please state the registration :");
                        registration = addScan.nextLine();
                        System.out.println("Please state the make :");
                        make = addScan.nextLine();
                        System.out.println("Please state the price :");
                        price = addScan.nextInt();
                        System.out.println("Please state the mileage :");
                        mileage = addScan.nextInt();
                        addNewCar(new Car(registration, make, price, mileage, true));

                    }
                    else{
                        System.out.println("Sorry, you are not authorized to perform this action");
                    }
                    break;
                //Buy a car
                case 2:
                    Scanner buyScan = new Scanner(System.in);
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("Buy a car");
                    System.out.println("--------------------------------------");
                    System.out.println("Please state the registration :");
                    registration = buyScan.nextLine();
                    buyCar(registration);
                    break;
                //View car by make
                case 3:
                    Scanner MakeScan = new Scanner(System.in);
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("View data - Make");
                    System.out.println("--------------------------------------");
                    System.out.println("Please state the make :");
                    make = MakeScan.nextLine();
                    ViewMakeCar(make);
                    break;
                //View car for sale
                case 4:
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("View data - Sale");
                    System.out.println("--------------------------------------");
                    ViewSaleCar();
                    break;
                case 5:
                    break;
            }
        }
    }

    //All the following function follow the same structure, they exchange stream data using the socket with the server
    // to obtain the wanted data.

    static void addNewCar(Car car){
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(0);
            car.writeOutputStream(out);
            boolean result = in.readBoolean();
            out.writeInt(id);
            if(!result){
                System.out.println("Error in adding the new Car");
            }else{
                System.out.println("Confirmed : car " + car.toStringReduced() +" successfully added");
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void buyCar(String registration){

        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(1);
            out.writeUTF(registration);
            boolean result = in.readBoolean();
            out.writeInt(id);
            if(!result){
                System.out.println("Error in buying the new Car");
            }else{
                System.out.println("Confirmed : car " + registration +" successfully brought");
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void ViewMakeCar(String make){
        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(2);
            out.writeUTF(make);
            out.writeInt(id);
            int a = in.readInt();
            System.out.println("Found = " + a);
            Car c = new Car();
            for(int i =0; i < a; i++){
                c.readInputStream(in);
                System.out.println(c);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void ViewSaleCar(){
        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(3);
            out.writeInt(id);
            int a = in.readInt();
            Car c = new Car();
            for(int i =0; i < a; i++){
                c.readInputStream(in);
                System.out.println(c);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
