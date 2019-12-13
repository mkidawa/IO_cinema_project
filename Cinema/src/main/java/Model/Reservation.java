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
    @JoinColumn(name = "PerformanceId")
    @Getter
    @Setter
    private Performance performance;

    @Column(name = "ReservationDate")
    @Getter
    @Setter
    private Timestamp reservationDate;


    public Reservation() {
    }

    public Reservation(int flgPosX, int flgPoxY, ReservationStatus reservationStatus, Performance performance, Timestamp reservationDate) {
        this.flgPosX = flgPosX;
        this.flgPoxY = flgPoxY;
        this.reservationStatus = reservationStatus;
        this.performance = performance;
        this.reservationDate = reservationDate;
    }
}
