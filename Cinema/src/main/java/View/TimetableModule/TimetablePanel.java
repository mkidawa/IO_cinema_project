package View.TimetableModule;

import Controller.PerformanceManager;
import Model.Performance;
import Model.TimeTable;
import View.TimetableModule.Util.PopOutWindow;
import View.TimetableModule.Util.SimplePerformance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_CREATOR_STYLE_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.PERFORMANCE_PANEL_STYLE_PATH;
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
     * METHOD PREPARES LIST OF SIMPLEPERFORMANCE BASED ON DATA FROM LIST OF PERFORMANCE AND
     * RETURN IT
     *
     * @return
     */
    private ObservableList<SimplePerformance> prepareSimplePerformanceList() {
        ObservableList<SimplePerformance> list = FXCollections.observableArrayList();

        for (Performance it : timeTable.getPerformanceList()) {
            list.add(new SimplePerformance(it.getId(), it.getMovie().getId(),
                    it.getHall().getId(), it.getMovie().getTitle(),

                    //TODO REMOVE THIS AND INSERT REAL DATA
                    new Date().toString(), new Date().toString()));
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
                        onClickRow(timeTable.getPerformanceListById(tableRow.getItem().getId()).get(0));
                    } catch (IndexOutOfBoundsException e) {
                        popOutWindow.messageBox("Database is empty",
                                "Database is empty, check if everything works properly",
                                Alert.AlertType.ERROR);
                    } catch (IOException e) {
                        popOutWindow.messageBox("Cannot Display Details",
                                e.getMessage(), Alert.AlertType.ERROR);
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
    private void onClickRow(Performance performance) throws IOException {
        PerformanceManager.setCurrentPerformance(performance);
        loadFxmlStage(PERFORMANCE_PANEL_PATH, PERFORMANCE_PANEL_STYLE_PATH, PERFORMANCE_PANEL);
    }

    @FXML
    private void onClickAddPerformance(MouseEvent mouseEvent) throws IOException {
        loadFxmlStage(PERFORMANCE_CREATOR_PATH,
                PERFORMANCE_CREATOR_STYLE_PATH, "Performance Creator");
    }

    @FXML
    private void onClickParameters(MouseEvent mouseEvent) throws IOException {
        loadFxmlStage(SYSTEM_PARAMS_PATH, SYSTEM_PARAMS_STYLE_PATH, "System Parameters");
    }
}
