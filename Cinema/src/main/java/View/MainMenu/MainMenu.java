package View.MainMenu;

import Controller.LPermissionController;
import Tools.PermissionChecker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
=======
>>>>>>> Added panel for mainMenu
=======
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
>>>>>>> Created buttons and methods
=======
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
>>>>>>> master
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Created buttons and methods
=======
>>>>>>> master
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    public void openMM(MouseEvent mouseEvent) {
=======
=======
>>>>>>> Created buttons and methods
    public void openMM(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Movie panel");
        stage.setResizable(false);
        stage.show();
>>>>>>> Added nicer login/menu
    }
>>>>>>> Added panel for mainMenu
=======
>>>>>>> implemented openMovieModule
=======
>>>>>>> master

    public void logIn(MouseEvent mouseEvent) {
    }

    public void printPermission(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Created buttons and methods
=======
>>>>>>> master

    public void openTimetableModule(MouseEvent mouseEvent) {
    }

    public void openSalesModule(MouseEvent mouseEvent) {
    }

    public void openRaportModule(MouseEvent mouseEvent) {
    }

    public void openEmployeeModule(MouseEvent mouseEvent) {
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> implemented openMovieModule
=======
>>>>>>> master

    public void openMovieModule(MouseEvent mouseEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MovieModule/MoviePanel/mainMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MovieModule/MoviePanel/mainMovie.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Movie panel");
        stage.setResizable(false);
        stage.show();
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Added panel for mainMenu
=======
>>>>>>> Created buttons and methods
=======
>>>>>>> implemented openMovieModule
=======
=======
>>>>>>> master

    public void printCurrentUser(MouseEvent mouseEvent) {
        PermissionChecker pc = new PermissionChecker();
        System.out.println(pc.getPermissionsList());
        System.out.println(pc.getCurrentUser().toString());
    }
<<<<<<< HEAD
>>>>>>> Fixed printing current User
=======
>>>>>>> master
}
