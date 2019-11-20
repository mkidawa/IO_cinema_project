package View.MovieView;

import Controller.MovieManager;
import Controller.StageManager;
import DBO.MovieStateDAO;
import DBO.MovieTypeDAO;
import DBO.PersonDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.DICT.PersonType;
import Model.Person;
import Model.PersonJob;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML private TableView<SimplePersonwType> peopleInvolved;
    @FXML private TableColumn<addPersonToMovieController.SimplePerson, Long> ID;
    @FXML private TableColumn<addPersonToMovieController.SimplePerson, String> Name;
    @FXML private TableColumn<addPersonToMovieController.SimplePerson, String> Lastname;
    @FXML private TableColumn<addPersonToMovieController.SimplePerson, String> Type;

    private short flag2d;
    private short flag3d;
    private short flagVR;
    private MovieType selectedGenre = new MovieType();
    private MovieState selectedState = new MovieState();
    private Time d;
    private int dur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Lastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));


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
    }

    public static class SimplePersonwType {
        private final SimpleLongProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Lastname;
        private SimpleStringProperty Type;

        public SimplePersonwType(Long ID, String Name, String Lastname, String Type){
            this.ID = new SimpleLongProperty(ID);
            this.Name = new SimpleStringProperty(Name);
            this.Lastname = new SimpleStringProperty(Lastname);
            this.Type = new SimpleStringProperty(Type);
        }
        public String getName(){
            return Name.get();
        }
        public String getLastname(){
            return Lastname.get();
        }
        public long getID() {
            return ID.get();
        }
        public String getType() { return Type.get();}
    }
    public static ObservableList<SimplePersonwType> getList() {
        ObservableList<SimplePersonwType> list = FXCollections.observableArrayList();
        for (int i = 0; i < MovieManager.workingPersons.size(); i++) {
            Person person = MovieManager.workingPersons.get(i).getPerson();
            PersonType type = MovieManager.workingPersons.get(i).getPersonType();
            list.add(new SimplePersonwType(person.getId(), person.getFirstName(), person.getLastName(), type.getName()));
        }
        return list;
    }

    public void updateList (){
            if(!(MovieManager.workingPersons == null)) {
                peopleInvolved.setItems(getList());
            }
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

        List<PersonJob> involved = new ArrayList<>();
        for(int i=0; i<MovieManager.workingPersons.size(); i++) {
            Person person = MovieManager.workingPersons.get(i).getPerson();
            PersonType type = MovieManager.workingPersons.get(i).getPersonType();
            PersonJob newPerson = new PersonJob(person, type);
            involved.add(newPerson);
        }

        MovieManager.createMovie(flag2d, flag3d, flagVR, selectedGenre, selectedState, Title.getText(), Description.getText(), d, involved);
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
        updateList();
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
