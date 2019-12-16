package View.Sale.Simple;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleSale {

    private SimpleStringProperty packName;
    private SimpleLongProperty amount;
    private SimpleDoubleProperty price;


    public SimpleSale(String packName, long amount, Double price) {
        this.packName = new SimpleStringProperty(packName);
        this.amount = new SimpleLongProperty(amount);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getPackName(){return this.packName.get();}
    public long getAmount(){return this.amount.get();}
    public double getPrice(){return this.price.get();}
}
