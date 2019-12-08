package View.Sale;

import DBO.PackDAO;
import DBO.PackPoDAO;
import DBO.ProductDAO;
import DBO.SaleDAO;
import Model.Pack;
import Model.PackPO;
import Model.Product;
import Model.Sale;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class SaleView {
    @Getter
    @Setter
    private Scene scene;

    @FXML
    private TableView<SimpleProduct> tableOfProducts = new TableView<SimpleProduct>();
    @FXML
    private TableView<SimplePack> tableOfPack = new TableView<SimplePack>();
    @FXML
    private TableView<SimplePackPO> tableOfPackContent = new TableView<SimplePackPO>();


    private ObservableList<SimpleProduct> products = FXCollections.observableArrayList();
    private ObservableList<SimplePack> packs = FXCollections.observableArrayList();
    private ObservableList<SimplePackPO> packContentList = FXCollections.observableArrayList();

    public SaleView() throws IOException {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleView.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());

        packs = getListofPack();
        products = getListOfProduct();
        packContentList = getContentOfPack(1);

        tableOfPack.setItems(packs);
        tableOfProducts.setItems(products);
        tableOfPackContent.setItems(packContentList);

    }



    public class SimpleProduct {
        private final SimpleLongProperty id;
        private final SimpleStringProperty name;
        private  SimpleDoubleProperty price;
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
    }


    public class SimplePack {
        private final SimpleLongProperty id;
        private final SimpleStringProperty name;
        private  SimpleDoubleProperty price;

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
    }

    public class SimplePackPO {
        private final SimpleLongProperty id;
        private final SimpleStringProperty product;
        private final SimpleIntegerProperty amount;
        private  SimpleDoubleProperty price;

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
    }

    public ObservableList<SimplePackPO> getContentOfPack(int id){
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


    public ObservableList<SimpleProduct> getListOfProduct() {
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

    public ObservableList<SimplePack> getListofPack() {
        ObservableList<SimplePack> list = FXCollections.observableArrayList();
        List<Pack> products = PackDAO.getAll();
        for (int i=0; i<products.size(); i++) {
            list.add(new SimplePack(products.get(i).getId(), products.get(i).getName(), products.get(i).getPrice().doubleValue()));
        }
        return list;
    }
    public void onClick(){
        products.get(0).setPrice(new SimpleDoubleProperty(1000));
        tableOfProducts.refresh();
    }
    public void showPackContent(){
        int index = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        packContentList.clear();
        packContentList = getContentOfPack(++index);

        tableOfPackContent.setItems(packContentList);
        tableOfPackContent.refresh();
    }
}
