package Model;

import Model.DICT.ScheduleStatus;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "UsersId")
    @Getter
    @Setter
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "TaskId")
    @Getter
    @Setter
    private Task task;

    @ManyToOne(cascade = {CascadeType.ALL})
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

    public Schedule(String name, Task task, ScheduleStatus scheduleStatus, Timestamp dateFrom, Timestamp dateTo) {
        this.name = name;
        this.task = task;
        this.scheduleStatus = scheduleStatus;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
