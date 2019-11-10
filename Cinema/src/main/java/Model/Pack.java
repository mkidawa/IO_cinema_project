package Model;

import lombok.Getter;
import lombok.Setter;
import lombok.var;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "PackH")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PackPO",
            joinColumns = @JoinColumn(name = "PackHId", referencedColumnName = "Id"))
    @Getter
    @Setter
    private List<PackPO> positions;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "_Price", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    private BigDecimal price;

    public void add(PackPO obj) {
        this.positions.add(obj);
        BigDecimal temp = new BigDecimal(0);
        for (PackPO item : positions) {
            temp = temp.add(item.getPrice());
        }
        price = temp.setScale(2, RoundingMode.HALF_UP);
    }

    public Pack() {
        this.positions = new LinkedList<>();
    }

    public Pack(String name) {
        this.positions = new LinkedList<>();
        this.name = name;
    }

    public Pack(List<PackPO> positions, String name) {
        this.positions = positions;
        this.name = name;
    }
}
