package Model;

import Controller.PerformanceManager;
import DBO.PerformanceDAO;
import Tools.Filter;
import View.TimetableModule.Exception.AdsDurationOutOfRangeException;
import View.TimetableModule.Exception.MinTimeIntervalOutOfRangeException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    public TimeTable() {
    }

//    public TimeTable(Performance performance, Timestamp performanceDate) {
//        this.performance = performance;
//        this.performanceDate = performanceDate;
//    }

    public void addPerformance(Performance performance) {
//        if () {
//
//        } else {
//
//        }
    }

    public void removePerformance(Performance performance) {
//        if () {
//            PerformanceDAO.delete(performance);
//        } else {
//
//        }
    }

    public List<Performance> getPerformanceList() {
        return PerformanceDAO.getAll();
    }

    public List<Performance> getPerformanceListById(long id) {
        return PerformanceDAO.getAllById(id);
    }

    public List<Performance> getFilteredPerformanceList(Filter filter) {
        return PerformanceDAO.getAllByFilter(filter);
    }

    public void setAdsDuration(Integer value) throws AdsDurationOutOfRangeException {
        if (value >= MIN_ADS_VALUE && value <= MAX_ADS_VALUE) {
            PerformanceDAO.updateAdsDuration(value);
        } else {
            throw new AdsDurationOutOfRangeException();
        }
    }

    public void setMinTimeInterval(Integer value) throws MinTimeIntervalOutOfRangeException {
        if (value >= MIN_PERFORMANCE_GAP_VALUE && value <= MAX_PERFORMANCE_GAP_VALUE) {
            PerformanceDAO.updateMinTimeInterval(value);
        } else {
            throw new MinTimeIntervalOutOfRangeException();
        }
    }
}
