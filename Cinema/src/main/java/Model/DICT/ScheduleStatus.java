package Model.DICT;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "DICTScheduleStatus")
public class ScheduleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    public ScheduleStatus() {
    }

    public ScheduleStatus(String name) {
        this.name = name;
    }
}
