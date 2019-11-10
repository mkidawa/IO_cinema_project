import Model.*;
import DBO.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Button button;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("First working window");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        BaseDB.setHost("localhost");
        BaseDB.setDbname("cinema");
        BaseDB.setUsername("sa");
        BaseDB.setPassword("12345");

        System.out.println("Connection works fine: "  + BaseDB.testConnection());
        System.out.println("");

        MovieRepository movieRepo = new MovieRepository();

        //get unique test
        Movie movie = new Movie();
        try {
            movie = movieRepo.getUnique(10);
            System.out.println("I found movie: " + movie);
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //insert test
        List<Actor> actors = new LinkedList<Actor>();
        List<Director> directors= new LinkedList<Director>();
        Movie insertMovie = new Movie("Misiowa jest cudowna", "I pieknie pachnie",
                MovieState.willBePlaying, Duration.ofMinutes(123),
                true, true, false, directors, actors);

        try {
            if (movieRepo.insert(insertMovie))
                System.out.println("Insert: success");
            else
                System.out.println("Insert: fail");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
