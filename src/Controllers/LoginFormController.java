package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {

    public AnchorPane loginContext;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnForgotPassword;
    public JFXButton btnLogin;
    public JFXButton btnSignup;
    public Label lblUsernameError;
    public Label lblPasswordError;

    public void forgotPasswordOnAction(ActionEvent event) {
        System.out.println("dfd");
    }

    public void loginOnAction(ActionEvent event) {
        System.out.println("dfd");
    }

    public void signupOnAction(ActionEvent event) {
        System.out.println("dfd");
    }
}
