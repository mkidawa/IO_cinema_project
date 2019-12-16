package View.Sale;

import DBO.PackDAO;
import DBO.PackPoDAO;
import DBO.ProductDAO;
import Model.Pack;
import Model.PackPO;
import Model.Product;
import View.Sale.Simple.SimplePack;
import View.Sale.Simple.SimplePackPO;
import View.Sale.Simple.SimpleProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SaleManagement {
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

    Stage primaryStage;

    public SaleManagement(Stage primary) throws IOException {
        primaryStage = primary;
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleView.fxml"));
        fxmlLoader.setController(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());

        packs = getListOfPack();
        products = SimpleProduct.getListOfProduct();

        tableOfPack.setItems(packs);
        tableOfProducts.setItems(products);
        tableOfPackContent.setItems(packContentList);



    }

    public void goBack() throws IOException {
        primaryStage.setScene(new SaleMenu(primaryStage).getScene());
        primaryStage.show();
    }


    public ObservableList<SimplePack> getListOfPack() {

        ObservableList<SimplePack> list = FXCollections.observableArrayList();
        List<Pack> products = PackDAO.getAll();
        for (int i=0; i<products.size(); i++) {
            list.add(new SimplePack(products.get(i).getId(), products.get(i).getName(), products.get(i).getPrice().doubleValue()));
        }

        tableOfPack.setEditable(true);
        tableOfPack.getSelectionModel().setCellSelectionEnabled(true);
        tableOfPack.setOnMouseClicked(click -> {

            int index = tableOfPack.getSelectionModel().selectedIndexProperty().get();
            packContentList.clear();
            packContentList = SimplePackPO.getContentOfPack((int) packs.get(index).getId());

            tableOfPackContent.setItems(packContentList);
            tableOfPackContent.refresh();

            if (click.getClickCount() == 2) {

                @SuppressWarnings("rawtypes")
                TablePosition pos = tableOfPack.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                int col = pos.getColumn();
                @SuppressWarnings("rawtypes")
                TableColumn column = pos.getTableColumn();
                String val = column.getCellData(row).toString();
                if (col == 0) {
                    TextInputDialog dialog = new TextInputDialog(val);
                    dialog.setTitle("Pack name changer");
                    dialog.setHeaderText(val);
                    dialog.setContentText("Enter new name for a pack");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        if (PackDAO.changeName(list.get(row).id.longValue(), result.get())) {
                            list.get(row).name.set(result.get());
                            tableOfPack.refresh();
                        }
                    }
                } else if (col == 1) {
                    TextInputDialog dialog = new TextInputDialog(val);
                    dialog.setTitle("Pack price changer");
                    dialog.setHeaderText(val);
                    dialog.setContentText("Enter new price for a pack");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String res = String.valueOf(result);
                        res = res.substring(9, res.length()-1);
                        double dres = 0;
                        try {
                            dres = Double.parseDouble(res);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        if (dres > 0) {
                            if (PackDAO.changePrice(list.get(row).id.longValue(), res)) {
                                list.get(row).price.set(dres);
                                tableOfPack.refresh();
                            }
                }}}}
        });
        return list;
    }

    public void addNewPack(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New pack creator");
        dialog.setContentText("Enter pack name");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            PackDAO.insert(new Pack(result.get(), new BigDecimal(0)));
            packs = getListOfPack();
            tableOfPack.setItems(packs);
        }
    }

    public void addNewProduct(){
        TextInputDialog d1 = new TextInputDialog();
        d1.setTitle("New product creator");
        d1.setContentText("Enter product name");
        Optional<String> result = d1.showAndWait();
        if (result.isPresent()) {
            String name = result.get();
            TextInputDialog d2 = new TextInputDialog();
            d2.setTitle("New product creator");
            d2.setContentText("Enter price of product");
            Optional<String> result2 = d2.showAndWait();
            if(result2.isPresent()){
                BigDecimal price = new BigDecimal(result2.get());
                TextInputDialog d3 = new TextInputDialog();
                d3.setTitle("New product creator");
                d3.setContentText("Enter amount of product in magazine");
                Optional<String> result3 = d3.showAndWait();
                if(result3.isPresent()){
                    int amount = Integer.valueOf(result3.get());
                    ProductDAO.insert(new Product(name, price, amount));
                    products = SimpleProduct.getListOfProduct();
                    tableOfProducts.setItems(products);
                }
            }
            packs = getListOfPack();
            tableOfPack.setItems(packs);
        }
    }

    public void addProductToPack(){

        int selectedProduct = tableOfProducts.getSelectionModel().selectedIndexProperty().get();
        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();

        long productId = products.get(selectedProduct).getId();
        long packId = packs.get(selectedPack).getId();

        var x = PackDAO.getAllByID(packId).get(0);
        var y = ProductDAO.getAllByID(productId).get(0);

        TextInputDialog d1 = new TextInputDialog();
        d1.setTitle("Set amount of product in pack");
        d1.setContentText("Enter amount");
        Optional<String> result = d1.showAndWait();
        if (result.isPresent()) {
            String amount = result.get();
            PackPoDAO.insert(new PackPO((Pack) x,(Product) y, new BigDecimal(amount)));

            packContentList = SimplePackPO.getContentOfPack((int) packId);
            tableOfPackContent.setItems(packContentList);
            tableOfPackContent.refresh();
        }
    }

    public void removeProductFromPack(){
        int selectedProduct = tableOfPackContent.getSelectionModel().selectedIndexProperty().get();
        long productId = packContentList.get(selectedProduct).getId();

        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        long packId = packs.get(selectedPack).getId();

        PackPoDAO.removeById(productId);

        packContentList = SimplePackPO.getContentOfPack((int) packId);
        tableOfPackContent.setItems(packContentList);
        tableOfPackContent.refresh();
    }

    public void removeProduct(){
        int selectedProduct = tableOfProducts.getSelectionModel().selectedIndexProperty().get();
        long productId = products.get(selectedProduct).getId();

        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        long packId = packs.get(selectedPack).getId();

        ProductDAO.removeById(productId);

        packContentList = SimplePackPO.getContentOfPack((int) packId);
        tableOfPackContent.setItems(packContentList);
        tableOfPackContent.refresh();
        products = SimpleProduct.getListOfProduct();
        tableOfProducts.setItems(products);
        tableOfProducts.refresh();
    }


    public void removePack(){
        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        long packId = packs.get(selectedPack).getId();

        PackDAO.removeById(packId);

        packContentList = SimplePackPO.getContentOfPack((int) packId);
        tableOfPackContent.setItems(packContentList);
        tableOfPackContent.refresh();
        packs = getListOfPack();
        tableOfPack.setItems(packs);
        tableOfPack.refresh();
    }

}
