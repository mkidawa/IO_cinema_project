package Model;

import Model.DICT.MovieState;
import Model.DICT.MovieType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MoviePersonPersonType",
            inverseJoinColumns = @JoinColumn(name = "MovieId", referencedColumnName = "Id"))
    @Getter
    @Setter
    private List<PersonJob> peoples;

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
    }
}
