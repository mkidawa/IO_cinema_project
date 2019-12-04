package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class UserSchedulerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    public void initialize() {
    }

    @FXML
    public void updateDate() {
        System.out.println(datePicker.getValue());
    }

}
