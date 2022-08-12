package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOneFormController {
    public AnchorPane clientContext;
    public Label lblName;
    public TextField txtType;
    public TextArea txtChatArea;
    public Button btnSend;
    public Button btnCamera;
    public Button btnEmoji;

    Socket socket = null;

    public void initialize() throws IOException {

        new Thread(()->{

            try {
                socket = new Socket("localhost",5000);

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
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtType.getText());
        printWriter.flush();
    }

    public void stickerOnAction(ActionEvent event) {

    }

    public void emojiOnAction(ActionEvent event) {

    }
}
