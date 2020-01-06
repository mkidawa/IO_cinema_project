package View.UserScheduler;

import Model.DICT.ScheduleStatus;
import Model.Schedule;
import Model.Task;
import Tools.PermissionChecker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewUserDialog implements Initializable {


    @FXML
    private Button cancel;
    @FXML
    private Button createUser;
    @FXML
    private TextField firstName;
    @FXML
    private TextField surname;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField code;
    @FXML
    private TextField hourlyRate;

    private UserSchedulerController parent;

    private Stage stage = new Stage();

    public CreateNewUserDialog(UserSchedulerController parent) {
        try {

            this.parent = parent;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserScheduler/CreateNewUserDialog.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.setWidth(750);
            stage.setHeight(500);
            stage.setResizable(false);
            PermissionChecker permissions = new PermissionChecker();
            if (!permissions.checkPermission("Tworzenie nowych uzytkownikow")) {
                //taskChoice.setDisable(true);
                //deleteButton.setDisable(true);
            }
            stage.show();
        } catch (IOException e) {
            // Bob, do something
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Permission to choose

    }

    @FXML
    private void create() {
        Platform.runLater(() -> {

        });
        stage.close();
    }

    @FXML
    private void cancel() {
        Platform.runLater(() -> {

        });
        stage.close();
    }






}
