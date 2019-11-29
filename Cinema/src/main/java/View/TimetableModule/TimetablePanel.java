package View.TimetableModule;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadFxmlStage(String fxmlPath, String fxmlStylePath, String title) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        scene.getStylesheets().add(getClass().getResource(fxmlStylePath).toExternalForm());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
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
