package Model;

import Model.DICT.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Getter
    @Setter
    private int flgPosX;

    @Getter
    @Setter
    private int flgPoxY;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "StatusId")
    @Getter
    @Setter
    private ReservationStatus reservationStatus;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "TimeTableId")
    @Getter
    @Setter
    private TimeTable timeTable;

    @Column(name = "ReservationDate")
    @Getter
    @Setter
    private Timestamp reservationDate;


    public Reservation() {
    }

    public Reservation(int flgPosX, int flgPoxY, ReservationStatus reservationStatus, TimeTable timeTable, Timestamp reservationDate) {
        this.flgPosX = flgPosX;
        this.flgPoxY = flgPoxY;
        this.reservationStatus = reservationStatus;
        this.timeTable = timeTable;
        this.reservationDate = reservationDate;
    }
}
