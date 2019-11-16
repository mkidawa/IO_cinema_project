package View.MovieView;

import DBO.PersonDAO;
import DBO.PersonTypeDAO;
import Model.DICT.PersonType;
import Model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class addPersonToMovieController implements Initializable {
    @FXML
    private ListView peopleOfCinema;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Person> people = PersonDAO.getAll();
        List<PersonType> professions = PersonTypeDAO.getAll();
        ObservableList<SimplePeople> involved = FXCollections.observableArrayList();
        for(int i=0;i<people.size();i++){
            involved.add(new SimplePeople(people.get(i).getFirstName(), people.get(i).getLastName()));
        }
        ObservableList<String> peopleInv = FXCollections.observableArrayList();
        for(int i=0;i<involved.size();i++) {
            peopleInv.add(involved.get(i).getName());
        }
        peopleOfCinema.setItems(peopleInv);

    }

    public class SimplePeople {
        private final SimpleStringProperty name;
        private final SimpleStringProperty lastname;

        public SimplePeople(String name, String lastname){
            this.name = new SimpleStringProperty(name);
            this.lastname = new SimpleStringProperty(lastname);
        }
        public String getName() {
            StringBuilder s = new StringBuilder();
            s.append(name.get());
            s.append(lastname.get());
            return s.toString();
        }
    }
}
