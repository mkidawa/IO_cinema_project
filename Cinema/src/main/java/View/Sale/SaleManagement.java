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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

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
    int indexForPacks = 0;


    public SaleManagement(Stage primary) throws IOException {
        primaryStage = primary;
        var fxmlLoader = new FXMLLoader(getClass().getResource("/Sale/SaleView.fxml"));
        fxmlLoader.setController(this);
        Pane pane = fxmlLoader.load();
        scene = new Scene(pane);

        packs = getListOfPack();
        products = SimpleProduct.getListOfProduct();

        tableOfPack.setItems(packs);
        tableOfProducts.setItems(products);
        tableOfPackContent.setItems(packContentList);


        tableOfProducts.setEditable(true);
        tableOfProducts.getSelectionModel().setCellSelectionEnabled(true);
        tableOfProducts.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {

                @SuppressWarnings("rawtypes")
                TablePosition pos = tableOfProducts.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                int col = pos.getColumn();
                @SuppressWarnings("rawtypes")
                TableColumn column = pos.getTableColumn();
                String val = column.getCellData(row).toString();

                if (col == 0) {
                    TextInputDialog dialog = new TextInputDialog(val);
                    dialog.setTitle("Product price changer");
                    dialog.setHeaderText(val);
                    dialog.setContentText("Enter new price for a product");

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
                            if (ProductDAO.changePrice(products.get(row).getId(), res)) {
                                products.get(row).setPrice(new SimpleDoubleProperty(dres));
                                tableOfProducts.refresh();
                            }
                        }
                    }
                }else if (col == 1){

                    TextInputDialog dialog = new TextInputDialog(val);
                    dialog.setTitle("Product amount changer");
                    dialog.setHeaderText(val);
                    dialog.setContentText("Enter new amount for a product");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String res = String.valueOf(result);
                        res = res.substring(9, res.length()-1);
                        int dres = 0;
                        try {
                            dres = Integer.parseInt(res);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        if (dres > 0) {
                            if (ProductDAO.setAmount(products.get(row).getId(), dres)) {
                                products.get(row).setAmount(dres);
                                tableOfProducts.refresh();
                            }
                        }
                    }

                } else if (col == 2) {
                    TextInputDialog dialog = new TextInputDialog(val);
                    dialog.setTitle("Product name changer");
                    dialog.setHeaderText(val);
                    dialog.setContentText("Enter new name for a product");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        if (ProductDAO.changeName(products.get(row).getId(), result.get())) {
                            products.get(row).setName(result.get());
                            tableOfProducts.refresh();
                        }
                    }
                }
            }
        });




        tableOfPack.setEditable(true);
        tableOfPack.getSelectionModel().setCellSelectionEnabled(true);
        tableOfPack.setOnMouseClicked(click -> {

            int index = tableOfPack.getSelectionModel().selectedIndexProperty().get();

            if(indexForPacks != index) {
                indexForPacks = index;
                packContentList.clear();
                packContentList = SimplePackPO.getContentOfPack((int) packs.get(index).getId());
                tableOfPackContent.setItems(packContentList);
                tableOfPackContent.refresh();
            }

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
                        if (PackDAO.changeName(packs.get(row).id.longValue(), result.get())) {
                            packs.get(row).name.set(result.get());
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
                            if (PackDAO.changePrice(packs.get(row).id.longValue(), res)) {
                                packs.get(row).price.set(dres);
                                tableOfPack.refresh();
                            }
                        }}}}
        });


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
                    PackDAO.insert(new Pack(name, price));

                    packs = getListOfPack();
                    tableOfPack.setItems(packs);
                    products = SimpleProduct.getListOfProduct();
                    tableOfProducts.setItems(products);

                    var x = PackDAO.getAllByID(packs.get(packs.size()-1).getId()).get(0);
                    var y = ProductDAO.getAllByID(products.get(products.size()-1).getId()).get(0);

                    PackPoDAO.insert(new PackPO((Pack) x,(Product) y, new BigDecimal(1)));
                }
            }
        }
    }

    public void addProductToPack(){

        int selectedProduct = tableOfProducts.getSelectionModel().selectedIndexProperty().get();
        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();

        long productId = products.get(selectedProduct).getId();
        long packId = packs.get(selectedPack).getId();

        var x = PackDAO.getAllByID(packId).get(0);
        var y = ProductDAO.getAllByID(productId).get(0);

        boolean alreadyInPack = false;

        for(int i = 0; i < packContentList.size(); i++){
            if(packContentList.get(i).getProductId() == productId){

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "This product is already in this Pack. Please, change amount instead of adding!",
                        ButtonType.OK);
                alert.showAndWait();
                alreadyInPack = true;
                break;
            }
        }

        if(!alreadyInPack){
            TextInputDialog d1 = new TextInputDialog();
            d1.setTitle("Set amount of product in pack");
            d1.setContentText("Enter amount");
            Optional<String> result = d1.showAndWait();
            if (result.isPresent()) {
                String amount = result.get();
                PackPoDAO.insert(new PackPO((Pack) x, (Product) y, new BigDecimal(amount)));

                packContentList = SimplePackPO.getContentOfPack((int) packId);
                tableOfPackContent.setItems(packContentList);
                tableOfPackContent.refresh();
            }
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
