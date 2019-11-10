package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "TnAData")
public class TnAData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "UsersId")
    @Getter
    @Setter
    private long userId;

    @Column(name = "DateDay")
    @Getter
    @Setter
    private Date dateDay;

    @Column(name = "TimeFrom")
    @Getter
    @Setter
    private Timestamp timeFrom;

    @Column(name = "TimeTo")
    @Getter
    @Setter
    private Timestamp timeTo;

    public TnAData() {
    }

    public TnAData(long userId, Date dateDay, Timestamp timeFrom, Timestamp timeTo) {
        this.userId = userId;
        this.dateDay = dateDay;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }
}
