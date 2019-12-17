package View.TimetableModule;

import DBO.TimeTableDAO;
import Controller.TimeTableController;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Util.FxmlStageSetup;
import View.TimetableModule.Util.PopOutWindow;
import View.TimetableModule.Util.SimplePerformance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR;
import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_STYLE_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL_STYLE_PATH;
import static View.TimetableModule.Util.Constants.SYSTEM_PARAMS;
import static View.TimetableModule.Util.Constants.SYSTEM_PARAMS_PATH;
import static View.TimetableModule.Util.Constants.SYSTEM_PARAMS_STYLE_PATH;

public class TimetablePanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private GridPane pane;
    @FXML
    private TableView<SimplePerformance> performanceTable;
    @FXML
    private TableColumn<SimplePerformance, Long> id;
    @FXML
    private TableColumn<SimplePerformance, Long> movieId;
    @FXML
    private TableColumn<SimplePerformance, Long> hallId;
    @FXML
    private TableColumn<SimplePerformance, String> movieTitle;
    @FXML
    private TableColumn<SimplePerformance, String> date;
    @FXML
    private TableColumn<SimplePerformance, String> startTime;

    private ObservableList<SimplePerformance>
            performanceObservableList = FXCollections.observableArrayList();
    private PopOutWindow popOutWindow = new PopOutWindow();

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * METHOD PREPARES LIST OF SIMPLEPERFORMANCE BASED ON DATA FROM LIST OF PERFORMANCE AND
     * RETURN IT
     *
     * @return
     */
    private ObservableList<SimplePerformance> prepareSimplePerformanceList() {
        ObservableList<SimplePerformance> list = FXCollections.observableArrayList();

        for (TimeTable timeTable : TimeTableDAO.getAll()) {
            StringBuilder date = new StringBuilder();
            date.append(timeTable.getPerformanceDate().getYear() + 1900);
            date.append("-");
            date.append(timeTable.getPerformanceDate().getMonth());
            date.append("-");
            date.append(timeTable.getPerformanceDate().getDay());
            StringBuilder time = new StringBuilder();
            time.append(timeTable.getPerformanceDate().getHours());
            time.append(":");
            time.append(timeTable.getPerformanceDate().getMinutes());
            list.add(new SimplePerformance(
                        timeTable.getId(),
                        timeTable.getPerformance().getMovie().getId(),
                        timeTable.getPerformance().getHall().getId(),
                        timeTable.getPerformance().getMovie().getTitle(),
                        date.toString(),
                        time.toString()
                    ));
        }

        return list;
    }

    /**
     * METHOD PREPARES CELLS IN TABLE AND LOAD DATA TO TABLE
     */
    private void prepareTable() {
        id.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, Long>("id"));
        movieId.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, Long>("movieId"));
        hallId.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, Long>("hallId"));
        movieTitle.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, String>("title"));
        date.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, String>("date"));
        startTime.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, String>("startTime"));

        performanceObservableList = prepareSimplePerformanceList();
        performanceTable.setItems(performanceObservableList);
    }

    /**
     * METHOD PREPARES ON CLICK ACTION ON ROWS IN TABLE
     */
    private void prepareOnClickRowAction() {
        performanceTable.setRowFactory(it -> {
            TableRow<SimplePerformance> tableRow = new TableRow<>();

            tableRow.setOnMouseClicked(event -> {
                if (!tableRow.isEmpty()
                        && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    try {
                        onClickRow(TimeTableDAO.getById(tableRow.getItem().getId()));
                    } catch (IndexOutOfBoundsException e) {
                        popOutWindow.messageBox("Database is empty",
                                "Database is empty, check if everything works properly",
                                Alert.AlertType.ERROR);
                    }
                }
            });

            return tableRow;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareTable();
        prepareOnClickRowAction();
    }

    @FXML
    private void onClickRow(TimeTable timeTable) {
        TimeTableController.getInstance().setCurrentTimeTable(timeTable);
        FxmlStageSetup.loadFxmlStage(PERFORMANCE_PANEL_PATH, PERFORMANCE_PANEL_STYLE_PATH,
                PERFORMANCE_PANEL);
    }

    @FXML
    private void onClickAddPerformance(MouseEvent mouseEvent) {
        TimeTableController.getInstance().setIsEditable(false);
        FxmlStageSetup.loadFxmlStage(PERFORMANCE_CREATOR_PATH,
                PERFORMANCE_CREATOR_STYLE_PATH, PERFORMANCE_CREATOR);
    }

    @FXML
    private void onClickParameters(MouseEvent mouseEvent) {
        FxmlStageSetup.loadFxmlStage(SYSTEM_PARAMS_PATH, SYSTEM_PARAMS_STYLE_PATH, SYSTEM_PARAMS);
    }
}
