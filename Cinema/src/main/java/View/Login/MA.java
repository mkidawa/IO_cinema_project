package View.Login;

import Controller.LPermissionController;
import Tools.PermissionChecker;
import View.Components.ButtonWithImage;
import View.Components.NumericKeyboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import javafx.scene.control.TextField;
import java.io.IOException;

public class MA {
    @Getter
    @Setter
    private Scene scene;

    @FXML
    private NumericKeyboard keyboard;

    @FXML
    private ButtonWithImage closeBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField logInTextField;



    public MA() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Login/mA.fxml"));
//        Parent root = fxmlLoader.load();
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
        scene.setFill(null);
    }
    public void openMM() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Movie panel");
        stage.setResizable(false);
        stage.show();
    }
    public void logIn() throws IOException {
        System.out.println(passwordField.getText());
        System.out.println(logInTextField.getText());
        LPermissionController.getInstance().login(logInTextField.getText(),passwordField.getText());
    }
    public void printPermission() throws IOException {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
    }
}
