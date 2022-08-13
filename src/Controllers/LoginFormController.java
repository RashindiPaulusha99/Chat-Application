package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
    public JFXPasswordField txtPassword;
    public JFXButton btnForgotPassword;
    public JFXButton btnLogin;
    public JFXButton btnSignup;
    public Label lblError;

    public void forgotPasswordOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/ForgotPasswordForm.fxml"))));
    }

    public void loginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (txtPassword.getText().equals("") || txtUsername.getText().equals("")){
            lblError.setText("All fields are required.!");
            lblError.setStyle("-fx-text-fill: red");
        }else {
            if (new RegisterServices().searchForLogin(txtUsername.getText(),txtPassword.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ClientForm.fxml"));
                Parent load = loader.load();
                ClientFormController controller=loader.<ClientFormController>getController();
                controller.setData(txtUsername.getText());
                Stage window = (Stage) loginContext.getScene().getWindow();
                window.setScene(new Scene(load));
            }else {
                lblError.setText("Check your username and password whether correct.!");
                lblError.setStyle("-fx-text-fill: red");
            }
        }
    }

    public void signupOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/RegisterForm.fxml"))));
    }
}
