package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Price", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    @Setter
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
