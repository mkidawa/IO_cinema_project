package View.Login;


import Controller.LoginController;
import View.Components.ButtonWithImage;
import View.Components.NumericKeyboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
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
    private NumericKeyboard keyboard;

    @FXML
    private ButtonWithImage closeBtn;

    public LoginView() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Login/LoginView.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
        scene.setFill(null);
        closeBtn.setText("Zamknij aplikację");


    }

    public void closeApplication() {
        LoginController.closeApplication();
    }

}
