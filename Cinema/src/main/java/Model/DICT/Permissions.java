package Model.DICT;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Id == that.Id &&
                code == that.code &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, code);
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
