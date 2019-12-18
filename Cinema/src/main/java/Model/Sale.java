package Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "SaleH")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "UserId")
    @Getter
    @Setter
    private long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SaleHId", referencedColumnName = "Id")
    @Getter
    @Setter
    private List<SalePO> positions;

    @Column(name = "SaleDate")
    @Getter
    @Setter
    private Timestamp saleDate;

    @Column(name = "_Price", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    private BigDecimal price;

    public void add(SalePO obj) {
        this.positions.add(obj);
        BigDecimal temp = new BigDecimal(0);
        for (SalePO item : positions) {
            temp = temp.add(item.getPrice());
        }
        price = temp.setScale(2, RoundingMode.HALF_UP);
        obj.setSale(this);
    }

    public Sale(long userId, Timestamp saleDate, BigDecimal price) {
        this.userId = userId;
        this.saleDate = saleDate;
        this.price = price;
        this.positions = new LinkedList<>();
    }

    public Sale() {
        this.positions = new LinkedList<>();
        this.saleDate = new Timestamp(new Date().getTime());
    }


    public Sale(long userId, List<SalePO> positions, Timestamp saleDate) {
        this.userId = userId;
        this.positions = positions;
        this.saleDate = saleDate;
    }

    public Sale(long userId, List<SalePO> positions, Timestamp saleDate, BigDecimal price) {
        this.userId = userId;
        this.positions = positions;
        this.saleDate = saleDate;
        this.price = price;
    }
}
