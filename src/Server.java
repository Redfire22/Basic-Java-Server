import java.io.*;
import java.net.*;

// - This code implement a very basic server in Java -
// This server will start a new thread for every new connection
// This server use the class ServerHandler to handle the operation
// ---------------------------------------------------------------------
// This server will handle request from a class "Client"

// The client will be able to do several things :
// - Add a car if he is a sale person
// - Buy a car
// - View car, with condition (ex : only car for sale)

// The database is a collection of car


public class Server {

    //We use the port number 1234
    final static int portNumber = 1234;
    //final static int numThread = 50;

    //The Database on which the action will be performed
    static CarData data = new CarData();

    //The main, will start the server
    public static void main(String[] args) {
        System.out.println("Server running...");
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true){
                Socket socket = serverSocket.accept();

                (new Thread(new ServerHandler(socket, data))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
