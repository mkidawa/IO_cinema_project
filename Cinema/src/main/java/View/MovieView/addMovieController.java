package View.MovieView;

import Controller.MovieManager;
import Controller.StageManager;
import DBO.MovieStateDAO;
import DBO.MovieTypeDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//todo exceptions for empty fields, passing selected person to peopleInvolved list
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
    @FXML
    private static ListView peopleInvolved;

    private short flag2d;
    private short flag3d;
    private short flagVR;
    private MovieType selectedGenre = new MovieType();
    private MovieState selectedState = new MovieState();
    private Time d;
    private int dur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //adding genres
        ObservableList<SimpleMovieGenre> movieTypes = FXCollections.observableArrayList();
        List<MovieType> types = MovieTypeDAO.getAll();
        for (int i=0; i<types.size(); i++) {
            movieTypes.add(new SimpleMovieGenre(types.get(i).getName()));
        }
        List typeList = new ArrayList();
        for(int i=0; i<movieTypes.size();i++){
            typeList.add(movieTypes.get(i).getGenre());
        }
        Genre.getItems().addAll(typeList);

        //adding states
        ObservableList<SimpleMovieState> movieStates = FXCollections.observableArrayList();
        List<MovieState> states = MovieStateDAO.getAll();
        for (int i=0; i<states.size(); i++) {
            movieStates.add(new SimpleMovieState(states.get(i).getName()));
        }
        List stateList = new ArrayList();
        for(int i=0; i<movieStates.size();i++){
            stateList.add(movieStates.get(i).getState());
        }
        MovieState.getItems().addAll(stateList);
        SpinnerValueFactory minutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(60, 200, 90, 2);
        Duration.setValueFactory(minutes);


        if(!(MovieManager.workingPersons ==null)) {
            ObservableList<addPersonToMovieController.SimplePeople> displayPeople = FXCollections.observableArrayList();
            for (int i = 0; i < MovieManager.workingPersons.size(); i++) {
                displayPeople.add(new addPersonToMovieController.SimplePeople(MovieManager.workingPersons.get(i).getId(), MovieManager.workingPersons.get(i).getFirstName(),
                        MovieManager.workingPersons.get(i).getLastName()));
            }
            peopleInvolved.setItems(displayPeople);
        }
    }

    public static void setPeopleInvolved(){
        ObservableList<addPersonToMovieController.SimplePeople> displayPeople = FXCollections.observableArrayList();
        for(int i=0;i<MovieManager.workingPersons.size();i++)
        {
            displayPeople.add(new addPersonToMovieController.SimplePeople(MovieManager.workingPersons.get(i).getId(), MovieManager.workingPersons.get(i).getFirstName(),
                    MovieManager.workingPersons.get(i).getLastName()));
        }
        peopleInvolved.setItems(displayPeople);
    }

    public class SimpleMovieGenre {
        private final SimpleStringProperty genre;

        public SimpleMovieGenre(String genre) {
            this.genre = new SimpleStringProperty(genre);
        }
        public String getGenre() {
            return genre.get();
        }
    }

    public class SimpleMovieState {
        private final SimpleStringProperty state;

        public SimpleMovieState(String state) {
            this.state = new SimpleStringProperty(state);
        }
        public String getState() {
            return state.get();
        }
    }

    public void onClickAddMovie() {
        selectedGenre = new MovieType((String) Genre.getValue());
        selectedState = new MovieState((String) MovieState.getValue());

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


    public void onClickAddPerson() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/addPersonToMoviePanel/addPersonToMoviePanel.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/addPersonToMoviePanel/addPersonToMoviePanel.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Add person panel");
        stage.setResizable(false);
        stage.show();
    }
}
