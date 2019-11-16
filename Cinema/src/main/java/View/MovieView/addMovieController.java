package View.MovieView;

import Controller.MovieManager;
import Controller.StageManager;
import DBO.MovieStateDAO;
import DBO.MovieTypeDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class addMovieController implements Initializable {
    @FXML
    private TextField Title;
    @FXML
    private TextField Description;
    @FXML
    private ComboBox Genre;
    @FXML
    private ComboBox MovieState;
    @FXML
    private Spinner Duration;
    @FXML
    private RadioButton flg2D;
    @FXML
    private RadioButton flg3D;
    @FXML
    private RadioButton flgVR;
    @FXML
    private Button addMovieButton;



    private short flag2d;
    private short flag3d;
    private short flagVR;
    private MovieType selectedGenre = new MovieType();
    private MovieState selectedState = new MovieState();
    private Time d;
    private List people;
    private int dur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Genre.getItems().addAll(MovieTypeDAO.getAll());
        MovieState.getItems().addAll(MovieStateDAO.getAll());
        SpinnerValueFactory minutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(60, 200, 90, 2);
        Duration.setValueFactory(minutes);
    }

    public void onClickAddMovie() {
        selectedGenre = (MovieType) Genre.getValue();
        selectedState = (Model.DICT.MovieState) MovieState.getValue();

        if (flg2D.isSelected())
            flag2d = 1;
        else
            flag2d = 0;

        if (flg3D.isSelected())
            flag3d = 1;
        else
            flag3d = 0;

        if (flgVR.isSelected())
            flagVR = 1;
        else
            flagVR = 0;

        dur = (int) Duration.getValue();
        int h = dur/60;
        int m = dur - (h*60);
        String hstring = String.valueOf(h);
        String mstring = String.valueOf(m);

        StringBuilder sb = new StringBuilder();
        sb.append(hstring);
        sb.append(":");
        sb.append(mstring);
        sb.append(":");
        sb.append("00");
        String timeString = sb.toString();
        d = Time.valueOf(timeString);

        System.out.println("flag2d " + flag2d);
        System.out.println("flag3d " + flag3d);
        System.out.println("flagVR " + flagVR);
        System.out.println("genre " + selectedGenre);
        System.out.println("state " + selectedState);
        System.out.println("title " + Title.getText());
        System.out.println("desc " + Description.getText());
        System.out.println("duration " + d);


        MovieManager.createMovieWithoutPeople(flag2d, flag3d, flagVR, selectedGenre, selectedState, Title.getText(), Description.getText(), d);

        closeAllStagesAndLoadNewMainStage();
    }

    public void closeAllStagesAndLoadNewMainStage() {
        try {
            Stage stage = (Stage) addMovieButton.getScene().getWindow();
            stage.close();

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(fxmlLoader);
            scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

            StageManager.mainStage.close();
            StageManager.mainStage = mainStage;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
