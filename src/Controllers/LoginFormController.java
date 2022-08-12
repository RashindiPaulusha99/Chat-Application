package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public Label lblUsernameError;
    public Label lblPasswordError;

    public void forgotPasswordOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/ForgotPasswordForm.fxml"))));
    }

    public void loginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (txtPassword.getText().equals("") || txtUsername.getText().equals("")){
            new Alert(Alert.AlertType.WARNING, "All fields are required.!").showAndWait();
        }else {
            if (txtUsername.getText().equalsIgnoreCase("admin") && txtPassword.getText().equalsIgnoreCase("1234")) {
                Stage window = (Stage) loginContext.getScene().getWindow();
                window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/ServerForm.fxml"))));
            }else if (new RegisterServices().searchForLogin(txtUsername.getText(),txtPassword.getText())){
                Stage window = (Stage) loginContext.getScene().getWindow();
                window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/ClientOneForm.fxml"))));
            }else {
                new Alert(Alert.AlertType.WARNING, "Check your username and password whether correct.!").showAndWait();
            }
        }
    }

    public void signupOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/RegisterForm.fxml"))));
    }
}
