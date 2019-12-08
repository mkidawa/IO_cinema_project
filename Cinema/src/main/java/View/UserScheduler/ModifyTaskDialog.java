package View.UserScheduler;

import Model.Schedule;
import Model.Task;
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
import java.util.List;
import java.util.ResourceBundle;

public class ModifyTaskDialog implements Initializable {

    @FXML
    private ChoiceBox<Task> taskChoice;

    private UserSchedulerController parent;
    private Schedule schedule;
    private Stage stage = new Stage();

    public ModifyTaskDialog(UserSchedulerController parent, Schedule schedule) {
        try {
            this.parent = parent;
            this.schedule = schedule;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserScheduler/ModifyTaskDialog.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.setWidth(300);
            stage.setHeight(170);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            // Bob, do something
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.taskChoice.setConverter(new TaskStringConverter());
        List<Task> allTasks = parent.getTaskManager().getAllTasks();
        this.taskChoice.getItems().addAll(allTasks);
        for (Task task : allTasks) {
            if (task.getId() == schedule.getTask().getId()) {
                taskChoice.setValue(task);
            }
        }
    }

    @FXML
    private void accept() {
        Platform.runLater(() -> {
            schedule.setTask(taskChoice.getValue());
            parent.getScheduleManager().updateSchedule(schedule);
            parent.fillSchedule();
        });
        stage.close();
    }

    @FXML
    private void delete() {
        Platform.runLater(() -> {
            parent.getScheduleManager().deleteSchedule(schedule);
            parent.fillSchedule();
        });
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
