package View.Sale.Simple;

import DBO.PackDAO;
import DBO.ProductDAO;
import Model.Pack;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SimplePack {
    public final SimpleLongProperty id;
    public final SimpleStringProperty name;
    public SimpleDoubleProperty price;

    public SimplePack(long id, String name, Double price) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public long getId() {
        return id.get();
    }
    public String getName() { return name.get(); }
    public double getPrice() { return price.get(); }

    public static ObservableList<SimplePack> getListOfPacks() {
        ObservableList<SimplePack> list = FXCollections.observableArrayList();
        List<Pack> packs = PackDAO.getAll();
        for (int i=0; i<packs.size(); i++) {
            list.add(new SimplePack(
                    packs.get(i).getId(),
                    packs.get(i).getName(),
                    packs.get(i).getPrice().doubleValue()));
        }
        return list;
    }
}