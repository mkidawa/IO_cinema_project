package View.UserScheduler;

import Model.Task;
import Model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AssignTaskDialog implements Initializable {

    @FXML
    private ChoiceBox<Task> taskChoice;

    private UserSchedulerController parent;
    private User user;
    private Timestamp time;
    private Stage stage = new Stage();

    public AssignTaskDialog(UserSchedulerController parent, User user, Timestamp time) {
        try {
            this.parent = parent;
            this.user = user;
            this.time = time;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserScheduler/AssignTaskDialog.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Bob, do something
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.taskChoice.setConverter(new TaskStringConverter());
        this.taskChoice.getItems().addAll(
                parent.getTaskManager().getAllTasks()
        );
    }

    @FXML
    private void accept() {
        Platform.runLater(() -> {
            parent.getScheduleManager().scheduleTask(user, taskChoice.getValue(), time);
            parent.fillSchedule();
        });
        stage.close();
    }

    @FXML
    private void reject() {
        stage.close();
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
