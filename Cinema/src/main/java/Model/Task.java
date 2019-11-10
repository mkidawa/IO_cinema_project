package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Description")
    @Getter
    @Setter
    private String description;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
