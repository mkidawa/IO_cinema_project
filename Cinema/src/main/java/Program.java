import DBO.MovieDAO;
import DBO.PersonDAO;
import Model.Movie;
import Model.Person;
import View.MovieView.MoviePanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.var;

import java.util.List;


public class Program extends Application {

    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/MoviePanel/mainMovie.fxml"));
        Scene scene = new Scene(fxmlLoader);
        scene.getStylesheets().add(getClass().getResource("/MoviePanel/mainMovie.css").toExternalForm());
        primaryStage.setTitle("Main Movie Panel");
        primaryStage.setScene(scene);
        primaryStage.show();

//        List<Movie> movies = MovieDAO.getAll();
//        MovieDAO.delete(movies.get(10));
//        List<Person> persons = PersonDAO.getAll();
//        PersonDAO.delete(persons.get(10));
    }
}
