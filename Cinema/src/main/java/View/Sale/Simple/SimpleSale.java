package View.Sale.Simple;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleSale {

    private SimpleStringProperty packName;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty price;
    private SimpleLongProperty packId;


    public SimpleSale(String packName, int amount, Double price, long packId) {
        this.packName = new SimpleStringProperty(packName);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);
        this.packId = new SimpleLongProperty(packId);
    }

    public String getPackName(){return this.packName.get();}
    public int getAmount(){return this.amount.get();}
    public double getPrice(){return this.price.get();}
    public long getPackID(){return this.packId.get();}
}
