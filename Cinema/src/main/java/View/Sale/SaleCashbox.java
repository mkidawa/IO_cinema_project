package View.Sale;

import DBO.PackPoDAO;
import View.Sale.Simple.SimplePack;
import View.Sale.Simple.SimplePackPO;
import View.Sale.Simple.SimpleSale;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    List<Pair<Long, Integer>> amountPairs = new ArrayList<>();

    public class PackEssential{
        public long id;
        public int amount;
        public BigDecimal price;

        public PackEssential(long id, int amount, BigDecimal price) {
            this.id = id;
            this.amount = amount;
            this.price = price;
        }
    }


    Stage primaryStage;
    List<PackEssential> listOfOrder = new ArrayList<>();

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

    public void addPackToOrder(){
        int selectedPack = tableOfPack.getSelectionModel().selectedIndexProperty().get();
        SimplePack simplePack = packs.get(selectedPack);

        TextInputDialog d1 = new TextInputDialog();
        d1.setTitle("How many packs do you wanna add?");
        d1.setContentText("Enter amount");
        Optional<String> result = d1.showAndWait();
        if (result.isPresent()) {
            String amount = result.get();
            int keyIndex = 0;
            boolean enoughInMagazine = true;
            boolean keyExists = false;
            List<Pair<Long, Integer>> amountOfProductsInPack = new ArrayList<>();

            for (int i = 0; i < packContentList.size(); i++) {
                keyExists = false;
                amountOfProductsInPack.add(new Pair<>(Long.valueOf(packContentList.get(i).getProductId()),
                        packContentList.get(i).getAmount()));

                for (int j = 0; j < amountPairs.size(); j++) {
                    if (amountPairs.get(j).getKey() == packContentList.get(i).getId()) {
                        keyIndex = j;
                        keyExists = true;
                        break;
                    }
                }
                if (!keyExists) {
                    if (PackPoDAO.checkAmount(packContentList.get(i).getId(), packContentList.get(i).getAmount() * Integer.valueOf(amount), 0)) {

                        amountPairs.add(new Pair<>(packContentList.get(i).getId(),
                                packContentList.get(i).getAmount() * Integer.valueOf(amount)));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "There is not enough " + simplePack.getName() + " in magazine!", ButtonType.CLOSE);
                        alert.showAndWait();
                        enoughInMagazine = false;
                        break;
                    }
                } else {
                    if (PackPoDAO.checkAmount(packContentList.get(i).getId(), packContentList.get(i).getAmount() * Integer.valueOf(amount), amountPairs.get(keyIndex).getValue())) {

                        int newAmount = amountPairs.get(keyIndex).getValue() +
                                (Integer.valueOf(amount) * packContentList.get(i).getAmount());
                        amountPairs.set(keyIndex, new Pair<>(amountPairs.get(keyIndex).getKey(), newAmount));

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "There is not enough " + simplePack.getName() + " in magazine!", ButtonType.CLOSE);
                        alert.showAndWait();
                        enoughInMagazine = false;
                        break;
                    }
                }
            }
            if(enoughInMagazine) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "FUCKIN SUCEEDED", ButtonType.CLOSE);
                alert.showAndWait();

                orderContent.add(
                        new SimpleSale(
                            simplePack.getName(),
                            Integer.valueOf(amount),
                            simplePack.price.multiply(Integer.valueOf(amount)).doubleValue(),
                            amountOfProductsInPack)
                        );
                tableOfOrderContent.setItems(orderContent);
                tableOfOrderContent.refresh();
            }
            System.out.print("AMOUNT PAIRS: " + amountPairs.toString() + "\n\n\n\n\n");
        }
    }
//
    public void removePackFromOrder(){
        int selectedPack = tableOfOrderContent.getSelectionModel().selectedIndexProperty().get();
        SimpleSale simpleSale = orderContent.get(selectedPack);

        for (int i = 0; i < simpleSale.productInSale.size(); i++) {
            for (int j = 0; j < amountPairs.size(); j++) {
                if (amountPairs.get(j).getKey().equals(simpleSale.productInSale.get(i).getKey())) {
                    int newAmount = (int)
                            (amountPairs.get(j).getValue() -
                                    (simpleSale.productInSale.get(i).getValue() *
                                            simpleSale.getAmount()));

                    amountPairs.set(j, new Pair<>(amountPairs.get(j).getKey(), newAmount));
              }
            }
        }
        System.out.print(amountPairs.toString());
        orderContent.remove(selectedPack);
        tableOfOrderContent.refresh();
    }



}
