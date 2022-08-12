package Controllers;

import Models.Users;
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

public class ForgotPasswordFormController {
    public AnchorPane resetPWContext;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    public Label lblUserNameError;
    public Label lblPasswordError;
    public Label lblConfirmPasswordError;
    public JFXButton btnReset;
    public JFXButton btnBack;

    public void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (txtUsername.getText().equals("") || txtPassword.getText().equals("") || txtConfirmPassword.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "All fields are required.!").showAndWait();
        } else {

            Users users1 = new RegisterServices().searchByUserName(txtUsername.getText());

            Users users = new Users(
                    users1.getUserId(),
                    txtUsername.getText(),
                    txtPassword.getText(),
                    txtConfirmPassword.getText()
            );

            if (users1.getUserName().equalsIgnoreCase(txtUsername.getText())){
                if (txtPassword.getText().equals(txtConfirmPassword.getText())){
                    if (new RegisterServices().updateUsers(users)){
                        new Alert(Alert.AlertType.CONFIRMATION, "Reset your password.!").showAndWait();

                        txtUsername.clear();
                        txtPassword.clear();
                        txtConfirmPassword.clear();

                        Stage window = (Stage) resetPWContext.getScene().getWindow();
                        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
                    }else if (users1.getUserName().equalsIgnoreCase(txtUsername.getText()))
                }else {
                    new Alert(Alert.AlertType.WARNING, "Check your password.!").showAndWait();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Check username whether correct.!").showAndWait();
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) resetPWContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
    }
}
