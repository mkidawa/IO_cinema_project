package Model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
                .append(hall, that.hall)
                .append(addTime, that.addTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(Id)
                .append(movie)
                .append(hall)
                .append(addTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Id", Id)
                .append("movie", movie)
                .append("hall", hall)
                .append("addTime", addTime)
                .toString();
    }
}
