package View.TimetableModule.Util;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimplePerformance {

    /*------------------------ FIELDS REGION ------------------------*/
    private SimpleLongProperty id;
    private SimpleLongProperty movieId;
    private SimpleLongProperty hallId;
    private SimpleStringProperty title;

    /*------------------------ METHODS REGION ------------------------*/
    public SimplePerformance(Long id, Long movieId, Long hallId, String title) {
        this.id = new SimpleLongProperty(id);
        this.movieId = new SimpleLongProperty(movieId);
        this.hallId = new SimpleLongProperty(hallId);
        this.title = new SimpleStringProperty(title);
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
}
