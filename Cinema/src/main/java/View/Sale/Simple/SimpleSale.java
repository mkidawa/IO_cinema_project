package View.Sale.Simple;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

import java.util.List;

public class SimpleSale {

    private SimpleStringProperty packName;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty price;
    public List<Pair<Long,Integer>> productInSale;



    public SimpleSale(String packName, int amount, Double price, List<Pair<Long,Integer>> listOfProducts) {
        this.packName = new SimpleStringProperty(packName);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);
        this.productInSale = listOfProducts;
    }

    public String getPackName(){return this.packName.get();}
    public long getAmount(){return this.amount.get();}
    public double getPrice(){return this.price.get();}


}
