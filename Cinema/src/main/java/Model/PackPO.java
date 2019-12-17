package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Entity
@Table(name = "PackPO")
public class PackPO {
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


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ProductId")
    @Getter
    @Setter
    private Product product;

    @Column(name = "Amount", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "_Price", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    private BigDecimal price;

    public PackPO() {
    }

    public PackPO(Pack pack, Product product, BigDecimal amount) {
        this.pack = pack;
        this.product = product;
        this.amount = amount;
        this.price = product.getPrice().multiply(this.amount).setScale(2, RoundingMode.HALF_UP);
    }
}
