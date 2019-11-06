package Model;

import java.time.Duration;
import java.util.List;

public class Movie {
    private long id;
    private String title;
    private String description;
    private MovieState state;
    private Duration movieTime;

    // 0 - cant be played in this format, 1 - can be;
    private boolean flg2D;
    private boolean flg3D;
    private boolean flgVR;

    private List<Director> directors;
    private List<Actor> actors;

    public Movie(long id, String title, String description, MovieState state,
                 Duration movieTime, boolean flg2D, boolean flg3D, boolean flgVR,
                 List<Director> directors, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.movieTime = movieTime;
        this.flg2D = flg2D;
        this.flg3D = flg3D;
        this.flgVR = flgVR;
        this.directors = directors;
        this.actors = actors;

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public MovieState getState() {
        return state;
    }

    public Duration getMovieTime() {
        return movieTime;
    }

    public boolean isFlg2D() {
        return flg2D;
    }

    public boolean isFlg3D() {
        return flg3D;
    }

    public boolean isFlgVR() {
        return flgVR;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return "Movie {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", movieTime=" + movieTime +
                ", flg2D=" + flg2D +
                ", flg3D=" + flg3D +
                ", flgVR=" + flgVR +
                ", directors=" + directors +
                ", actors=" + actors +
                '}';
    }
}
