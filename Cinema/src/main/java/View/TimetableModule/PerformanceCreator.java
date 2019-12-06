package View.TimetableModule;

import Controller.PerformanceManager;
import DBO.HallDAO;
import DBO.MovieDAO;
import Model.Hall;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Exception.Performance.HallNotAvailableException;
import View.TimetableModule.Exception.Performance.MovieNotAvailableException;
import View.TimetableModule.Exception.Performance.WrongHallTypeException;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.FxmlUtilControlls;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

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
    private Spinner spinnerHour;
    @FXML
    private Spinner spinnerMinute;
    @FXML
    private DatePicker performanceDatePicker;
    @FXML
    private Button confirmButton;

    private short flag2dValue;
    private short flag3dValue;
    private short flagVRValue;
    private long hallIdValue;
    private String titleValue;
    private LocalDateTime performanceDate;

    private TimeTable timeTable = new TimeTable();
    private PopOutWindow popOutWindow = new PopOutWindow();
    private FxmlUtilControlls fxmlUtilControlls = new FxmlUtilControlls();

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * METHOD SET MIN, MAX AND INITIAL VALUE OF SPINNERS
     */
    private void setSpinnersValue() {
        fxmlUtilControlls.setIntegerSpinner(spinnerHour, MIN_HOUR_VALUE, MAX_HOUR_VALUE,
                INITIAL_HOUR_VALUE, SPINNER_STEP);
        fxmlUtilControlls.setIntegerSpinner(spinnerMinute, MIN_MINUTE_VALUE, MAX_MINUTE_VALUE,
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
        flag2dValue = fxmlUtilControlls.setFlagValue(flg2D);
        flag3dValue = fxmlUtilControlls.setFlagValue(flg3D);
        flagVRValue = fxmlUtilControlls.setFlagValue(flgVR);

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

        if (PerformanceManager.getIsEditable()) {
            Performance currentPerformance = PerformanceManager.getCurrentPerformance();
            comboBoxTitle.getSelectionModel().select(currentPerformance.getMovie().getTitle());
            comboBoxHallNumber.getSelectionModel().select(currentPerformance.getHall().getId());

            PerformanceManager.setIsEditable(false);
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
        // TODO REMOVE THIS
//        PerformanceDAO.insertUpdate(new Performance(new Movie((short) 1, (short) 1, (short) 1,
//                new MovieType("Comedy"), new MovieState("vfvfvf"), "WEEIA vs FTIMS",
//                "Only one true win(n)er", new Time(15)),
//                new Hall((short) 1, (short) 0, (short) 0, 5, 7, "acdcd",
//                        "nrinvnvklfdvklfdkvfkvkfn"), new Time(25)));

        if (fxmlUtilControlls.isOneRadioButtonSelected(flg2D, flg3D, flgVR)
                && fxmlUtilControlls.isComboBoxesFilled(comboBoxTitle, comboBoxHallNumber)
                && fxmlUtilControlls.isSpinnersFilled(spinnerHour, spinnerMinute)
                && performanceDatePicker.getValue() != null) {
            getValueFromInputs();

            try {
                // TODO ADD VALUE TO PERFORMANCE CONSTRUCTOR
                timeTable.addPerformance(new Performance());

                FxmlStageSetup.reloadStage(confirmButton, TIMETABLE_PANEL_PATH,
                        TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
            } catch (HallNotAvailableException e) {
                popOutWindow.messageBox("Hall not available",
                        "Hall is not available in selected time",
                        Alert.AlertType.WARNING);
            } catch (MovieNotAvailableException e) {
                popOutWindow.messageBox("Movie not available",
                        "Movie is currently not available",
                        Alert.AlertType.WARNING);
            } catch (WrongHallTypeException e) {
                popOutWindow.messageBox("Wrong hall type",
                        "Selected hall and type of movie don't match",
                        Alert.AlertType.WARNING);
            }

        } else {
            popOutWindow.messageBox("Empty Input Warning",
                    "Every input must be selected and exactly one movie type must be selected",
                    Alert.AlertType.WARNING);
        }
    }
}
