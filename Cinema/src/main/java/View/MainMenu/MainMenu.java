package View.MainMenu;

import Controller.LPermissionController;
import Controller.StageManager;
import Tools.PermissionChecker;
import View.Program;
import View.Sale.SaleMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class MainMenu {

    public Button movieModuleButton;
    public Button employeeModuleButton;
    public Button raportModuleButton;
    public PasswordField passwordField;
    public TextField logInTextField;
    public Button logInButton;
    public Button pPermission;
    public Button salesModuleButton;
    public Button timetableModuleButton;
    public Button pCurrentUser;
    public Button settingsButton;
    public Button logoutButton;

    public void logIn(MouseEvent mouseEvent) {
    }

    public void printPermission(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
//        System.out.println(pc.getPermissionsList());
        pc.prettyPermissions();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, pc.prettyPermissions(), ButtonType.YES);
        alert.setTitle("Information");
        alert.setHeaderText("Available Permissions");
        alert.showAndWait();
    }

    public void openTimetableModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/TimetableModule/TimetablePanel/TimetablePanel.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
//        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Timetable panel");
        stage.setResizable(false);
        stage.show();
    }

    public void openSalesModule(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new SaleMenu(stage).getScene());
        stage.show();
    }

    public void openRaportModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/ReportModule/ReportPanel/mainReport.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
//        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Raport panel");
        stage.setResizable(false);
        stage.show();
    }

    public void openEmployeeModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/UserScheduler/UserSchedulerView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Employee schedule panel");
        stage.show();
        StageManager.mainStage = stage;
    }

    public void openMovieModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Movie panel");
        stage.setResizable(false);
        stage.show();
        StageManager.mainStage = stage;
    }


    public void printCurrentUser(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
        System.out.println(pc.getCurrentUser().toString());
    }

    public void openSettings(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Settings/SettingsMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Settings Menu");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void logOut(MouseEvent mouseEvent) throws IOException {


            LPermissionController.getInstance().logOut();
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MainMenu/Login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader,800,600);
            Program.getPrimaryStage().setScene(scene);




    }
}
