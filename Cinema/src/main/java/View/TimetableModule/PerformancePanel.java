package View.TimetableModule;

import Controller.TimeTableController;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.FxmlUtilControls;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
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

    private FxmlUtilControls fxmlUtilControls = new FxmlUtilControls();

    /*------------------------ METHODS REGION ------------------------*/
    private void prepareDisplayMode() {
        Movie movie = TimeTableController.getInstance()
                .getCurrentTimeTable().getPerformance().getMovie();

        fxmlUtilControls.setCheckBox(movie.getFlg2D(), flg2D);
        fxmlUtilControls.setCheckBox(movie.getFlg3D(), flg3D);
        fxmlUtilControls.setCheckBox(movie.getFlgVR(), flgVR);
    }

    private void prepareBasicInformation() {
        TimeTable currentTimeTable = TimeTableController.getInstance().getCurrentTimeTable();
        Performance currentPerformance = currentTimeTable.getPerformance();
        LocalDateTime currentPerformanceLocalDateTime = currentTimeTable
                .getPerformanceDate().toLocalDateTime();

        labelPerformanceId.setText(Long.toString(currentPerformance.getId()));
        labelMovieId.setText(Long.toString(currentPerformance.getMovie().getId()));
        labelMovieTitle.setText(currentPerformance.getMovie().getTitle());
        labelHallId.setText(Long.toString(currentPerformance.getHall().getId()));

        labelDate.setText(currentPerformanceLocalDateTime.toLocalDate().toString());
        labelHour.setText(currentPerformanceLocalDateTime.getHour() + ":"
                + currentPerformanceLocalDateTime.getMinute());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareDisplayMode();
        prepareBasicInformation();
    }

    @FXML
    private void onClickEditButton(MouseEvent mouseEvent) {
        TimeTableController.getInstance().setIsEditable(true);
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
        TimeTableController controller = TimeTableController.getInstance();
        controller.deleteTimeTable(controller.getCurrentTimeTable());

        FxmlStageSetup.reloadStage(deleteButton, TIMETABLE_PANEL_PATH,
                TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
    }
}
