package View;

import Tools.BaseDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
    //https://stackoverflow.com/questions/15805881/how-can-i-obtain-the-primary-stage-in-a-javafx-application

    //README !
    // To pozwala odwolac sie do primaryStage i podmienic okienko (np dla logowania)
    public static Stage pStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/Login.fxml"));
        primaryStage.setTitle("Cinema Management");
        primaryStage.setScene(new Scene(root, 800 , 600));
        primaryStage.show();
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
        launch(args);



    }

}
