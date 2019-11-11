package Model;

import Model.DICT.MovieState;
import Model.DICT.MovieType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Getter
    @Setter
    private short flg2D;

    @Getter
    @Setter
    private short flg3D;

    @Getter
    @Setter
    private short flgVR;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MovieTypeId")
    @Getter
    @Setter
    private MovieType movieType;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MovieStateId")
    @Getter
    @Setter
    private MovieState movieState;

    @Column(name = "Title")
    @Getter
    @Setter
    private String title;

    @Column(name = "Description")
    @Getter
    @Setter
    private String description;

    @Column(name = "MovieTime")
    @Getter
    @Setter
    private Time movieTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @Getter
    @Setter
    private List<PersonJob> peoples;

    public void addPerson(PersonJob person) {
        peoples.add(person);
        person.setMovie(this);
    }

    public Movie() {
    }

    public Movie(short flg2D, short flg3D, short flgVR, MovieType movieType, MovieState movieState, String title, String description, Time movieTime) {
        this.flg2D = flg2D;
        this.flg3D = flg3D;
        this.flgVR = flgVR;
        this.movieType = movieType;
        this.movieState = movieState;
        this.title = title;
        this.description = description;
        this.movieTime = movieTime;
        this.peoples = new LinkedList<>();
    }

    public Movie(short flg2D, short flg3D, short flgVR, MovieType movieType, MovieState movieState, String title, String description, Time movieTime, List<PersonJob> peoples) {
        this.flg2D = flg2D;
        this.flg3D = flg3D;
        this.flgVR = flgVR;
        this.movieType = movieType;
        this.movieState = movieState;
        this.title = title;
        this.description = description;
        this.movieTime = movieTime;
        this.peoples = peoples;
    }

    @Override
    public String toString() {
        return "Movie" + '\n' + "{" +
                "Id=" + Id +
                ", flg2D=" + flg2D +
                ", flg3D=" + flg3D +
                ", flgVR=" + flgVR +
                ", movieType=" + movieType +
                ", movieState=" + movieState +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", movieTime=" + movieTime +
                ", peoples=" + peoples +
                '}';
    }
}
