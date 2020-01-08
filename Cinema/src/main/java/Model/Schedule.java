package Model;

import Model.DICT.ScheduleStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @ManyToOne
    @JoinColumn(name = "UsersId")
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "TaskId")
    @NotFound(action = NotFoundAction.IGNORE)
    @Getter
    @Setter
    private Task task;

    @ManyToOne
    @JoinColumn(name = "SheduleStatusId")
    @Getter
    @Setter
    private ScheduleStatus scheduleStatus;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateFrom")
    @Getter
    @Setter
    private Timestamp dateFrom;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateTo")
    @Getter
    @Setter
    private Timestamp dateTo;


    public Schedule() {
    }

    public Schedule(User user, Task task, ScheduleStatus scheduleStatus, Timestamp dateFrom, Timestamp dateTo) {
        this.user = user;
        this.task = task;
        this.scheduleStatus = scheduleStatus;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
