package View.Sale.Simple;

import DBO.ProductDAO;
import Model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SimpleProduct {
    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private final SimpleIntegerProperty amount;

    public SimpleProduct(long id, String name, Double price, int amount) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.amount = new SimpleIntegerProperty(amount);
    }

    public long getId() {
        return id.get();
    }
    public String getName() { return name.get(); }
    public double getPrice() { return price.get(); }
    public int getAmount() { return amount.get(); }

    public void setPrice(SimpleDoubleProperty a) {price = a;}

    public static ObservableList<SimpleProduct> getListOfProduct() {
        ObservableList<SimpleProduct> list = FXCollections.observableArrayList();
        List<Product> products = ProductDAO.getAll();
        for (int i=0; i<products.size(); i++) {
            list.add(new SimpleProduct(
                    products.get(i).getId(),
                    products.get(i).getName(),
                    products.get(i).getPrice().doubleValue(),
                    products.get(i).getAmount()));
        }
        return list;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
