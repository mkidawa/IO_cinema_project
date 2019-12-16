package View.Sale;

import View.Sale.Simple.SimplePack;
import View.Sale.Simple.SimplePackPO;
import View.Sale.Simple.SimpleSale;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

public class SaleCashbox {

    @Getter
    @Setter
    private Scene scene;

    @FXML
    private TableView<SimplePack> tableOfPack = new TableView<>();

    @FXML
    private TableView<SimplePackPO> tableOfPackContent = new TableView<>();


    @FXML
    private TableView<SimpleSale> tableOfOrderContent = new TableView<>();

    private ObservableList<SimplePack> packs;
    private ObservableList<SimplePackPO> packContentList = observableArrayList();
    private ObservableList<SimpleSale> orderContent = observableArrayList();

    Stage primaryStage;

    public SaleCashbox(Stage primary) throws IOException {

        primaryStage = primary;
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleCashbox.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());

        packs = SimplePack.getListOfPacks();

        tableOfPack.setItems(packs);
        tableOfOrderContent.setItems(orderContent);
        tableOfPackContent.setItems(packContentList);
    }


    public void showPackContent(){
        int index = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        packContentList.clear();
        packContentList = SimplePackPO.getContentOfPack((int) packs.get(index).getId());

        tableOfPackContent.setItems(packContentList);
        tableOfPackContent.refresh();
    }

    public void goBack() throws IOException {
        primaryStage.setScene(new SaleMenu(primaryStage).getScene());
        primaryStage.show();
    }


}
