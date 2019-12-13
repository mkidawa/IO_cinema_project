package View.MainMenu;

import Controller.LPermissionController;
import Tools.PermissionChecker;
import View.Program;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
public class Login {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField logInTextField;
    @Getter
    @Setter
    private Stage stage;
    @FXML
    private PasswordField codeField;


    public void logIn() throws IOException {
        System.out.println(passwordField.getText());
        System.out.println(logInTextField.getText());
//        if(LPermissionController.getInstance().getFailCounter()>=5)
//        {
//            Alert alert = new Alert(AlertType.WARNING, "Przekroczony limit 5 prób logowania", ButtonType.YES);
//            alert.showAndWait();
//            return;
//        }

        try {
            checkFailCounter();
            LPermissionController.getInstance().login(logInTextField.getText(), passwordField.getText());
            showMainMenu();
        }
        catch (Error | Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }
    public void showMainMenu() throws IOException {

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader);
//            stage.setScene(scene);

            Program.getPrimaryStage().setScene(scene);
//            stage.setTitle("Main Menu");
//            stage.setResizable(false);
//            stage.show();


    }
    public void printPermission() throws IOException {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());

    }


    public void btnConfirm_Click(ActionEvent actionEvent) throws IOException {
        System.out.println("Confirm");

        try{
            checkFailCounter();
            LPermissionController.getInstance().login(codeField.getText());
            showMainMenu();
        }
        catch (Error | Exception e)
        {
            Alert alert = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.YES);
            alert.showAndWait();
        }
    }

    public void checkFailCounter() throws Exception {
        if(LPermissionController.getInstance().getFailCounter()>=4)
        {
            throw new Exception("Przekroczony limit 5 prob logowania");
        }
    }
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
    }
}
