package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

    public void resetOnAction(ActionEvent event) {

    }

    public void backOnAction(ActionEvent event) {

    }
}
