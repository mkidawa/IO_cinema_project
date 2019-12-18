package Controller.UserScheduler;

import DBO.TaskDAO;
import Model.Task;

import java.util.List;

public class TaskManager {

    private static List<Task> tasksList = null;

    public List<Task> getAllTasks() {
        if (tasksList == null) {
            tasksList = TaskDAO.getAll();
        }
        return tasksList;
    }

}
