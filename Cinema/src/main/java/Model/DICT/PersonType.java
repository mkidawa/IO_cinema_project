package Model.DICT;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "DICTPersonType")
public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    public PersonType() {
    }

    public PersonType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonType{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
