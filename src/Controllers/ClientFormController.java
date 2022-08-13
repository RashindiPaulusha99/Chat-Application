package Controllers;

import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientFormController {
    public AnchorPane clientContext;
    public Label lblName;
    public TextField txtType;
    public TextArea txtChatArea;
    public Button btnSend;
    public Button btnCamera;
    public Button btnEmoji;
    public ImageView imgProfile;
    public ComboBox<String> cmbInfo;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

   public void setData(String name) throws SQLException, ClassNotFoundException {
       Users users = new RegisterServices().searchByUserName(name);
       lblName.setText(users.getName());
   }

    public void initialize() throws IOException {

        cmbInfo.getItems().addAll(
                "Profile",
                "Logout"
        );

        cmbInfo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                if (newValue.equals("Logout")){
                    Stage window = (Stage) clientContext.getScene().getWindow();
                    try {
                        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //connectSocket();

        /*new Thread(()->{

            try {
                socket = new Socket("localhost",5000);

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();*/
    }

    /*public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(Controller.username + ":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                txtChatArea.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

   /* public void connectSocket() {
        try {
            socket = new Socket("localhost", 8889);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void sendOnAction(ActionEvent event) throws IOException {
        /*PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtType.getText());
        printWriter.flush();*/
        /*String msg = txtType.getText();
        writer.println(Controller.username + ": " + msg);
        txtChatArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtChatArea.appendText("Me: " + msg + "\n");
        txtType.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }*/
    }

    public void stickerOnAction(ActionEvent event) {

    }

    public void emojiOnAction(ActionEvent event) {

    }
}
