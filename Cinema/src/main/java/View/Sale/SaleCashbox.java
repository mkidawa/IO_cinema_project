package View.Sale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;

public class SaleCashbox {
    @Getter
    @Setter
    private Scene scene;

    public SaleCashbox() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleCashbox.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
            }

}
