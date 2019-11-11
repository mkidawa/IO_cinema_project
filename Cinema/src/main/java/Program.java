import DBO.*;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.DICT.PersonType;
import Model.Movie;
import Model.Person;
import Model.PersonJob;
import Tools.Filter;
import lombok.var;

import javax.sound.midi.SysexMessage;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


public class Program {
    public static void main(String[] args) {
        //TODO Main program file. System starts here
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void test() throws Exception {

        Filter filter = new Filter();
        filter.addField("Name", "wasPlaying");
        List movies = MovieDAO.getAll();
        System.out.println(movies);

        List<MovieType> movieTypes = MovieTypeDAO.getAll();
        List<MovieState> movieStates = MovieStateDAO.getAll();
        List<Person> persons = PersonDAO.getAllById(1);
        List<PersonType> personTypes = PersonTypeDAO.getAll();
        Movie movie = new Movie( (short)1, (short)1, (short)1, movieTypes.get(0), movieStates.get(0),
                            "film", "cudowna baja", Time.valueOf("1:23:10"));
        movie.addPerson(new PersonJob(persons.get(0),personTypes.get(0)));
        MovieDAO.insertUpdate(movie);
    }

}
