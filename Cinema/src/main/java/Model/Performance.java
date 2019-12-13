package Model;

import java.sql.Timestamp;
import java.time.Duration;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Column(name = "MovieType")
    @Getter
    @Setter
    private String movieType;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "HallId")
    @Getter
    @Setter
    private Hall hall;

    @Column(name = "StartTime")
    @Getter
    @Setter
    private Timestamp startTime;

    @Column(name = "Duration")
    @Getter
    @Setter
    private Duration duration;

    public Performance() {}

    public Performance(Movie movie, String movieType, Hall hall, Timestamp startTime, Duration duration) {
        this.movie = movie;
        this.movieType = movieType;
        this.hall = hall;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Performance that = (Performance) o;

        return new EqualsBuilder()
                .append(Id, that.Id)
                .append(movie, that.movie)
                .append(movieType, that.movieType)
                .append(hall, that.hall)
                .append(startTime, that.startTime)
                .append(duration, that.duration)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(Id)
                .append(movie)
                .append(movieType)
                .append(hall)
                .append(startTime)
                .append(duration)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Id", Id)
                .append("movie", movie)
                .append("movieType", movieType)
                .append("hall", hall)
                .append("startTime", startTime)
                .append("duration", duration)
                .toString();
    }
}
