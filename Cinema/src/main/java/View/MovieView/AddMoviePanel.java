package View.MovieView;

import Controller.MovieManager;
import DBO.PersonTypeDAO;
import Model.Movie;
import Model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    @FXML private RadioButton true2D;
    @FXML private RadioButton false2D;
    @FXML private RadioButton true3D;
    @FXML private RadioButton false3D;
    @FXML private RadioButton trueVR;
    @FXML private RadioButton falseVR;

    @FXML private Label peopleName;
    @FXML private Label peopleSurname;
    @FXML private Label peopleType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.movie = MovieManager.workingMovie;

        id.setText(movie.getId() + "");
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        genre.setText(movie.getMovieType().getName());
        state.setText(movie.getMovieState().getName());
        duration.setText(movie.getMovieTime().toString());

        if(movie.getFlg2D() > 0) {
            true2D.setSelected(true);
            false2D.setSelected(false);
        } else {
            true2D.setSelected(false);
            false2D.setSelected(true);
        }

        if(movie.getFlg3D() > 0) {
            true3D.setSelected(true);
            false3D.setSelected(false);
        } else {
            true3D.setSelected(false);
            false3D.setSelected(true);
        }

        if(movie.getFlgVR() > 0) {
            trueVR.setSelected(true);
            falseVR.setSelected(false);
        } else {
            trueVR.setSelected(false);
            falseVR.setSelected(true);
        }

        for(int i=0; i<movie.getPeoples().size(); i++){
            peopleName.setText(peopleName.getText() + movie.getPeoples().get(i).getPerson().getFirstName() + '\n');
            peopleSurname.setText(peopleSurname.getText() + movie.getPeoples().get(i).getPerson().getLastName() + '\n');
            peopleType.setText(peopleType.getText() + movie.getPeoples().get(i).getPersonType().getName() + '\n');
        }
    }
}
