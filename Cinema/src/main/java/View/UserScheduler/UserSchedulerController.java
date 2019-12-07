package View.UserScheduler;

import Controller.UserScheduler.ScheduleManager;
import Controller.UserScheduler.TaskManager;
import Controller.UserScheduler.UserManager;
import Model.Task;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.StringConverter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserSchedulerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane scheduleTable;

    @FXML
    private ChoiceBox<User> userChoice;

    @FXML
    private ChoiceBox<Task> taskChoice;

    private Map<Button, Timestamp> buttonTimestampMap = new LinkedHashMap<>();

    private ScheduleManager scheduleManager = new ScheduleManager();

    private UserManager userManager = new UserManager();

    private TaskManager taskManager = new TaskManager();

    @FXML
    public void initialize() {

        List<User> users = userManager.getAllUsers();
        List<Task> tasks = taskManager.getAllTasks();

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

        // Setup User choice box
        userChoice.setConverter(new UserStringConverter());
        userChoice.getItems().addAll(users);

        // Setup Task choice box
        taskChoice.setConverter(new TaskStringConverter());
        taskChoice.getItems().addAll(tasks);

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
            Timestamp time = buttonTimestampMap.get(event.getSource());
            User user = userChoice.getValue();
            Task task = taskChoice.getValue();
            scheduleManager.scheduleTask(user, task, time);
        }
    }

    private class UserStringConverter extends StringConverter<User>
    {
        @Override
        public String toString(User user) {
            return user.getFirstName() + " " + user.getLastName() + " (" + user.getLogin() + ")";
        }

        @Override
        public User fromString(String string) {
            return null;
        }
    }

    private class TaskStringConverter extends StringConverter<Task>
    {
        @Override
        public String toString(Task task) {
            return task.getName();
        }

        @Override
        public Task fromString(String string) {
            return null;
        }
    }

}
