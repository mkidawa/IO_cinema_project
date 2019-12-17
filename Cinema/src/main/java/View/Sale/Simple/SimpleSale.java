package View.Sale.Simple;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleSale {

    private SimpleStringProperty packName;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty price;


    public SimpleSale(String packName, int amount, Double price) {
        this.packName = new SimpleStringProperty(packName);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getPackName(){return this.packName.get();}
    public long getAmount(){return this.amount.get();}
    public double getPrice(){return this.price.get();}


}
