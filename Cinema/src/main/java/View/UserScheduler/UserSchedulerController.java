package View.UserScheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class UserSchedulerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane scheduleTable;

    @FXML
    public void initialize() {
        // Clear layout
        scheduleTable.getColumnConstraints().clear();
        scheduleTable.getRowConstraints().clear();
        // Recreate layout
        for (int x = 0; x < 10; x++) {
            scheduleTable.getColumnConstraints().add(new ColumnConstraints());
        }
        for (int y = 0; y < 10; y++) {
            scheduleTable.getRowConstraints().add(new RowConstraints());
        }
        // Fill layout
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Button btn = new Button();
                btn.setText(x + "," + y);
                scheduleTable.add(btn, y, x);
            }
        }
    }

    @FXML
    public void updateDate() {
        System.out.println(datePicker.getValue());
    }

}
