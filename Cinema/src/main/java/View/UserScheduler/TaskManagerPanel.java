package View.UserScheduler;

import javafx.fxml.FXML;
import Controller.UserScheduler.TaskManager;
import Model.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TaskManagerPanel extends VBox implements Initializable {

    @FXML
    private TextField newTaskName;

    @FXML
    private TextField newTaskDesc;

    @FXML
    private VBox tasksListBox;

    private UserSchedulerController parent;

    private TaskManager taskManager = new TaskManager();

    public TaskManagerPanel() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserScheduler/TaskManagerPanelView.fxml"));
            fxmlLoader.setController(this);
            Node n = fxmlLoader.load();
            this.getChildren().add(n);
            update();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update() {
        List<Task> tasks = taskManager.getAllTasks();
        tasksListBox.getChildren().clear();
        for (Task t : tasks) {
            // Create layout
            FlowPane pane = new FlowPane();
            TextField name = new TextField(t.getName());
            TextField desc = new TextField(t.getDescription());
            Button save = new Button("Zapisz");
            Button delete = new Button("Usuń");
            // Assign actions to buttons
            save.setOnMouseClicked((MouseEvent e) -> {
                modifyTask(t, name.getText(), desc.getText());
            });
            delete.setOnMouseClicked((MouseEvent e) -> {
                deleteTask(t);
            });
            pane.getChildren().addAll(name, desc, save, delete);
            tasksListBox.getChildren().add(pane);
        }
    }

    @FXML
    private void addTask() {
        taskManager.addTask(newTaskName.getText(), newTaskDesc.getText());
        newTaskName.setText("");
        newTaskDesc.setText("");
        update();
    }

    private void deleteTask(Task task) {
        taskManager.deleteTask(task);
        updateSchedule();
        update();
    }

    private void modifyTask(Task task, String name, String desc) {
        task.setName(name);
        task.setDescription(desc);
        taskManager.updateTask(task);
        updateSchedule();
    }

    private void addTask(String name, String desc) {
        taskManager.addTask(name, desc);
        update();
    }

    private void updateSchedule() {
        if (parent != null) {
            parent.fillSchedule();
        }
    }

    public void setParent(UserSchedulerController parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
