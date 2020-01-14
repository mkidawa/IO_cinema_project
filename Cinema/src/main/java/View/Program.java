package View;

import Tools.BaseDB;

import Tools.SettingsTool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Program extends Application {
    //https://stackoverflow.com/questions/15805881/how-can-i-obtain-the-primary-stage-in-a-javafx-application

    //README !
    // To pozwala odwolac sie do primaryStage i podmienic okienko (np dla logowania)
    public static Stage pStage;
    public static Rectangle2D screenBounds;
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/Login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/UserScheduler/UserSchedulerView.fxml"));
        screenBounds = Screen.getPrimary().getBounds();

        primaryStage.setTitle("Cinema Management");
        primaryStage.setScene(new Scene(root, screenBounds.getWidth() ,  screenBounds.getHeight()));
        //        Disable all
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
//        Disable Resize
//        primaryStage.resizableProperty().setValue(Boolean.FALSE);
//        Disable minimize/maximize
//        primaryStage.initStyle(StageStyle.UTILITY);


        pStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    public static void setPrimaryStage(Stage pStage) {
        Program.pStage = pStage;
    }

    public static void main(String[] args) {
        //TODO Main program file. System starts here

        BaseDB.setUp();
        SettingsTool.loadSettings();
        launch(args);



    }

}
