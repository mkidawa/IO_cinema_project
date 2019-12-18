package Model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import DBO.TimeTableDAO;
import Tools.Filter;
import Model.TimeTableExceptions.Params.AdsDurationOutOfRangeException;
import Model.TimeTableExceptions.Params.MinTimeIntervalOutOfRangeException;
import Model.TimeTableExceptions.Performance.TimeTableCreationException;
import Model.TimeTableExceptions.Performance.HallNotAvailableException;
import Model.TimeTableExceptions.Performance.MovieNotAvailableException;
import static View.TimetableModule.Util.Constants.MAX_ADS_VALUE;
import static View.TimetableModule.Util.Constants.MAX_PERFORMANCE_GAP_VALUE;
import static View.TimetableModule.Util.Constants.MIN_ADS_VALUE;
import static View.TimetableModule.Util.Constants.MIN_PERFORMANCE_GAP_VALUE;

@Entity
@Table(name = "TimeTable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PerformanceId")
    @Getter
    @Setter
    private Performance performance;

    @Column(name = "PerformanceDate")
    @Getter
    @Setter
    private Timestamp performanceDate;

    public TimeTable() {}

    public TimeTable(Performance performance, Timestamp performanceDate, long id) throws TimeTableCreationException{
        /* prepare related performance begin and end timestamps */
        Calendar myBegin = Calendar.getInstance();
        myBegin.setTime(performanceDate);
        Calendar myEnd = Calendar.getInstance();
        myEnd.setTime(performanceDate);
        myEnd.add(Calendar.SECOND, (int)performance.getDuration().getSeconds());

        List<TimeTable> allTimeTables = TimeTableDAO.getAll();
        boolean conflict = allTimeTables.stream()
            /* get all in the same hall (skip myself if I exists) */
            .filter(timeTable -> {
                if(timeTable.getId() == id)
                    return false;
                return timeTable.getPerformance().getHall().getId() == performance.getHall().getId();
            })
            /* get all taking place in the same time */
            .filter(timeTable -> {
                /* prepare checked performance begin and end timestamps */
                Calendar otherBegin = Calendar.getInstance();
                otherBegin.setTime(timeTable.getPerformanceDate());
                Calendar otherEnd = Calendar.getInstance();
                otherEnd.setTime(timeTable.getPerformanceDate());
                otherEnd.add(Calendar.SECOND, (int)timeTable.getPerformance().getDuration().getSeconds());
                return (otherBegin.after(myBegin) && otherBegin.before(myEnd))
                     || (otherEnd.after(myBegin) && otherEnd.before(myEnd));
            })
            .findAny()
            .isPresent();
        if(conflict){
            throw new HallNotAvailableException("Selected hall is not available in chosen time!");
        }
        this.performance = performance;
        this.performanceDate = performanceDate;

        if(id > 0){
            this.Id = id;
        }
    }

    public TimeTable(Performance performance, Timestamp performanceDate) throws TimeTableCreationException{
        this(performance, performanceDate, 0);
    }

    public static void setAdsDuration(Integer value) throws AdsDurationOutOfRangeException {
        if (value >= MIN_ADS_VALUE && value <= MAX_ADS_VALUE) {
            TimeTableDAO.updateAdsDuration(value);
        } else {
            throw new AdsDurationOutOfRangeException();
        }
    }

    public static void setMinTimeInterval(Integer value) throws MinTimeIntervalOutOfRangeException {
        if (value >= MIN_PERFORMANCE_GAP_VALUE && value <= MAX_PERFORMANCE_GAP_VALUE) {
            TimeTableDAO.updateMinTimeInterval(value);
        } else {
            throw new MinTimeIntervalOutOfRangeException();
        }
    }
}
