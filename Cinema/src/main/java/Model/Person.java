package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "FirstName")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "LastName")
    @Getter
    @Setter
    private String lastName;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateBorn")
    @Getter
    @Setter
    private Timestamp bornDate;

    public Person() {
    }

    public Person(String firstName, String lastName, Timestamp bornDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
    }

    @Override
    public String toString() {
        return "\nPerson" + "{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bornDate=" + bornDate +
                '}';
    }
}
