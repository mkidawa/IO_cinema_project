import Model.*;
import sun.awt.image.ImageWatched;
import sun.java2d.windows.GDIRenderer;

import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Director director = new Director(1, "Mateusz", "Walczak", new Date());
        Actor actor = new Actor(2, "Konrad", "Kajszczak", new Date());

        List<Actor> actors = new LinkedList<Actor>();
        actors.add(actor);

        List<Director> directors= new LinkedList<Director>();
        directors.add(director);

        Movie movie = new Movie(1, "Cool", "Super Cool",
                MovieState.isPlaying, Duration.ofMinutes(90),
                true, true, false, directors, actors);
        System.out.println(movie);
    }
}
