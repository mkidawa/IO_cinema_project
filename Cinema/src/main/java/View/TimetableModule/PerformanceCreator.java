package View.TimetableModule;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

import Controller.TimeTableController;
import DBO.HallDAO;
import DBO.MovieDAO;
import Model.Hall;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import Model.TimeTableExceptions.Performance.HallNotAvailableException;
import Model.TimeTableExceptions.Performance.MovieNotAvailableException;
import Model.TimeTableExceptions.Performance.TimeTableCreationException;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.FxmlUtilControls;
import View.TimetableModule.Util.PopOutWindow;
import static View.TimetableModule.Util.Constants.INITIAL_HOUR_VALUE;
import static View.TimetableModule.Util.Constants.INITIAL_MINUTE_VALUE;
import static View.TimetableModule.Util.Constants.MAX_HOUR_VALUE;
import static View.TimetableModule.Util.Constants.MAX_MINUTE_VALUE;
import static View.TimetableModule.Util.Constants.MIN_HOUR_VALUE;
import static View.TimetableModule.Util.Constants.MIN_MINUTE_VALUE;
import static View.TimetableModule.Util.Constants.SPINNER_STEP;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class PerformanceCreator implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxTitle;
    @FXML
    private ComboBox comboBoxHallNumber;
    @FXML
    private RadioButton flg2D;
    @FXML
    private RadioButton flg3D;
    @FXML
    private RadioButton flgVR;
    @FXML
    private DatePicker performanceDatePicker;
    @FXML
    private Spinner spinnerHour;
    @FXML
    private Spinner spinnerMinute;
    @FXML
    private Button confirmButton;

    private short flag2dValue;
    private short flag3dValue;
    private short flagVRValue;
    private long hallIdValue;
    private String titleValue;
    private LocalDateTime performanceDate;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private FxmlUtilControls fxmlUtilControls = new FxmlUtilControls();

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * METHOD SET MIN, MAX AND INITIAL VALUE OF SPINNERS
     */
    private void setSpinnersValue() {
        fxmlUtilControls.setIntegerSpinner(spinnerHour, MIN_HOUR_VALUE, MAX_HOUR_VALUE,
                INITIAL_HOUR_VALUE, SPINNER_STEP);
        fxmlUtilControls.setIntegerSpinner(spinnerMinute, MIN_MINUTE_VALUE, MAX_MINUTE_VALUE,
                INITIAL_MINUTE_VALUE, SPINNER_STEP);
    }

    /**
     * METHOD FILL COMBOBOXES WITH SELECTED MOVIE DATA
     */
    private void fillComboBoxes() {
        List<Movie> movies = MovieDAO.getAll();
        movies.forEach((it) -> comboBoxTitle.getItems().add(it.getTitle()));

        List<Hall> halls = HallDAO.getAll();
        halls.forEach((it) -> comboBoxHallNumber.getItems().add(it.getId()));
    }

    /**
     * METHOD GET VALUE FROM INPUT AND SAVE THEM TO CLASS FIELDS
     */
    private void getValueFromInputs() {
        flag2dValue = fxmlUtilControls.setFlagValue(flg2D);
        flag3dValue = fxmlUtilControls.setFlagValue(flg3D);
        flagVRValue = fxmlUtilControls.setFlagValue(flgVR);

        titleValue = (String) comboBoxTitle.getValue();
        hallIdValue = (Long) comboBoxHallNumber.getValue();
        LocalDate tmpDate = performanceDatePicker.getValue();
        performanceDate = LocalDateTime.of(tmpDate.getYear(), tmpDate.getMonth(),
                tmpDate.getDayOfMonth(), (int) spinnerHour.getValue(),
                (int) spinnerMinute.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSpinnersValue();
        fillComboBoxes();
        TimeTableController controller = TimeTableController.getInstance();

        if (controller.getIsEditable()) {
            TimeTable currentTimeTable = controller.getCurrentTimeTable();

            comboBoxTitle.getSelectionModel().select(currentTimeTable.getPerformance().getMovie().getTitle());
            comboBoxHallNumber.getSelectionModel().select(currentTimeTable.getPerformance().getHall().getId());
            fxmlUtilControls.setRadioButton(currentTimeTable.getPerformance().getMovie().getFlg2D(), flg2D);
            fxmlUtilControls.setRadioButton(currentTimeTable.getPerformance().getMovie().getFlg3D(), flg3D);
            fxmlUtilControls.setRadioButton(currentTimeTable.getPerformance().getMovie().getFlgVR(), flgVR);
            performanceDatePicker.setValue(currentTimeTable.getPerformanceDate().toLocalDateTime().toLocalDate());
            fxmlUtilControls.setSpinnerValue(spinnerHour, currentTimeTable.getPerformanceDate().getHours());
            fxmlUtilControls.setSpinnerValue(spinnerMinute, currentTimeTable.getPerformanceDate().getMinutes());

            controller.setIsEditable(false);
        }
    }

    /**
     * METHOD CHECKS IF ALL REQUIRED INPUTS ARE FILLED THEN SAVE TO DB AND RELOAD
     * TIMETABLE STAGE, IF NOT SHOW POP OUT MESSAGE
     *
     * @param mouseEvent
     */
    @FXML
    private void onClickConfirm(MouseEvent mouseEvent) {

        if (fxmlUtilControls.isOneRadioButtonSelected(flg2D, flg3D, flgVR)
                && fxmlUtilControls.isComboBoxesFilled(comboBoxTitle, comboBoxHallNumber)
                && fxmlUtilControls.isSpinnersFilled(spinnerHour, spinnerMinute)
                && performanceDatePicker.getValue() != null) {
            getValueFromInputs();

            try {
                TimeTableController controller = TimeTableController.getInstance();
                String performanceType;
                if(flag2dValue == 1)
                    performanceType = "2D";
                else if(flag3dValue == 1)
                    performanceType = "3D";
                else
                    performanceType = "VR";
                controller.addPerformanceToTimeTable(
                        titleValue,
                        hallIdValue,
                        performanceType,
                        Timestamp.valueOf(performanceDate)
                );

                FxmlStageSetup.reloadStage(confirmButton, TIMETABLE_PANEL_PATH,
                        TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
            } catch (HallNotAvailableException e) {
                popOutWindow.messageBox("Hall not available",
                        e.getMessage(),
                        Alert.AlertType.WARNING);
            } catch (MovieNotAvailableException e) {
                popOutWindow.messageBox("Movie not available",
                        e.getMessage(),
                        Alert.AlertType.WARNING);
            } catch (TimeTableCreationException e){
                //unused
            }
        } else {
            popOutWindow.messageBox("Empty Input Warning",
                    "Every input must be selected and exactly one movie type must be selected",
                    Alert.AlertType.WARNING);
        }
    }
}
