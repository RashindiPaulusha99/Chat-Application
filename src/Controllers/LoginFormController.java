package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public AnchorPane loginContext;
    public JFXTextField txtUsername;
    public JFXButton btnLogin;
    public Label lblError;

    public void loginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (txtUsername.getText().equals("")){
            lblError.setText("All fields are required.!");
            lblError.setStyle("-fx-text-fill: red");
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ClientForm.fxml"));
            Parent load = loader.load();
            ClientFormController controller=loader.<ClientFormController>getController();
            controller.setData(txtUsername.getText());
            Stage window = (Stage) loginContext.getScene().getWindow();
            window.setScene(new Scene(load));
        }
    }

}
