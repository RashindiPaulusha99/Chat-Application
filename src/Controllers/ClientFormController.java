package Controllers;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientFormController extends Thread implements Initializable {
    public AnchorPane clientContext;
    public Label lblName;
    public TextField txtType;
    public TextArea txtChatArea;
    public Button btnSend;
    public Button btnCamera;
    public Button btnEmoji;
    public ImageView imgProfile;
    public ComboBox<String> cmbInfo;
    public VBox vbox;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket=null;

    private File file;

   public void setData(String name) {
       lblName.setText(name);
   }

   @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmbInfo.getItems().addAll(
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
     
       try {
           socket = new Socket("localhost", 8889);
           System.out.println("Socket is connected with server!");
           reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           writer = new PrintWriter(socket.getOutputStream(), true);
           this.start();

       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void run() {
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
                if (cmd.equalsIgnoreCase(lblName.getText() + " :  ")) {
                    continue;
                }else if(fulmsg.toString().equalsIgnoreCase("Bye")) {
                    break;
                }

                /*if (file != null){
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(file);
                        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        Text text1 = new Text(lblName.getText()+ " : ");
                        vbox.getChildren().add(text1);
                        VBox vBox = new VBox();
                        vBox.getChildren().add(imageView);
                        vbox.getChildren().add(vBox);

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }*/

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HBox box = new HBox();

                        if (fulmsg.toString().endsWith(".png") || fulmsg.toString().endsWith(".jpg")){
                            ImageView imageView = new ImageView();
                            imageView.setImage(new Image(new File(String.valueOf(fulmsg)).toURI().toString()));
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            Text text = new Text(lblName.getText() + " : ");
                            vbox.getChildren().add(text);
                            vbox.getChildren().add(imageView);
                        }else {
                            Text text = new Text(msg + "\n");
                            TextFlow textFlow = new TextFlow(text);
                            box.getChildren().add(textFlow);
                            vbox.getChildren().add(text);
                        }
                    }
                });

            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOnAction(ActionEvent event) throws IOException {
        String msg = txtType.getText();

        /*writer.println(lblName.getText() + ": " + msg);
        txtChatArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtChatArea.appendText("Me: " + msg + "\n\n");
        txtType.setText("");
        if(msg.equalsIgnoreCase("Bye") || msg.equalsIgnoreCase("Exit")) {
            System.exit(0);
        }
*/
        writer.println(lblName.getText() + ": " + msg);
        writer.flush();
        vbox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        Text text = new Text("Me : " + msg + "\n");
        HBox box = new HBox();
        box.getChildren().add(text);
        vbox.getChildren().add(box);

        txtType.setText("");
        if(msg.equalsIgnoreCase("Bye") || msg.equalsIgnoreCase("Exit")) {
            System.exit(0);
        }

    }

    public void stickerOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        file = fileChooser.showOpenDialog(stage);

        if (file != null){
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Text text = new Text("Me : ");
            vbox.getChildren().add(text);
            vbox.getChildren().add(imageView);

            writer.println(lblName.getText() + " : " + file.toURI().toURL());
        }
    }

    public void emojiOnAction(ActionEvent event) {
        byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x81};
        String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
        txtType.setText(txtType.getText() + " "+ emoji);
    }
}
