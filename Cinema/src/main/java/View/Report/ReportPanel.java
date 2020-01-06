package View.Report;

import DBO.UserDAO;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportPanel implements Initializable {
    public DatePicker toDate;
    public DatePicker fromDate;
    @FXML
    private GridPane pane;
    @FXML
    private ComboBox<String> reportList;
    @FXML
    private ComboBox<String> userCombo;

    private ObservableList<String> list = FXCollections.observableArrayList();
    private ObservableList<String> userList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        list = FXCollections.observableArrayList(
                "All Movies Report",
               "Work time report",
                "Individual Work time report",
                "Incomes Report",
                "Food Sale Report"
        );


        reportList.getItems().addAll(list);
        reportList.setValue(list.get(0));

        List<User> users = UserDAO.getAll();

        List<String> userIds = new ArrayList<>();



        for(User user : users) {
            userIds.add(String.valueOf(user.getId()));
        }

        userList = FXCollections.observableArrayList(userIds);

        userCombo.getItems().addAll(userList);
        userCombo.setValue(userIds.get(0));
    }

    public void onClickGenerate(MouseEvent mouseEvent) throws IOException {
        System.out.println(toDate.getValue());
        System.out.println(fromDate.getValue());


        if(reportList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report");
            alert.setHeaderText(null);
            alert.setContentText("Report must be selected!");
            alert.showAndWait();
            return;
        }

        switch (reportList.getValue()) {
            case "All Movies Report": {
                Controller.ReportGenerator.generateAllMoviesReport();
            }
            case "Work time report": {
                Controller.ReportGenerator.generateWorkTimeReport(fromDate.getValue(), toDate.getValue());
            }
            case "Individual Work time report": {
                Controller.ReportGenerator.generateIndividualWorkTimeReport(fromDate.getValue(), toDate.getValue(), Long.parseLong(userCombo.getValue()));
            }
            case "Incomes Report": {
                Controller.ReportGenerator.generateIncomesReport();
            }
            case "Food Sale Report": {
                Controller.ReportGenerator.generateFoodSaleReport();
            }

            default: {
                System.out.println("default");
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report generated");
        alert.setHeaderText(null);
        alert.setContentText("Generated report: " + reportList.getValue());
        alert.showAndWait();
    }
}
