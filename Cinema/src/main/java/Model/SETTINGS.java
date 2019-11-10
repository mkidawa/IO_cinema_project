package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "SETTINGS")
public class SETTINGS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Symbol")
    @Getter
    @Setter
    private String symbol;

    @Column(name = "Value")
    @Getter
    @Setter
    private String value;

    public SETTINGS() {
    }

    public SETTINGS(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }
}
