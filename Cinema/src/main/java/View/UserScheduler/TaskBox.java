package View.UserScheduler;

import Model.Schedule;
import Model.Task;
import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class TaskBox extends VBox implements Initializable {

    private UserSchedulerController parent;
    private User user;
    private Timestamp time;

    public TaskBox(UserSchedulerController parent, User user, Timestamp time) {
        super();
        this.getStylesheets().add(getClass().getResource("/UserScheduler/TaskBox.css").toExternalForm());
        this.parent = parent;
        this.user = user;
        this.time = time;
        setAddingView();
    }

    private void setAddingView() {
        Label label = new Label("+");
        this.getChildren().clear();
        this.getChildren().add(label);
        this.getStyleClass().clear();
        this.getStyleClass().add("unassigned");
        this.setOnMouseClicked((MouseEvent event) -> {
            parent.openAssignTaskDialog(user, time);
        });
    }

    public void setTaskView(Schedule schedule) {
        Task task = schedule.getTask();
        // If task was deleted
        if (task == null) {
            task = new Task("<Zadanie usunięte>", "");
            task.setId(-1);
        }
        Label name = new Label(task.getName());
        Label description = new Label(task.getDescription());
        name.setPickOnBounds(false);
        description.setPickOnBounds(false);
        this.getChildren().clear();
        this.getChildren().addAll(name, description);
        this.getStyleClass().clear();
        switch ((int)schedule.getScheduleStatus().getId()) {
            case -1:
                this.getStyleClass().add("undefined");
                break;
            case 1:
                this.getStyleClass().add("assigned");
                break;
            case 2:
                this.getStyleClass().add("closed");
                break;
            case 3:
                this.getStyleClass().add("annuled");
                break;
        }
        this.setOnMouseClicked((MouseEvent event) -> {
            parent.openModifyTaskDialog(schedule);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
