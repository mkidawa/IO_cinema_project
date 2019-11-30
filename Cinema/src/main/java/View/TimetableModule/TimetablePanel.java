package View.TimetableModule;

import Controller.PerformanceManager;
import Model.Performance;
import View.MovieView.MoviePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        list = getList();
//        id.setCellValueFactory(
//                new PropertyValueFactory<MoviePanel.SimpleMovie, Long>("id"));
//        title.setCellValueFactory(
//                new PropertyValueFactory<MoviePanel.SimpleMovie, String>("title"));
//        description.setCellValueFactory(
//                new PropertyValueFactory<MoviePanel.SimpleMovie, String>("description"));
//        state.setCellValueFactory(
//                new PropertyValueFactory<MoviePanel.SimpleMovie, String>("state"));
//        genre.setCellValueFactory(
//                new PropertyValueFactory<MoviePanel.SimpleMovie, String>("genre"));
//        table.setItems(list);
//
//        table.setRowFactory(tv -> {
//            TableRow<MoviePanel.SimpleMovie> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
//                        && event.getClickCount() == 2) {
//
//                    MoviePanel.SimpleMovie clickedMovie = row.getItem();
//                    List<Movie> movies = MovieDAO.getAllById(clickedMovie.getId());
//                    try {
//                        onClickRow(movies.get(0));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            return row;
//        });

    }

//    public ObservableList<MoviePanel.SimpleMovie> getList() {
//        return null;
//    }

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

//    class SimplePerformance {
//        private SimpleLongProperty movieId;
//        private SimpleLongProperty movieId;
//        private SimpleStringProperty
//    }
}
