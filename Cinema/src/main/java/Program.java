import Controller.LPermissionController;
import Tools.BaseDB;
import Tools.PermissionChecker;
import View.Login.LoginView;
import View.Login.MA;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class Program extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cinema Management System");
//        var myLoader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
//        Pane myPane = myLoader.load();

        //var myScene = new Scene();
        primaryStage.setScene(new MA().getScene());
        primaryStage.show();
        //primaryStage.setFullScreen(true);

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        //TODO Main program file. System starts here
        BaseDB.setUp();
        launch(args);
//        BaseDB.openConnection();


//        System.out.println(LPermissionController.getInstance().getPermissionsList());
        //exit(0);
    }

}
