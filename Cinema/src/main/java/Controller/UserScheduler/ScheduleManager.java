package Controller.UserScheduler;

import DBO.ScheduleDAO;
import Model.DICT.ScheduleStatus;
import Model.Schedule;
import Model.Task;
import Model.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class ScheduleManager {

    private static List<ScheduleStatus> statuses = null;

    public List<ScheduleStatus> getStatuses() {
        if (statuses == null) {
            statuses = ScheduleDAO.getStatusList();
        }
        return statuses;
    }

    public ScheduleStatus getStatusById(int id) {
        if (statuses == null) {
            getStatuses();
        }
        for (ScheduleStatus status : statuses) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public List<Schedule> getSchedulesForDay(LocalDate date) {
        Timestamp from = Timestamp.valueOf(date.atStartOfDay());
        Timestamp to = Timestamp.valueOf(date.atStartOfDay().plusDays(1));
        List<Schedule> schedules = ScheduleDAO.getScheduleBetween(from, to);
        return schedules;
    }

    public Schedule scheduleTask(User user, Task task, Timestamp date) {
        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setTask(task);
        schedule.setScheduleStatus(this.getStatusById(1));
        schedule.setDateFrom(date);
        schedule.setDateTo(new Timestamp(date.getTime() + (60*60*1000)));
        ScheduleDAO.insertUpdate(schedule);
        return schedule;
    }

}
