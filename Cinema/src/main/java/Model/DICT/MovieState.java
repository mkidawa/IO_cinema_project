package Model.DICT;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "DICTMovieState")
public class MovieState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    public MovieState() {
    }

    public MovieState(String name) {
        this.name = name;
    }
}
