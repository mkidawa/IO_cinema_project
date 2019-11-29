package View.TimetableModule;

import View.MovieView.MoviePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimetablePanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String PERFORMANCE_CREATOR_PATH =
            "/TimetableModule/MovieCreator/PerformanceCreator.fxml";
    public static final String PERFORMANCE_CREATOR_STYLE_PATH =
            "/TimetableModule/MovieCreator/PerformanceCreator.css";

    public static final String PERFORMANCE_PANEL_PATH =
            "/TimetableModule/PerformancePanel/PerformancePanel.fxml";
    public static final String PERFORMANCE_PANEL_STYLE_PATH =
            "/TimetableModule/PerformancePanel/PerformancePanel.css";

    public static final String SYSTEM_PARAMS_PATH =
            "/TimetableModule/SystemParamsPanel/SystemParamsPanel.fxml";
    public static final String SYSTEM_PARAMS_STYLE_PATH =
            "/TimetableModule/SystemParamsPanel/SystemParamsPanel.css";

    @FXML
    private GridPane pane;

    @FXML
    private TableView<MoviePanel.SimpleMovie> table;

    @FXML
    private TableColumn<MoviePanel.SimpleMovie, Long> id;

    @FXML
    private TableColumn<MoviePanel.SimpleMovie, String> title;

    @FXML
    private TableColumn<MoviePanel.SimpleMovie, String> description;

    @FXML
    private TableColumn<MoviePanel.SimpleMovie, String> state;

    @FXML
    private TableColumn<MoviePanel.SimpleMovie, String> genre;

    private ObservableList<MoviePanel.SimpleMovie> list = FXCollections.observableArrayList();

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onClickAddPerformance(MouseEvent mouseEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(PERFORMANCE_CREATOR_PATH)));
        scene.getStylesheets().add(getClass().getResource(PERFORMANCE_CREATOR_STYLE_PATH).toExternalForm());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Performance Creator");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onClickHelp(MouseEvent mouseEvent) {

    }
}
