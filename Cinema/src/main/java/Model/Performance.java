package Model;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import DBO.HallDAO;
import DBO.MovieDAO;
import DBO.TimeTableDAO;
import Model.TimeTableExceptions.Performance.HallNotAvailableException;
import Model.TimeTableExceptions.Performance.MovieNotAvailableException;
import Model.TimeTableExceptions.Performance.TimeTableCreationException;
import Tools.Filter;
import javax.persistence.*;

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

    @Column(name = "AdsDuration")
    @Getter
    @Setter
    private Duration adsDuration;

    @Column(name = "PerformanceType")
    @Getter
    @Setter
    private String performanceType;

    public Performance() {}

    public Performance(String movieTitle, long hallId, String performanceType) throws TimeTableCreationException{
        /* find proper movie */
        Filter filter = new Filter();
        filter.addField("title", movieTitle);
        List<Movie> movies = MovieDAO.getAllByFilter(filter);
        if(movies.size() == 0){
            throw new MovieNotAvailableException("Can not find movie with title: " + movieTitle);
        }else if(!movies.get(0).getMovieState().getName().equals("Current")){
            throw new MovieNotAvailableException("Selected movie is not available (not in 'Current' state)!");
        }
        this.movie = movies.get(0);

        /* find proper hall */
        List<Hall> halls = HallDAO.getAllById(hallId);
        if(halls.size() == 0){
            throw new HallNotAvailableException("Can not find hall with id: " + hallId);
        }
        this.hall = halls.get(0);

        /* set adsDuration as current adsDuration system parameter value */
        this.adsDuration = Duration.ofMinutes(TimeTableDAO.getAdsDuration());

        /* set performance type if:
         *      - movie has this type available
         *      - hall has this type available
         */
        if(performanceType.equals("2D")){
            if(this.hall.getFlg2D() == 0)
                throw new HallNotAvailableException("2D films can not be shown in this hall!");
            if(this.movie.getFlg2D() == 0)
                throw new MovieNotAvailableException("This film can not be shown in 2D!");
            this.performanceType = performanceType;
        }else if(performanceType.equals("3D")){
            if(this.hall.getFlg3D() == 0)
                throw new HallNotAvailableException("3D films can not be shown in this hall!");
            if(this.movie.getFlg3D() == 0)
                throw new MovieNotAvailableException("This film can not be shown in 3D!");
            this.performanceType = performanceType;
        }else{
            if(this.hall.getFlgVR() == 0)
                throw new HallNotAvailableException("VR films can not be shown in this hall!");
            if(this.movie.getFlgVR() == 0)
                throw new MovieNotAvailableException("This film can not be shown in VR!");
            this.performanceType = performanceType;
        }
    }

    public Duration getDuration(){
        Duration movieDuration = Duration.ofSeconds(0);
        movieDuration.plusHours(movie.getMovieTime().getHours());
        movieDuration.plusMinutes(movie.getMovieTime().getMinutes());
        return adsDuration.plus(movieDuration);
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
                .append(adsDuration, that.adsDuration)
                .append(performanceType, that.performanceType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(Id)
                .append(movie)
                .append(hall)
                .append(adsDuration)
                .append(performanceType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Id", Id)
                .append("movie", movie)
                .append("hall", hall)
                .append("adsDuration", adsDuration)
                .append("performanceType", performanceType)
                .toString();
    }
}
