import Controller.LPermissionController;
import Tools.BaseDB;
import Tools.PermissionChecker;
import View.Login.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class Program extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cinema Management System");
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UserScheduler/UserSchedulerView.fxml"));
        Pane myPane = myLoader.load();
        Scene scene = new Scene(myPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    public static void main(String[] args) {
        //TODO Main program file. System starts here
        launch(args);
        BaseDB.openConnection();
//        System.out.println(LPermissionController.getInstance().getPermissionsList());
        //exit(0);
    }

}
