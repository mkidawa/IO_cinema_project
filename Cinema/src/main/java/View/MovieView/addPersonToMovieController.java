package View.MovieView;

import Controller.MovieManager;
import DBO.PersonDAO;
import DBO.PersonTypeDAO;
import Model.DICT.PersonType;
import Model.Person;
import Model.PersonJob;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addPersonToMovieController implements Initializable {
    @FXML private TableView<SimplePerson> table;
    @FXML private TableColumn<SimplePerson, Long> ID;
    @FXML private TableColumn<SimplePerson, String> Name;
    @FXML private TableColumn<SimplePerson, String> Lastname;
    @FXML private ComboBox Role;
    @FXML private Button addPersonButton;
    private ObservableList<SimplePerson> list = FXCollections.observableArrayList();
    private PersonType selectedPersonType = new PersonType();


    public ObservableList<SimplePerson> getList() {
        ObservableList<SimplePerson> list = FXCollections.observableArrayList();
        List<Person> persons = PersonDAO.getAll();
        for (int i=0; i<persons.size(); i++) {
            list.add(new SimplePerson(persons.get(i).getId(), persons.get(i).getFirstName(), persons.get(i).getLastName()));
        }
        return list;
    }
    public static class SimplePerson {
        private final SimpleLongProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Lastname;
        private SimpleStringProperty Type = null;

        public SimplePerson(Long ID, String Name, String Lastname, String Type){
            this.ID = new SimpleLongProperty(ID);
            this.Name = new SimpleStringProperty(Name);
            this.Lastname = new SimpleStringProperty(Lastname);
            this.Type = new SimpleStringProperty(Type);
        }

        public SimplePerson(Long ID, String Name, String Lastname){
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
        public String getType() { return Type.get();}
    }

    public static class SimplePersonType {
        private final SimpleLongProperty ID;
        private final SimpleStringProperty type;

        public SimplePersonType(Long ID, String type) {
            this.ID = new SimpleLongProperty(ID);
            this.type = new SimpleStringProperty(type);
        }
        public String getType() {
            return type.get();
        }
        public Long getID() {return  ID.get();}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = getList();
        ID.setCellValueFactory(new PropertyValueFactory<SimplePerson, Long>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<SimplePerson, String>("Name"));
        Lastname.setCellValueFactory(new PropertyValueFactory<SimplePerson, String>("Lastname"));
        table.setItems(list);

        ObservableList<SimplePersonType> personTypes = FXCollections.observableArrayList();
        List<PersonType> types = PersonTypeDAO.getAll();
        for (int i=0; i<types.size(); i++) {
            personTypes.add(new SimplePersonType(types.get(i).getId(), types.get(i).getName()));
        }
        List typeList = new ArrayList();
        for(int i=0; i<personTypes.size();i++){
            typeList.add(personTypes.get(i).getType());
        }
        Role.getItems().addAll(typeList);
    }

    public void createPersonJob(){
        SimplePerson p = table.getSelectionModel().getSelectedItem(); //person
        List<Person> persons = PersonDAO.getAllById(p.getID());
        selectedPersonType = new PersonType((String) Role.getValue()); //persontype

        PersonJob temp = new PersonJob(persons.get(0), selectedPersonType);
        MovieManager.workingPersons.add(temp);
    }

    public void onClickAddPerson() {
        createPersonJob();
        System.out.println(MovieManager.workingPersons.get(0));
        closeCurrent();
    }
    public void closeCurrent() {
            Stage stage = (Stage) addPersonButton.getScene().getWindow();
            stage.close();
    }

}
