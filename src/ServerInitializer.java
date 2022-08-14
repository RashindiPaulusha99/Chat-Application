import Controllers.ClientController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer{

    private static ArrayList<ClientController> clients = new ArrayList<ClientController>();

    public ServerInitializer()
    {
        try {
            ServerSocket ss=new ServerSocket(8006);
            System.out.println ("Waiting for request");
            Socket s=ss.accept();
            System.out.println ("Connected With"+s.getInetAddress().toString());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            String req=(String)ois.readObject();
            System.out.println (req);

            File f=new File(req);
            FileInputStream fin=new FileInputStream(f);

            int c;
            int sz=(int)f.length();
            byte b[]=new byte [sz];
            oos.writeObject(new Integer(sz));
            oos.flush();
            int j=0;
            while ((c = fin.read()) != -1) {

                b[j]=(byte)c;
                j++;
            }

            fin.close();
            oos.flush();
            oos.write(b,0,b.length);
            oos.flush();
            System.out.println ("Size "+sz);
            System.out.println ("buf size"+ss.getReceiveBufferSize());
            oos.writeObject(new String("Ok"));
            oos.flush();
            ss.close();
        }
        catch (Exception ex) {
            System.out.println ("Error"+ex);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket;
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

        ServerInitializer ob=new ServerInitializer();
    }
}
