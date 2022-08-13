package Controllers;

import Models.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    public Label lblSuccess;

    public void signUpOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        Users users = new Users(
                new RegisterServices().generateUserIds(),
                txtUsername.getText(),
                txtPassword.getText(),
                txtName.getText()
        );

        if (txtName.getText().equals("") || txtPassword.getText().equals("") || txtUsername.getText().equals("")) {
            lblSuccess.setText("All fields are required.!");
            lblSuccess.setStyle("-fx-text-fill: red");
        } else {
            if (checkBox.isSelected()){
                if (new RegisterServices().saveUsers(users)){
                    lblSuccess.setText("Successfully Registered.! Now you can log !");

                    txtUsername.clear();
                    txtPassword.clear();
                    txtName.clear();

                }else {
                    lblSuccess.setText("Try again.!");
                    lblSuccess.setStyle("-fx-text-fill: red");
                }
            }else {
                lblSuccess.setText("Please agree to the teams and policy.!");
                lblSuccess.setStyle("-fx-text-fill: red");
            }
        }
    }

    public void loginOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) registerContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
    }
}
