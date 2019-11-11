package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Hall")
public class Hall {
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

    @Getter
    @Setter
    private int flgX;

    @Getter
    @Setter
    private int flgY;

    @Getter
    @Setter
    @Column(name = "Name")
    private String name;

    @Getter
    @Setter
    @Column(name = "Description")
    private String description;

    public Hall() {
    }

    public Hall(short flg2D, short flg3D, short flgVR, int flgX, int flgY, String name, String description) {
        this.flg2D = flg2D;
        this.flg3D = flg3D;
        this.flgVR = flgVR;
        this.flgX = flgX;
        this.flgY = flgY;
        this.name = name;
        this.description = description;
    }
}
