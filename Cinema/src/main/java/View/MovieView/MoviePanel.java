package View.MovieView;

import DBO.MovieDAO;
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

    private ObservableList<SimpleMovie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        list = getList();
        id.setCellValueFactory(new PropertyValueFactory<SimpleMovie, Long>("id"));
        title.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<SimpleMovie, String>("description"));
        table.setItems(list);
    }

    public ObservableList<SimpleMovie> getList() {
        ObservableList<SimpleMovie> list = FXCollections.observableArrayList();
        List<Movie> movies = MovieDAO.getAll();
        for (int i=0; i<movies.size(); i++) {
            list.add(new SimpleMovie(movies.get(i).getId(), movies.get(i).getTitle(), movies.get(i).getDescription()));
        }
        return list;
    }

    public class SimpleMovie {
        private final SimpleLongProperty id;
        private final SimpleStringProperty title;
        private final SimpleStringProperty description;

        public SimpleMovie(long id, String title, String description) {
            this.id = new SimpleLongProperty(id);
            this.title = new SimpleStringProperty(title);
            this.description = new SimpleStringProperty(description);
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
    }
}
