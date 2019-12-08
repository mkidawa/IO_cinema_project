package View.UserScheduler;

import Model.Task;
import Model.User;
import javafx.event.EventHandler;
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
        this.parent = parent;
        this.user = user;
        this.time = time;
        setAddingView();
    }

    private void setAddingView() {
        Label label = new Label("+");
        label.setOnMouseClicked(new TaskBoxOnClick());
        this.getChildren().clear();
        this.getChildren().add(label);
    }

    public void setTaskView(Task task) {
        Label name = new Label(task.getName());
        Label description = new Label(task.getDescription());
        this.getChildren().clear();
        this.getChildren().addAll(name, description);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private class TaskBoxOnClick implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            parent.openAssignTaskDialog(user, time);
        }
    }

}
