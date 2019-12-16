import Controller.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

import Tools.BaseDB;

public class Program extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        BaseDB.setUp();
        launch(args);
    }

    String movie = "MovieModule/MoviePanel/mainMovie.fxml";
    String timetable = TIMETABLE_PANEL_PATH;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager.mainStage = primaryStage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(timetable)));

        scene.getStylesheets().add(getClass().getResource(TIMETABLE_PANEL_STYLE_PATH).toExternalForm());

        primaryStage.setTitle("Main Movie Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
