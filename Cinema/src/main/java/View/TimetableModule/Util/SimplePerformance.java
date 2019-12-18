package View.TimetableModule.Util;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimplePerformance {

    /*------------------------ FIELDS REGION ------------------------*/
    private SimpleLongProperty id;
    private SimpleLongProperty movieId;
    private SimpleLongProperty hallId;
    private SimpleStringProperty title;
    private SimpleStringProperty date;
    private SimpleStringProperty startTime;

    /*------------------------ METHODS REGION ------------------------*/
    public SimplePerformance(Long id, Long movieId, Long hallId, String title,
                             String date, String startTime) {
        this.id = new SimpleLongProperty(id);
        this.movieId = new SimpleLongProperty(movieId);
        this.hallId = new SimpleLongProperty(hallId);
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.startTime = new SimpleStringProperty(startTime);
    }

    public long getId() {
        return id.get();
    }

    public long getMovieId() {
        return movieId.get();
    }

    public long getHallId() {
        return hallId.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getStartTime() {
        return startTime.get();
    }
}
