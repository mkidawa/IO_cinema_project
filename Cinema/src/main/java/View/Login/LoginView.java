package View.Login;


import Controller.LoginController;
import View.Components.NumericKeyboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;

public class LoginView {
    @Getter
    @Setter
    private Scene scene;

    @FXML
    NumericKeyboard keyboard;

    public LoginView() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Login/LoginView.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
        scene.setFill(null);
    }

    public void closeApplication(ActionEvent actionEvent) {
        LoginController.closeApplication();
    }

}
