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

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "MovieId")
    @Getter
    @Setter
    private Movie movie;

    @ManyToOne(cascade = {CascadeType.REFRESH})
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

    private Movie findProperMovie(String movieTitle) throws MovieNotAvailableException{
        Filter filter = new Filter();
        filter.addField("title", movieTitle);
        List<Movie> movies = MovieDAO.getAllByFilter(filter);
        if(movies.size() == 0){
            throw new MovieNotAvailableException("Can not find movie with title: " + movieTitle);
        }else if(!movies.get(0).getMovieState().getName().equals("Current")){
            throw new MovieNotAvailableException("Selected movie is not available (not in 'Current' state)!");
        }
        return movies.get(0);
    }

    private Hall findProperHall(long hallId) throws HallNotAvailableException{
        List<Hall> halls = HallDAO.getAllById(hallId);
        if(halls.size() == 0){
            throw new HallNotAvailableException("Can not find hall with id: " + hallId);
        }
        return halls.get(0);
    }

    private boolean checkPerformanceTypeMatchesHall(Hall hall, String performanceType){
        if(performanceType.equals("2D")){
            if(hall.getFlg2D() == 0)
                return false;
        }else if(performanceType.equals("3D")){
            if(hall.getFlg3D() == 0)
                return false;
        }else{
            if(hall.getFlgVR() == 0)
                return false;
        }
        return true;
    }

    private boolean checkPerformanceTypeMatchesMovie(Movie movie, String performanceType){
        if(performanceType.equals("2D")){
            if(movie.getFlg2D() == 0)
                return false;
        }else if(performanceType.equals("3D")){
            if(movie.getFlg3D() == 0)
                return false;
        }else{
            if(movie.getFlgVR() == 0)
                return false;
        }
        return true;
    }

    public Performance(String movieTitle, long hallId, String performanceType, long id) throws TimeTableCreationException{

        Movie movie = findProperMovie(movieTitle);
        Hall hall = findProperHall(hallId);

        if(!checkPerformanceTypeMatchesHall(hall, performanceType)){
            throw new HallNotAvailableException(performanceType + "films can not be shown in this hall!");
        }
        
        if(!checkPerformanceTypeMatchesMovie(movie, performanceType)){
            throw new MovieNotAvailableException("This film can not be shown in " + performanceType);
        }

        this.movie = movie;
        this.hall = hall;
        this.adsDuration = Duration.ofMinutes(TimeTableDAO.getAdsDuration());
        this.performanceType = performanceType;

        if(id > 0){
            this.Id = id;
        }
    }

    public Performance(String movieTitle, long hallId, String performanceType) throws TimeTableCreationException{
        this(movieTitle, hallId, performanceType, 0);
    }

    public Duration getDuration(){
        Duration movieDuration = Duration.ofSeconds(0);
        movieDuration = movieDuration.plusHours(movie.getMovieTime().getHours());
        movieDuration = movieDuration.plusMinutes(movie.getMovieTime().getMinutes());
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
