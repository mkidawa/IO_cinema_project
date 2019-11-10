package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MovieId")
    @Getter
    @Setter
    private Movie movie;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "HallId")
    @Getter
    @Setter
    private Hall hall;

    @Column(name = "AddTime")
    @Getter
    @Setter
    private Time addTime;

    public Performance() {
    }

    public Performance(Movie movie, Hall hall, Time addTime) {
        this.movie = movie;
        this.hall = hall;
        this.addTime = addTime;
    }
}
