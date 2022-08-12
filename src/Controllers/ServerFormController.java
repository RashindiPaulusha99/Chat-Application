package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public AnchorPane serverContext;
    public TextArea txtChatArea;
    public TextField txtType;
    public Button btnSend;
    public Button btnCamera;
    public Button btnEmoji;

    Socket socket=null;

    public void initialize(){
        new Thread(()->{
            try {

                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server started.");
                socket = serverSocket.accept();
                System.out.println("Client Connected.");

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);

            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    public void sendOnAction(ActionEvent event) throws IOException {
        txtChatArea.setText("You  : "+txtType.getText());
        txtType.clear();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtType.getText());
        printWriter.flush();
    }

    public void stickerOnAction(ActionEvent event) {

    }

    public void emojiOnAction(ActionEvent event) {

    }
}
