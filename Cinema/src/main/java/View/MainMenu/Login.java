package View.MainMenu;

import Controller.LPermissionController;
import Tools.PermissionChecker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField logInTextField;
    public void logIn() throws IOException {
        System.out.println(passwordField.getText());
        System.out.println(logInTextField.getText());
        if(LPermissionController.getInstance().login(logInTextField.getText(), passwordField.getText())) {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.setResizable(false);
            stage.show();
        }

    }
    public void printPermission() throws IOException {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
    }
}
