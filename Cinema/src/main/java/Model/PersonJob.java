package Model;

import Model.DICT.PersonType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MoviePersonPersonType")
public class PersonJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PersonId")
    @Getter
    @Setter
    private Person person;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PersonTypeId")
    @Getter
    @Setter
    private PersonType personType;

    public PersonJob() {
    }

    public PersonJob(Person person, PersonType personType) {
        this.person = person;
        this.personType = personType;
    }
}
