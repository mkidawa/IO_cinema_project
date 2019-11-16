package View.MovieView;

import Controller.MovieManager;
import DBO.MovieStateDAO;
import DBO.MovieTypeDAO;
import DBO.PersonDAO;
import DBO.PersonTypeDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

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


        people = PersonTypeDAO.getAll();
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


        MovieManager.createMovie(flag2d, flag3d, flagVR, selectedGenre, selectedState, Title.getText(), Description.getText(), d, people);
    }

}
