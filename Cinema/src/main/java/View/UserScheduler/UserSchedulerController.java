package View.UserScheduler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserSchedulerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane scheduleTable;

    private Map<Button, Timestamp> buttonTimestampMap = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        // Set datePicker to today
        LocalDate today = LocalDate.now();
        Timestamp todayTime = Timestamp.valueOf(today.atStartOfDay());
        datePicker.setValue(today);
        // Clear layout
        scheduleTable.getColumnConstraints().clear();
        scheduleTable.getRowConstraints().clear();
        // Recreate layout
        for (int x = 0; x < 24; x++) {
            scheduleTable.getRowConstraints().add(new RowConstraints());
        }
        for (int y = 0; y < 10; y++) {
            scheduleTable.getColumnConstraints().add(new ColumnConstraints());
        }
        // Fill layout
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 24; x++) {
                Timestamp time = new Timestamp(todayTime.getTime() + (60*60*1000*x));
                if (y == 0) {
                    Label label = new Label(time.toString());
                    scheduleTable.add(label, y, x);
                } else {
                    Button btn = new Button();
                    buttonTimestampMap.put(btn, time);
                    btn.setOnAction(new ScheduleButtonClickHandler());
                    btn.setText("Add task");
                    scheduleTable.add(btn, y, x);
                }
            }
        }
    }

    @FXML
    public void updateDate() {
        System.out.println(datePicker.getValue());
    }

    private class ScheduleButtonClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Task time: "
                    + buttonTimestampMap.get(event.getSource()).toString());
        }
    }

}
