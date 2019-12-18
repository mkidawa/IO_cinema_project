package View.Sale;

import Tools.PermissionChecker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;

public class SaleMenu {

    @Getter
    @Setter
    private Scene scene;

    Stage primaryStage;

    public SaleMenu(Stage primary) throws IOException {

        primaryStage = primary;
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleMenu.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());

    }

    public void cashStart() throws IOException {
        primaryStage.setScene(new SaleCashbox(primaryStage).getScene());
        primaryStage.show();
    }

    public void manageStart() throws IOException {
        PermissionChecker pc = new PermissionChecker();
        if(pc.checkPermission(7)) {
            primaryStage.setScene(new SaleManagement(primaryStage).getScene());
            primaryStage.show();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have no manager right's",
                    ButtonType.OK);
        }
    }
}
