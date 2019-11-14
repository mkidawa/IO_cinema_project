package View.MovieView;

import Controller.MovieManager;
import DBO.MovieDAO;
import DBO.MovieStateDAO;
import DBO.MovieTypeDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.Movie;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import javax.swing.text.TabableView;
import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoviePanel implements Initializable {
    @FXML private GridPane pane;
    @FXML private TableView<SimpleMovie> table;
    @FXML private TableColumn<SimpleMovie, Long> id;
    @FXML private TableColumn<SimpleMovie, String> title;
    @FXML private TableColumn<SimpleMovie, String> description;
    @FXML private TableColumn<SimpleMovie, String> state;
    @FXML private TableColumn<SimpleMovie, String> genre;

    private ObservableList<SimpleMovie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        list = getList();
        id.setCellValueFactory(new PropertyValueFactory<SimpleMovie, Long>("id"));
        title.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("description"));
        state.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("state"));
        genre.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("genre"));
        table.setItems(list);

        table.setRowFactory(tv -> {
            TableRow<SimpleMovie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    SimpleMovie clickedMovie = row.getItem();
                    List<Movie> movies = MovieDAO.getAllById(clickedMovie.getId());
                    try {
                        onClickRow(movies.get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    public ObservableList<SimpleMovie> getList() {
        ObservableList<SimpleMovie> list = FXCollections.observableArrayList();
        List<Movie> movies = MovieDAO.getAll();
        for (int i=0; i<movies.size(); i++) {
            list.add(new SimpleMovie(movies.get(i).getId(), movies.get(i).getTitle(), movies.get(i).getDescription(),
                    movies.get(i).getMovieState().getName(), movies.get(i).getMovieType().getName()));
        }
        return list;
    }

    public void onClickRow(Movie clickedMovie) throws IOException {
        MovieManager.workingMovie = clickedMovie;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/AddMoviePanel/addMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }

    public class SimpleMovie {
        private final SimpleLongProperty id;
        private final SimpleStringProperty title;
        private final SimpleStringProperty description;
        private final SimpleStringProperty state;
        private final SimpleStringProperty genre;

        public SimpleMovie(long id, String title, String description, String state, String genre) {
            this.id = new SimpleLongProperty(id);
            this.title = new SimpleStringProperty(title);
            this.description = new SimpleStringProperty(description);
            this.state = new SimpleStringProperty(state);
            this.genre = new SimpleStringProperty(genre);
        }

        public long getId() {
            return id.get();
        }

        public String getTitle() {
            return title.get();
        }

        public String getDescription() {
            return description.get();
        }

        public String getState() {
            return state.get();
        }

        public String getGenre() {
            return genre.get();
        }
    }
}
