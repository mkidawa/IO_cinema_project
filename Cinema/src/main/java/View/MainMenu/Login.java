package View.MainMenu;

import Controller.LPermissionController;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Changed Errors for LoginException
import Tools.LoginException;
=======
import Controller.LoginController;
>>>>>>> Changes: Add support for custom events in keyboard
=======
import Controller.LoginController;
>>>>>>> master
import Tools.PermissionChecker;
import View.Components.ButtonWithImage;
import View.Components.NumericKeyboard;
import View.Program;
<<<<<<< HEAD
<<<<<<< HEAD
import javafx.event.ActionEvent;
<<<<<<< HEAD
=======
import Tools.PermissionChecker;
>>>>>>> Added panel for mainMenu
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> Changes: Add support for custom events in keyboard
=======
>>>>>>> master
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> master

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> master
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
<<<<<<< HEAD
=======
=======
>>>>>>> Added nicer login/menu
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
<<<<<<< HEAD

>>>>>>> Added panel for mainMenu
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> master
public class Login {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField logInTextField;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> master
    @Getter
    @Setter
    private Stage stage;

    @FXML
    private ButtonWithImage btnClose;

    @FXML
    private NumericKeyboard keyboard;


<<<<<<< HEAD
<<<<<<< HEAD
    public void logIn() throws IOException {
        System.out.println(passwordField.getText());
        System.out.println(logInTextField.getText());
        try {
            LPermissionController.getInstance().checkFailCounter();
            LPermissionController.getInstance().login(logInTextField.getText(), passwordField.getText());
            showMainMenu();
        }
        catch (Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }
    public void showMainMenu() throws IOException {

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader);
            Program.getPrimaryStage().setScene(scene);

<<<<<<< HEAD

=======
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> master
    public void logIn() throws IOException {
        System.out.println(passwordField.getText());
        System.out.println(logInTextField.getText());
        try {
            LPermissionController.getInstance().checkFailCounter();
            LPermissionController.getInstance().login(logInTextField.getText(), passwordField.getText());
            showMainMenu();
        }
        catch (Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }
    public void showMainMenu() throws IOException {

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.setResizable(false);
            stage.show();
        }
>>>>>>> Added panel for mainMenu
=======
//            stage.setScene(scene);

=======
>>>>>>> Created loginException
            Program.getPrimaryStage().setScene(scene);


>>>>>>> Added nicer login/menu

=======
>>>>>>> Changes: Add support for custom events in keyboard
=======
            Program.getPrimaryStage().setScene(scene);

>>>>>>> master
    }
    public void printPermission() throws IOException {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Added nicer login/menu
=======
>>>>>>> master

    }

    public void onConfirm(MouseEvent actionEvent) {
        System.out.println("Confirm");
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
=======
>>>>>>> Changes: Add support for custom events in keyboard
=======
>>>>>>> master
        try{
            LPermissionController.getInstance().checkFailCounter();
            LPermissionController.getInstance().login(keyboard.getCodeField().getText());
            showMainMenu();
        }
        catch (Error | Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD

=======
        if(LPermissionController.getInstance().login(codeField.getText()))
        {
=======
=======

>>>>>>> Zalozono limit na ilosc logowan
        try{
            LPermissionController.getInstance().checkFailCounter();
            LPermissionController.getInstance().login(codeField.getText());
>>>>>>> Changed return false for throwing error
            showMainMenu();
        }
        catch (Error | Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> Added nicer login/menu
=======
    public void checkFailCounter() throws Exception {
        if(LPermissionController.getInstance().getFailCounter()>=4)
        {
            throw new Exception("Przekroczony limit 5 prob logowania");
        }
    }
>>>>>>> Zalozono limit na ilosc logowan
=======

>>>>>>> Implemented 5 seconds timer for failCounter
    public void btnCancel_Click(ActionEvent actionEvent) {
        System.out.println("Cancel");
        codeField.setText("");
    }
    private void ClickButton(int num) {
        //System.out.println(num);
        codeField.setText(codeField.getText() + String.valueOf(num));

    }

    public void btn0_Click(ActionEvent actionEvent) {
        ClickButton(0);
    }

    public void btn1_Click(ActionEvent actionEvent) {
        ClickButton(1);
    }

    public void btn2_Click(ActionEvent actionEvent) {
        ClickButton(2);
    }

    public void btn3_Click(ActionEvent actionEvent) {
        ClickButton(3);
    }

    public void btn4_Click(ActionEvent actionEvent) {
        ClickButton(4);
    }

    public void btn5_Click(ActionEvent actionEvent) {
        ClickButton(5);
    }

    public void btn6_Click(ActionEvent actionEvent) {
        ClickButton(6);
    }

    public void btn7_Click(ActionEvent actionEvent) {
        ClickButton(7);
    }

    public void btn8_Click(ActionEvent actionEvent) {
        ClickButton(8);
    }

    public void btn9_Click(ActionEvent actionEvent) {
        ClickButton(9);
<<<<<<< HEAD
=======
>>>>>>> Added panel for mainMenu
=======
>>>>>>> Added nicer login/menu
=======
    public void closeApplication(MouseEvent mouseEvent){
        LoginController.closeApplication();
>>>>>>> Changes: Add support for custom events in keyboard
=======
    public void closeApplication(MouseEvent mouseEvent){
        LoginController.closeApplication();
>>>>>>> master
    }
}
