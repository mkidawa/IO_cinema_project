package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

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

    public TimeTable(Performance performance, Timestamp performanceDate) {
        this.performance = performance;
        this.performanceDate = performanceDate;
    }
}
