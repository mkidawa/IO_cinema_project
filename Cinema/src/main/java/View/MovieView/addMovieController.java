package View.MovieView;

import Controller.MovieManager;
import DBO.MovieDAO;
import DBO.MovieTypeDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class addMovieController implements Initializable {
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private ComboBox Genre;
    @FXML private ComboBox MovieState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //MovieManager.createMovie();
        List list = MovieTypeDAO.getAll();
        Genre.getItems().addAll(list);



    }
}
