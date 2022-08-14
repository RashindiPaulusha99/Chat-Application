import Controllers.ClientController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer{

    private static ArrayList<ClientController> clients = new ArrayList<ClientController>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket ;

        try {
            serverSocket = new ServerSocket(8889);
            while(true) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientController clientThread = new ClientController(socket, clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
