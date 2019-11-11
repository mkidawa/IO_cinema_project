package Model.DICT;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "DICTPermission")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Code")
    @Getter
    @Setter
    private int code;

    public Permissions() {
    }

    public Permissions(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
