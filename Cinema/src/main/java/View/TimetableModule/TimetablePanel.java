package View.TimetableModule;

import Controller.PerformanceManager;
import Model.Performance;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<SimplePerformance> table;

    @FXML
    private TableColumn<SimplePerformance, Long> id;

    @FXML
    private TableColumn<SimplePerformance, String> title;

    @FXML
    private TableColumn<SimplePerformance, String> description;

    @FXML
    private TableColumn<SimplePerformance, String> state;

    @FXML
    private TableColumn<SimplePerformance, String> genre;

    private ObservableList<SimplePerformance> list = FXCollections.observableArrayList();

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
        list.add(new SimplePerformance(1L, 1L, 1L, "cbduyvbuvjf"));
        id.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, Long>("id"));
        title.setCellValueFactory(
                new PropertyValueFactory<SimplePerformance, String>("title"));
//        description.setCellValueFactory(
//                new PropertyValueFactory<SimplePerformance, String>("description"));
//        state.setCellValueFactory(
//                new PropertyValueFactory<SimplePerformance, String>("state"));
//        genre.setCellValueFactory(
//                new PropertyValueFactory<SimplePerformance, String>("genre"));
        table.setItems(list);
//
//        table.setRowFactory(tv -> {
//            TableRow<SimplePerformance> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
//                        && event.getClickCount() == 2) {
//
//                    SimplePerformance clickedMovie = row.getItem();
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

//    public ObservableList<SimplePerformance> getDataFromDb() {
//        return null;
//    }

    public class SimplePerformance {

        private SimpleLongProperty id;
        private SimpleLongProperty movieId;
        private SimpleLongProperty hallId;
        private SimpleStringProperty title;

        public SimplePerformance(Long id, Long movieId, Long hallId, String title) {
            this.id = new SimpleLongProperty(id);
            this.movieId = new SimpleLongProperty(movieId);
            this.hallId = new SimpleLongProperty(hallId);
            this.title = new SimpleStringProperty(title);
        }

        public long getId() {
            return id.get();
        }

        public long getMovieId() {
            return movieId.get();
        }

        public long getHallId() {
            return hallId.get();
        }

        public String getTitle() {
            return title.get();
        }
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
