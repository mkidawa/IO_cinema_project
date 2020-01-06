package View.UserScheduler;


import Model.User;
import Tools.PermissionChecker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
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
    private PasswordField password;
    @FXML
    private PasswordField code;
    @FXML
    private TextField baseSalary;
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
            stage.setResizable(true);
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
        this.baseSalary.setText("1.0");
        this.hourlyRate.setText("1.0");
    }

    public boolean checkAllFields() {

        if (firstName.getText().length() < 1
                || surname.getText().length() < 1
                || login.getText().length() < 2
                || password.getText().length() < 2
                || code.getText().length() < 2
                || baseSalary.getText().length() == 0
                || hourlyRate.getText().length() == 0
        ) {
            return false;
        }
        if( baseSalary.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")
        && hourlyRate.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")
        ) {
            return true;
        }else return false;

    }


    @FXML
    private void create() {
        if(!checkAllFields()) return;
        String firstName = this.firstName.getText();
        String surname = this.surname.getText();
        String login = this.login.getText();
        String password = this.password.getText();
        String code = this.code.getText();
        BigDecimal baseSalary = new BigDecimal(this.baseSalary.getText());
        BigDecimal hourlyRate = new BigDecimal(this.hourlyRate.getText());


        User user = new User(firstName, surname, login, password, code, baseSalary, hourlyRate);
        Platform.runLater(() -> {
            parent.getUserManager().updateUser(user);
            parent.fillSchedule();
        });
        stage.close();
    }

    @FXML
    private void cancel() {
        stage.close();
    }


}
