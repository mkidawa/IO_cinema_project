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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addPersonToMovieController implements Initializable {
    @FXML private TableView<SimplePerson> table;
    @FXML private TableColumn<SimplePerson, Long> ID;
    @FXML private TableColumn<SimplePerson, String> Name;
    @FXML private TableColumn<SimplePerson, String> Lastname;
    @FXML private TextField TextName;
    @FXML private TextField TextLastname;
    @FXML private TextField BirthDate;

    @FXML private ComboBox Role;
    @FXML private Button addPersonButton;
    private ObservableList<SimplePerson> list = FXCollections.observableArrayList();
    private PersonType selectedPersonType = new PersonType();
    private addMovieController controller;
    private ObservableList<SimplePersonType> personTypes = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = getList();
        ID.setCellValueFactory(new PropertyValueFactory<SimplePerson, Long>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<SimplePerson, String>("Name"));
        Lastname.setCellValueFactory(new PropertyValueFactory<SimplePerson, String>("Lastname"));
        table.setItems(list);

        //Listing personTypes in ComboBox
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
    public ObservableList<SimplePerson> getList() {
        ObservableList<SimplePerson> list = FXCollections.observableArrayList();
        List<Person> persons = PersonDAO.getAll();
        for (int i=0; i<persons.size(); i++) {
            list.add(new SimplePerson(persons.get(i).getId(), persons.get(i).getFirstName(), persons.get(i).getLastName()));
        }
        return list;
    }

    public void setController(addMovieController c){
        this.controller = c;
    }

    public void createNewPerson() {
        String name = TextName.getText();
        String lastname = TextLastname.getText();
        String born = BirthDate.getText();

        StringBuilder sb = new StringBuilder();
        sb.append(born);
        sb.append(" 00:00:00");
        Timestamp t = Timestamp.valueOf(String.valueOf(sb));

        Person p = MovieManager.createPerson(name, lastname, t);
        Long typeID;
        for(int i=0; i<personTypes.size();i++){
            if (personTypes.get(i).getType().equals((String) Role.getValue())) {
                typeID = personTypes.get(i).getID();
                List<PersonType> PT = PersonTypeDAO.getAllById(typeID);
                selectedPersonType = PT.get(0);
            }
        }
        PersonJob temp = new PersonJob(p, selectedPersonType);
        MovieManager.workingPersons.add(temp);
    }

    public void createPersonJob(){
        SimplePerson p = table.getSelectionModel().getSelectedItem(); //person
        List<Person> persons = PersonDAO.getAllById(p.getID());
        Long typeID;
        for(int i=0; i<personTypes.size();i++){
            if (personTypes.get(i).getType().equals((String) Role.getValue())) {
                typeID = personTypes.get(i).getID();
                List<PersonType> PT = PersonTypeDAO.getAllById(typeID);
                selectedPersonType = PT.get(0);
            }
        }

        PersonJob temp = new PersonJob(persons.get(0), selectedPersonType);
        MovieManager.workingPersons.add(temp);
    }

    public void onClickAddPerson() {
        if(TextName.getText().trim().isEmpty() && TextLastname.getText().trim().isEmpty()){
            createPersonJob();
            controller.updateList();
            closeCurrent();
        }else {
            createNewPerson();
            controller.updateList();
            closeCurrent();
        }
    }

    public void closeCurrent() {
        Stage stage = (Stage) addPersonButton.getScene().getWindow();
        stage.close();
    }

    public static class SimplePerson {
        private final SimpleLongProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Lastname;

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

}
