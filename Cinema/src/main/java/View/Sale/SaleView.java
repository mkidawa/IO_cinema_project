package View.Sale;

import Controller.StageManager;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;


import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

public class SaleView {
    @Getter
    @Setter
    private Scene scene;

    private TableView<Product> table = new TableView<Product>();
    @Getter
    @Setter
    private ObservableList<Product> products= FXCollections.observableArrayList(
            new Product("Jacob", new BigDecimal(12),12),
            new Product("Isabella", new BigDecimal(12),12),
            new Product("Ethan", new BigDecimal(12),12),
            new Product("Emma", new BigDecimal(12),12),
            new Product("Michael", new BigDecimal(12),12)
    );

    public SaleView() throws IOException {

        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleView.fxml"));
        fxmlLoader.setController(this);

    }
}
