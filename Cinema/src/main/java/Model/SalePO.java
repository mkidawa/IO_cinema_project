package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Entity
@Table(name = "SalePO")
public class SalePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PackHId")
    @Getter
    @Setter
    private Pack pack;

    @Column(name = "Amount", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "_Price", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    private BigDecimal price;

    public SalePO() {
    }

    public SalePO(Pack pack, BigDecimal amount) {
        this.pack = pack;
        this.amount = amount;
        this.price = pack.getPrice().multiply(this.amount).setScale(2, RoundingMode.HALF_UP);
    }
}
