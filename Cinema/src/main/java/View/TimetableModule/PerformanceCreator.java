package View.TimetableModule;

import Controller.StageManager;
import DBO.HallDAO;
import DBO.MovieDAO;
import Model.Hall;
import Model.Movie;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Exception.Performance.HallNotAvailableException;
import View.TimetableModule.Exception.Performance.MovieNotAvailableException;
import View.TimetableModule.Exception.Performance.WrongHallTypeException;
import View.TimetableModule.Util.PopOutWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
     * CHECK IF EXACTLY ONE RADIO BUTTON IS SELECTED
     *
     * @param radioButtons
     * @return - IF ONE RETURN TRUE, ELSE RETURN FALSE
     */
    private boolean isOneRadioButtonSelected(RadioButton... radioButtons) {
        int numberOfSelectedRadioButtons = 0;

        for (RadioButton it : radioButtons) {
            if (it.isSelected()) {
                numberOfSelectedRadioButtons++;
            }
        }

        if (numberOfSelectedRadioButtons == 1) {
            return true;
        }

        return false;
    }

    /**
     * CHECKS IF COMBOBOXES ARE FILLED
     *
     * @param comboBoxes
     * @return
     */
    private boolean isComboBoxesFilled(ComboBox... comboBoxes) {
        for (ComboBox it : comboBoxes) {
            if (it.getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * METHOD CHECKS IF SPINNERS ARE FILLED IF SO RETURN TRUE, IF NOT RETURN FALSE
     *
     * @param spinners
     * @return
     */
    private boolean isSpinnersFilled(Spinner... spinners) {
        for (Spinner it : spinners) {
            if (it.getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * METHOD SET FLAG VALUE, IF SELECTED SET VALUE TO ONE, IF NOT TO 0
     *
     * @param radioButton
     * @return
     */
    private short setFlagValue(RadioButton radioButton) {
        if (radioButton.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * METHOD GET VALUE FROM INPUT AND SAVE THEM TO CLASS FIELDS
     */
    private void getValueFromInputs() {
        flag2dValue = setFlagValue(flg2D);
        flag3dValue = setFlagValue(flg3D);
        flagVRValue = setFlagValue(flgVR);

        titleValue = (String) comboBoxTitle.getValue();
        hallIdValue = (Long) comboBoxHallNumber.getValue();
        LocalDate tmpDate = performanceDatePicker.getValue();
        performanceDate = LocalDateTime.of(tmpDate.getYear(), tmpDate.getMonth(),
                tmpDate.getDayOfMonth(), (int) spinnerHour.getValue(),
                (int) spinnerMinute.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBoxes();
        spinnerHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_HOUR_VALUE, MAX_HOUR_VALUE, INITIAL_HOUR_VALUE, SPINNER_STEP));
        spinnerMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_MINUTE_VALUE, MAX_MINUTE_VALUE, INITIAL_MINUTE_VALUE, SPINNER_STEP));
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

        if (isOneRadioButtonSelected(flg2D, flg3D, flgVR)
                && isComboBoxesFilled(comboBoxTitle, comboBoxHallNumber)
                && isSpinnersFilled(spinnerHour, spinnerMinute)
                && performanceDatePicker.getValue() != null) {
            getValueFromInputs();

            try {
                // TODO ADD VALUE TO PERFORMANCE CONSTRUCTOR
                timeTable.addPerformance(new Performance());

                reloadStage(confirmButton, TIMETABLE_PANEL_PATH,
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
