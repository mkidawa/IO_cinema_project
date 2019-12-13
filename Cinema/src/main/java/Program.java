import Controller.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Tools.BaseDB;

import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_PATH;
import static View.TimetableModule.Util.Constants.TIMETABLE_PANEL_STYLE_PATH;

public class Program extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    String movie = "MovieModule/MoviePanel/mainMovie.fxml";
    String timetable = TIMETABLE_PANEL_PATH;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BaseDB.setUp();
        StageManager.mainStage = primaryStage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(timetable)));

        scene.getStylesheets().add(getClass().getResource(TIMETABLE_PANEL_STYLE_PATH).toExternalForm());

        primaryStage.setTitle("Main Movie Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
