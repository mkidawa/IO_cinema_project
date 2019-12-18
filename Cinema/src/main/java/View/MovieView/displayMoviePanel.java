package View.MovieView;

import Controller.MovieManager;
import Controller.StageManager;
import DBO.MovieDAO;
import Model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class displayMoviePanel implements Initializable {
    @FXML private Movie movie;

    @FXML private Label id;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label genre;
    @FXML private Label state;
    @FXML private Label duration;

    @FXML private CheckBox flg2D;
    @FXML private CheckBox flg3D;
    @FXML private CheckBox flgVR;

    @FXML private Label peopleName;
    @FXML private Label peopleSurname;
    @FXML private Label peopleType;

    @FXML private Button delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.movie = MovieManager.currentMovie;
        MovieManager.isEdit = false;

        id.setText(movie.getId() + "");
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        genre.setText(movie.getMovieType().getName());
        state.setText(movie.getMovieState().getName());
        duration.setText(movie.getMovieTime().toString());

        if(movie.getFlg2D() > 0) {
            flg2D.setSelected(true);
        } else {
            flg2D.setSelected(false);
        }

        if(movie.getFlg3D() > 0) {
            flg3D.setSelected(true);
        } else {
            flg3D.setSelected(false);
        }

        if(movie.getFlgVR() > 0) {
            flgVR.setSelected(true);
        } else {
            flgVR.setSelected(false);
        }

        for(int i=0; i<movie.getPeoples().size(); i++){
            peopleName.setText(peopleName.getText() + movie.getPeoples().get(i).getPerson().getFirstName() + '\n');
            peopleSurname.setText(peopleSurname.getText() + movie.getPeoples().get(i).getPerson().getLastName() + '\n');
            peopleType.setText(peopleType.getText() + movie.getPeoples().get(i).getPersonType().getName() + '\n');
        }
    }

    public void onClickDelete() {
        MovieDAO.delete(movie);
        MovieManager.currentMovie = null;

        closeAllStagesAndLoadNewMainStage();
    }

    public void onClickEdit() {
        MovieManager.isEdit = true;
        closeAllStagesAndLoadNewMainStage();
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/addMoviePanel/addMovie.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/addMoviePanel/addMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Add movie panel");
        stage.setResizable(false);
        stage.show();
    }

    public void closeAllStagesAndLoadNewMainStage() {
        try {
            Stage stage = (Stage) delete.getScene().getWindow();
            stage.close();

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(fxmlLoader);
            scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.setTitle("Main Movie Panel");
            mainStage.setResizable(false);
            mainStage.show();

            StageManager.mainStage.close();
            StageManager.mainStage = mainStage;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
