package View.TimetableModule;

import Controller.PerformanceManager;
import Controller.StageManager;
import Model.TimeTable;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class PerformancePanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private Button editButton;
    @FXML
    public Button deleteButton;

    private TimeTable timeTable = new TimeTable();
    private PopOutWindow popOutWindow = new PopOutWindow();

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * LOAD SELECTED STAGE AND ITS CSS STYLING
     *
     * @param fxmlPath
     * @param fxmlStylePath
     * @param title
     * @return
     * @throws IOException
     */
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

    /**
     * CLOSE CURRENT STAGE AND RELOAD SELECTED STAGE
     *
     * @param button
     * @param fxmlPath
     * @param fxmlStylePath
     * @param title
     */
    private void reloadStage(Button button, String fxmlPath, String fxmlStylePath, String title) {
        try {
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();
            Stage mainStage = loadFxmlStage(fxmlPath, fxmlStylePath, title);
            StageManager.mainStage.close();
            StageManager.mainStage = mainStage;
        } catch (IOException e) {
            popOutWindow.messageBox("Stage Loading Error",
                    "Cannot Properly Load Main Stage", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onClickEditButton(MouseEvent mouseEvent) {

    }

    /**
     * METHOD DELETES CHOSEN PERFORMANCE AND RELOAD TIMETABLE STAGE
     *
     * @param mouseEvent
     */
    @FXML
    private void onClickDeleteButton(MouseEvent mouseEvent) {
        timeTable.removePerformance(PerformanceManager.getCurrentPerformance());

        reloadStage(deleteButton, TIMETABLE_PANEL_PATH,
                TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
    }
}
