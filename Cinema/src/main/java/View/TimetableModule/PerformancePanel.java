package View.TimetableModule;

import Controller.PerformanceManager;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.FxmlUtilControls;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    private FxmlUtilControls fxmlUtilControls = new FxmlUtilControls();

    /*------------------------ METHODS REGION ------------------------*/
    private void prepareDisplayMode() {
        Movie movie = PerformanceManager.getCurrentPerformance().getMovie();

        fxmlUtilControls.setCheckBox(movie.getFlg2D(), flg2D);
        fxmlUtilControls.setCheckBox(movie.getFlg3D(), flg3D);
        fxmlUtilControls.setCheckBox(movie.getFlgVR(), flgVR);
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
        FxmlStageSetup.closeStage(editButton);
        FxmlStageSetup.loadFxmlStage(PERFORMANCE_CREATOR_PATH,
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

        FxmlStageSetup.reloadStage(deleteButton, TIMETABLE_PANEL_PATH,
                TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
    }
}
