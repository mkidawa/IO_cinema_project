import Controller.LPermissionController;
import View.Login.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cinema Management System");
        //var myLoader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
        //Pane myPane = myLoader.load();

        //var myScene = new Scene();
        primaryStage.setScene(new LoginView().getScene());
        primaryStage.show();
        //primaryStage.setFullScreen(true);

    }

    public static void main(String[] args) {
        //TODO Main program file. System starts here
//        launch(args);
        LPermissionController.getInstance().getAllPermissions();
    }

}
