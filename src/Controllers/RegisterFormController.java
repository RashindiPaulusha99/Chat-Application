package Controllers;

import Models.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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

public class RegisterFormController {
    public AnchorPane registerContext;
    public JFXTextField txtName;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnSignUp;
    public JFXButton btnLogin;
    public JFXCheckBox checkBox;

    public void signUpOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        Users users = new Users(
                new RegisterServices().generateUserIds(),
                txtUsername.getText(),
                txtPassword.getText(),
                txtName.getText()
        );

        if (txtName.getText().equals("") || txtPassword.getText().equals("") || txtUsername.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "All fields are required.!").showAndWait();
        } else {
            if (checkBox.isSelected()){
                if (new RegisterServices().saveUsers(users)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered.!").showAndWait();

                    txtUsername.clear();
                    txtPassword.clear();
                    txtName.clear();

                    Stage window = (Stage) registerContext.getScene().getWindow();
                    window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
                }else {
                    new Alert(Alert.AlertType.WARNING, "Try again.!").showAndWait();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Please agree to the teams and policy.!").showAndWait();
            }
        }
    }

    public void loginOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) registerContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
    }
}
