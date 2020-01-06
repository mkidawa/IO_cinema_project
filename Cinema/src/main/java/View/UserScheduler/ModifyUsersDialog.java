package View.UserScheduler;

import Controller.UserScheduler.UserManager;
import Model.User;
import Tools.PermissionChecker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyUsersDialog implements Initializable {
    @FXML
    ListView<String> userList;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;

    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField code;
    @FXML
    private TextField hourlyRate;

    private UserManager userManager = new UserManager();

    private UserSchedulerController parent;
    private Stage stage = new Stage();

    ModifyUsersDialog(UserSchedulerController parent){
        try {
            this.parent = parent;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserScheduler/ModifyUsersDialog.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.setWidth(750);
            stage.setHeight(500);
            stage.setResizable(true);
            PermissionChecker permissions = new PermissionChecker();
//            if (!permissions.checkPermission("Tworzenie nowych uzytkownikow")) {
//                //taskChoice.setDisable(true);
//                //deleteButton.setDisable(true);
//            }
            stage.show();
        } catch (IOException e) {
            System.out.println("Something went wrong."+e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> data = FXCollections.observableArrayList();

        List< User > users = userManager.getAllUsers();
        for (int y = 0; y < users.size(); y++) {
           data.add((users.get(y).getFirstName()+" "+users.get(y).getLastName()).toString());
        }
        this.userList.setItems(data);  // = new ListView<String>(data);
        this.hourlyRate.setText("1.0");
        this.login.setDisable(true);


    }


    @FXML
    private void update() {
        Platform.runLater(() -> {

        });
        stage.close();
    }

    @FXML
    private void delete() {
        Platform.runLater(() -> {

        });
        stage.close();
    }

    @FXML
    private void cancel() {
        Platform.runLater(() -> {
            System.out.println(userList.getSelectionModel().getSelectedIndex());
        });
        stage.close();
    }


    public void handleListView(){
        ObservableList<String> data = userList.getItems();
        int index = 0;
        List<User> users = userManager.getAllUsers();
        for(int i = 0; i != data.size(); i++)
            if(userList.getSelectionModel().getSelectedItem().compareTo(data.get(i))==0){
                index = i;
                System.out.println(index);
                System.out.println();
                break;
            }

        this.name.setText(users.get(index).getFirstName());
        this.lastName.setText(users.get(index).getLastName());
        this.login.setText(users.get(index).getLogin());
//        this.password
//        this.code;



    }


}

