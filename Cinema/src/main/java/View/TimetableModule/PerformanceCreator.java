package View.TimetableModule;

import Controller.StageManager;
import DBO.PerformanceDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.Hall;
import Model.Movie;
import Model.Performance;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class PerformanceCreator implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox title;
    @FXML
    private ComboBox hallNumber;
    @FXML
    private RadioButton flg2D;
    @FXML
    private RadioButton flg3D;
    @FXML
    private RadioButton flgVR;
    @FXML
    private Button confirmButton;

    private PopOutWindow popOutWindow = new PopOutWindow();

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
    private void onClickConfirm(MouseEvent mouseEvent) {
        // TODO REMOVE THIS
        PerformanceDAO.insertUpdate(new Performance(new Movie((short) 1, (short) 1, (short) 1,
                new MovieType("Comedy"), new MovieState("vfvfvf"), "WEEIA vs FTIMS",
                "Only one true win(n)er", new Time(15)),
                new Hall((short) 1, (short) 0, (short) 0, 5, 7, "acdcd",
                        "nrinvnvklfdvklfdkvfkvkfn"), new Time(25)));

        reloadStage(confirmButton, TIMETABLE_PANEL_PATH,
                TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);

    }
}
