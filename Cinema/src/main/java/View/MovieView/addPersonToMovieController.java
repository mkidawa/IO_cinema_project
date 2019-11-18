package View.MovieView;

import Controller.MovieManager;
import DBO.MovieDAO;
import DBO.PersonDAO;
import Model.Movie;
import Model.Person;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class addPersonToMovieController implements Initializable {
    @FXML private TableView<SimplePeople> table;
    @FXML private TableColumn<SimplePeople, Long> ID;
    @FXML private TableColumn<SimplePeople, String> Name;
    @FXML private TableColumn<SimplePeople, String> Lastname;
    @FXML private Button addPersonButton;
    private ObservableList<SimplePeople> list = FXCollections.observableArrayList();


    public ObservableList<SimplePeople> getList() {
        ObservableList<SimplePeople> list = FXCollections.observableArrayList();
        List<Person> persons = PersonDAO.getAll();
        for (int i=0; i<persons.size(); i++) {
            list.add(new SimplePeople(persons.get(i).getId(), persons.get(i).getFirstName(), persons.get(i).getLastName()));
        }
        return list;
    }
    public static class SimplePeople {
        private final SimpleLongProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Lastname;

        public SimplePeople(Long ID, String Name, String Lastname){
            this.ID = new SimpleLongProperty(ID);
            this.Name = new SimpleStringProperty(Name);
            this.Lastname = new SimpleStringProperty(Lastname);
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
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = getList();
        ID.setCellValueFactory(new PropertyValueFactory<SimplePeople, Long>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<SimplePeople, String>("Name"));
        Lastname.setCellValueFactory(new PropertyValueFactory<SimplePeople, String>("Lastname"));
        table.setItems(list);
    }

    public void onClickAddPerson() {
        SimplePeople p = table.getSelectionModel().getSelectedItem();
        List<Person> persons = PersonDAO.getAllById(p.getID());
        MovieManager.workingPersons.add(persons.get(0));
        System.out.println(MovieManager.workingPersons);
        //addMovieController.setPeopleInvolved();
    }
}
