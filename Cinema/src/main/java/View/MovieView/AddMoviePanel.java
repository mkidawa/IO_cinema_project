package View.MovieView;

import Controller.MovieManager;
import Model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMoviePanel implements Initializable {
    @FXML private Movie movie;
    @FXML private Label id;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label genre;
    @FXML private Label state;
    @FXML private Label duration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.movie = MovieManager.workingMovie;

        id.setText(movie.getId() + "");
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        genre.setText(movie.getMovieType().getName());
        state.setText(movie.getMovieState().getName());
        duration.setText(movie.getMovieTime().toString());
    }
}
