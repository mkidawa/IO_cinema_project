import Controller.LPermissionController;
import Tools.BaseDB;
import Tools.PermissionChecker;
import View.Login.LoginView;
import View.MainMenu.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class Program extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu/Login.fxml"));
        primaryStage.setTitle("Cinema Management");
        primaryStage.setScene(new Scene(root, 800 , 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        //TODO Main program file. System starts here
        launch(args);
//        BaseDB.openConnection();


//        System.out.println(LPermissionController.getInstance().getPermissionsList());
        //exit(0);
    }

}
