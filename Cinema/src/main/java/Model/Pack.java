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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PackHId", referencedColumnName = "Id")
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
        obj.setPack(this);
    }

    public Pack() {
        this.positions = new LinkedList<>();
    }

    public Pack(String name, BigDecimal price) {
        this.positions = new LinkedList<>();
        this.name = name;
        this.price = price;
    }

    public Pack(List<PackPO> positions, String name) {
        this.positions = positions;
        this.name = name;
    }
}
