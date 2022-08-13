package Controllers;

import Models.Users;
import com.jfoenix.controls.JFXButton;
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

public class ForgotPasswordFormController {
    public AnchorPane resetPWContext;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    public JFXButton btnReset;
    public JFXButton btnBack;
    public Label lblError;

    public void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (txtUsername.getText().equals("") || txtPassword.getText().equals("") || txtConfirmPassword.getText().equals("")) {
            lblError.setText("All fields are required.!");
            lblError.setStyle("-fx-text-fill: red");
        } else {
            if (new RegisterServices().ifSearch(txtUsername.getText())){
                if (txtPassword.getText().equals(txtConfirmPassword.getText())){

                    Users users1 = new RegisterServices().searchByUserName(txtUsername.getText());

                    Users users = new Users(
                            users1.getUserId(),
                            txtUsername.getText(),
                            txtPassword.getText(),
                            txtConfirmPassword.getText()
                    );

                    if (new RegisterServices().updateUsers(users)){
                        lblError.setText("Successfully reset your password.!");
                        lblError.setStyle("-fx-text-fill: red");

                        txtUsername.clear();
                        txtPassword.clear();
                        txtConfirmPassword.clear();
                    }else {
                        lblError.setText("Try again.!");
                        lblError.setStyle("-fx-text-fill: red");
                    }
                }else {
                    lblError.setText("Check your password.!");
                    lblError.setStyle("-fx-text-fill: red");
                }
            }else {
                lblError.setText("Check username whether correct.!");
                lblError.setStyle("-fx-text-fill: red");
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) resetPWContext.getScene().getWindow();
        window.setScene(new Scene( FXMLLoader.load(getClass().getResource("../Views/LoginForm.fxml"))));
    }
}
