package View.TimetableModule;

import Controller.PerformanceManager;
import Controller.StageManager;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR;
import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_STYLE_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class PerformancePanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private Label labelPerformanceId;
    @FXML
    private Label labelMovieId;
    @FXML
    private Label labelMovieTitle;
    @FXML
    private Label labelHallId;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelHour;
    @FXML
    private CheckBox flg2D;
    @FXML
    private CheckBox flg3D;
    @FXML
    private CheckBox flgVR;
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
    private Stage loadFxmlStage(String fxmlPath, String fxmlStylePath, String title) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
            scene.getStylesheets().add(getClass().getResource(fxmlStylePath).toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();

            return stage;
        } catch (IOException e) {
            popOutWindow.messageBox("Stage Loading Error",
                    "Cannot Properly Load Main Stage", Alert.AlertType.ERROR);
        }

        return null;
    }

    /**
     * CLOSE CURRENT STAGE BASED ON SCENE GET FROM BUTTON
     *
     * @param button
     */
    private void closeStage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
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
        closeStage(button);
        Stage mainStage = loadFxmlStage(fxmlPath, fxmlStylePath, title);
        StageManager.mainStage.close();
        StageManager.mainStage = mainStage;
    }

    private void setCheckBox(short flag, CheckBox checkBox) {
        if (flag > 0) {
            checkBox.setSelected(true);
        } else {
            checkBox.setSelected(false);
        }
    }

    private void prepareDisplayMode() {
        Movie movie = PerformanceManager.getCurrentPerformance().getMovie();

        setCheckBox(movie.getFlg2D(), flg2D);
        setCheckBox(movie.getFlg3D(), flg3D);
        setCheckBox(movie.getFlgVR(), flgVR);
    }

    private void prepareBasicInformation() {
        Performance currentPerformance = PerformanceManager.getCurrentPerformance();

        labelPerformanceId.setText(Long.toString(currentPerformance.getId()));
        labelMovieId.setText(Long.toString(currentPerformance.getMovie().getId()));
        labelMovieTitle.setText(currentPerformance.getMovie().getTitle());
        labelHallId.setText(Long.toString(currentPerformance.getHall().getId()));

        //TODO REMOVE THIS AND INSERT REAL DATA
        labelDate.setText(new Date().toString());
        labelHour.setText(new Date().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareDisplayMode();
        prepareBasicInformation();
    }

    @FXML
    private void onClickEditButton(MouseEvent mouseEvent) {
        PerformanceManager.setIsEditable(true);
        closeStage(editButton);
        loadFxmlStage(PERFORMANCE_CREATOR_PATH,
                PERFORMANCE_CREATOR_STYLE_PATH, PERFORMANCE_CREATOR);
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
