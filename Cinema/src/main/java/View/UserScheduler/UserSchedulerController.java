package View.UserScheduler;

import Controller.UserScheduler.ScheduleManager;
import Controller.UserScheduler.TaskManager;
import Controller.UserScheduler.UserManager;
import Model.Schedule;
import Model.User;
import Tools.PermissionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserSchedulerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane scheduleTable;

    @FXML
    private HBox scheduleContainer;

    @FXML
    private Button createUser;

    @FXML
    private Button modifyUsers;

    @FXML
    private TaskManagerPanel taskManagerPanel;

    private UserManager userManager = new UserManager();
    private ScheduleManager scheduleManager = new ScheduleManager();
    private TaskManager taskManager = new TaskManager();
    private List<Schedule> scheduleList;

    @FXML
    public void initialize() {

        // Set datePicker to today
        LocalDate today = LocalDate.now();
        datePicker.setValue(today);

        // Setup controllers for components
        taskManagerPanel.setParent(this);

        // Modify view based on permissions
        PermissionChecker permissions = new PermissionChecker();
        if (!permissions.checkPermission("Zarzadzanie zadaniami")) {
            scheduleContainer.getChildren().remove(taskManagerPanel);
        }

        fillSchedule();

    }

    public void fillSchedule() {

        List<User> users = userManager.getAllUsers();
        Timestamp todayTime = Timestamp.valueOf(datePicker.getValue().atStartOfDay());
        scheduleList = scheduleManager.getSchedulesForDay(datePicker.getValue());

        // Clear layout
        scheduleTable.getChildren().clear();
        scheduleTable.getColumnConstraints().clear();
        scheduleTable.getRowConstraints().clear();

        // Recreate layout
        for (int x = 0; x < 25; x++) {
            scheduleTable.getRowConstraints().add(new RowConstraints());
        }
        for (int y = 0; y < users.size(); y++) {
            scheduleTable.getColumnConstraints().add(new ColumnConstraints());
        }

        // Fill layout
        for (int y = 1; y <= users.size(); y++) {
            User user = users.get(y - 1);
            Label label = new Label(user.getFirstName() + " " + user.getLastName() + " (" + user.getLogin() + ")");
            scheduleTable.add(label, y, 0);
        }
        for (int x = 1; x <= 24; x++) {
            Timestamp time = new Timestamp(todayTime.getTime() + (60*60*1000*(x-1)));
            LocalDateTime dateTime = time.toLocalDateTime();
            String hour = ((dateTime.getHour() < 10) ? "0" : "") + dateTime.getHour();
            String minutes = ((dateTime.getMinute() < 10) ? "0" : "") + dateTime.getMinute();
            Label label = new Label(hour + ":" + minutes);
            scheduleTable.add(label, 0, x);
        }
        for (int y = 1; y <= users.size(); y++) {
            for (int x = 1; x <= 24; x++) {
                User user = users.get(y - 1);
                Timestamp time = new Timestamp(todayTime.getTime() + (60*60*1000*(x-1)));
                TaskBox tb = new TaskBox(this, user, time);
                scheduleTable.add(tb, y, x);
                //Find matching schedule
                for (Schedule sched : scheduleList) {
                    if (sched.getDateFrom().equals(time) && sched.getUser().getId() == user.getId()) {
                        tb.setTaskView(sched);
                        break;
                    }
                }
            }
        }

    }

    public void openAssignTaskDialog(User user, Timestamp time) {
        AssignTaskDialog dialog = new AssignTaskDialog(this, user, time);
    }

    public void openModifyTaskDialog(Schedule schedule) {
        ModifyTaskDialog dialog = new ModifyTaskDialog(this, schedule);
    }

    @FXML
    public void updateDate() {
        fillSchedule();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    
    public void handleCreateUserButtonAction() {
        CreateNewUserDialog dialog = new CreateNewUserDialog(this);
    }

    public void handleModifyUsersButtonAction() {
        ModifyUsersDialog dialog = new ModifyUsersDialog(this);
    }

}
