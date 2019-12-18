package View.TimetableModule;

import Controller.TimeTableController;
import Model.TimeTableExceptions.Params.AdsDurationOutOfRangeException;
import Model.TimeTableExceptions.Params.MinTimeIntervalOutOfRangeException;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.FxmlUtilControls;
import View.TimetableModule.Util.PopOutWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import java.net.URL;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.INITIAL_ADS_VALUE;
import static View.TimetableModule.Util.Constants.INITIAL_PERFORMANCE_GAP_VALUE;
import static View.TimetableModule.Util.Constants.MAX_ADS_VALUE;
import static View.TimetableModule.Util.Constants.MAX_PERFORMANCE_GAP_VALUE;
import static View.TimetableModule.Util.Constants.MIN_ADS_VALUE;
import static View.TimetableModule.Util.Constants.MIN_PERFORMANCE_GAP_VALUE;
import static View.TimetableModule.Util.Constants.SPINNER_STEP;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class SystemParamsPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private Spinner spinnerAdsDuration;
    @FXML
    private Spinner spinnerMinTimeInterval;
    @FXML
    private Button confirmButton;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private FxmlUtilControls fxmlUtilControls = new FxmlUtilControls();

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlUtilControls.setIntegerSpinner(spinnerAdsDuration, MIN_ADS_VALUE, MAX_ADS_VALUE,
                INITIAL_ADS_VALUE, SPINNER_STEP);
        fxmlUtilControls.setIntegerSpinner(spinnerMinTimeInterval, MIN_PERFORMANCE_GAP_VALUE,
                MAX_PERFORMANCE_GAP_VALUE, INITIAL_PERFORMANCE_GAP_VALUE, SPINNER_STEP);
    }

    @FXML
    private void onClickConfirmButton(ActionEvent actionEvent) {
        TimeTableController controller = TimeTableController.getInstance();
        if (fxmlUtilControls.isSpinnersFilled(spinnerAdsDuration, spinnerMinTimeInterval)) {
            try {
                controller.setAdsDuration((Integer) spinnerAdsDuration.getValue());
            } catch (AdsDurationOutOfRangeException e) {
                popOutWindow.messageBox("Invalid Ads Duration",
                        "Ads duration is not valid", Alert.AlertType.WARNING);
            }

            try {
                controller.setMinTimeInterval((Integer) spinnerMinTimeInterval.getValue());
            } catch (MinTimeIntervalOutOfRangeException e) {
                popOutWindow.messageBox("Invalid Minimal Time Interval",
                        "Minimal time interval is not valid ", Alert.AlertType.WARNING);
            }

            FxmlStageSetup.reloadStage(confirmButton, TIMETABLE_PANEL_PATH,
                    TIMETABLE_PANEL_STYLE_PATH, TIMETABLE_PANEL);
        }
    }
}
