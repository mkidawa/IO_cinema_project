package View.MainMenu;

import Controller.LPermissionController;
import Controller.StageManager;
import Tools.PermissionChecker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.*;
import java.io.IOException;

public class MainMenu  {

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

    public void logIn(MouseEvent mouseEvent) {
    }

    public void printPermission(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
    }

    public void openTimetableModule(MouseEvent mouseEvent) {
    }

    public void openSalesModule(MouseEvent mouseEvent) {
    }

    public void openRaportModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/ReportModule/ReportPanel/mainReport.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
//        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Movie panel");
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
        StageManager.mainStage=stage;
    }


    public void printCurrentUser(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
        System.out.println(pc.getCurrentUser().toString());
    }
}
