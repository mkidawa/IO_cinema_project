package View.Components;

import Controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NumericKeyboard extends GridPane implements Initializable {

    @FXML
    private PasswordField codeField;

    public NumericKeyboard() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Components/NumericKeyboard.fxml"));
            //fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            Node n = fxmlLoader.load();
            this.getChildren().add(n);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void btnConfirm_Click(ActionEvent actionEvent) {
        System.out.println("Confirm");
        LoginController.login(codeField.getText());
    }

    public void btnCancel_Click(ActionEvent actionEvent) {
        System.out.println("Cancel");
        codeField.setText("");
    }
}
