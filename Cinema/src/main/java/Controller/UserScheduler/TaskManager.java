package Controller.UserScheduler;

import DBO.TaskDAO;
import Model.Task;

import java.util.List;

public class TaskManager {

    public List<Task> getAllTasks() {
        return TaskDAO.getAll();
    }

    public void addTask(String name, String desc) {
        Task task = new Task(name, desc);
        TaskDAO.insertUpdate(task);
    }

    public void updateTask(Task task) {
        TaskDAO.insertUpdate(task);
    }

    public void deleteTask(Task task) {
        TaskDAO.delete(task);
    }

}
