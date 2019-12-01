package View.TimetableModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerformancePanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    private Stage loadFxmlStage(String fxmlPath, String fxmlStylePath, String title) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        scene.getStylesheets().add(getClass().getResource(fxmlStylePath).toExternalForm());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();

        return stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onClickEditButton(MouseEvent mouseEvent) {

    }

    @FXML
    private void onClickDeleteButton(MouseEvent mouseEvent) {

    }
}
