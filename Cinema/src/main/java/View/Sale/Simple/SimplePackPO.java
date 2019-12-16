package View.Sale.Simple;

import DBO.PackPoDAO;
import DBO.ProductDAO;
import Model.PackPO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SimplePackPO {
    private final SimpleLongProperty id;
    private final SimpleStringProperty product;
    private final SimpleIntegerProperty amount;
    private SimpleDoubleProperty price;

    public SimplePackPO(long id, String product, int amount, Double price) {
        this.id = new SimpleLongProperty(id);
        this.product = new SimpleStringProperty(product);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);
    }

    public long getId() {
        return id.get();
    }
    public String getProductId() { return product.get(); }
    public int getAmount() { return amount.get(); }
    public double getPrice() { return price.get(); }

    public static ObservableList<SimplePackPO> getContentOfPack(int id){
        ObservableList<SimplePackPO> list = FXCollections.observableArrayList();
        List<PackPO> packs = PackPoDAO.getAllById(id);
        for (int i=0; i<packs.size(); i++) {
            list.add(new SimplePackPO(
                    packs.get(i).getId(),
                    ProductDAO.getNameById(packs.get(i).getProduct().getId()),
                    packs.get(i).getAmount().intValue(),
                    packs.get(i).getPrice().doubleValue()));
        }
        return list;
    }
}