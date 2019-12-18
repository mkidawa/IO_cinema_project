package View.Components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NumericKeyboard extends GridPane implements Initializable {

    @Getter
    @Setter
    @FXML
    private PasswordField codeField;

    @FXML
    private Button btnConfirm;

    public NumericKeyboard() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Components/NumericKeyboard.fxml"));
            //fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            Node n = fxmlLoader.load();
            this.getChildren().add(n);
            this.setPickOnBounds(false);
            this.btnConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    onConfirmProperty().get().handle(mouseEvent);
                }
            });

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    private ObjectProperty<EventHandler<MouseEvent>> propertyOnConfirm = new SimpleObjectProperty<EventHandler<MouseEvent>>();

    public final ObjectProperty<EventHandler<MouseEvent>> onConfirmProperty() {
        return propertyOnConfirm;
    }

    public final void setOnConfirm(EventHandler<MouseEvent> handler) {
        propertyOnConfirm.set(handler);
    }

    public final EventHandler<MouseEvent> getOnConfirm() {
        return propertyOnConfirm.get();
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

    public void btnCancel_Click(ActionEvent actionEvent) {
        System.out.println("Cancel");
        codeField.setText("");
    }
}
