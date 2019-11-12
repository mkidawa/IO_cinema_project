import Controller.MovieManager;
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
import javafx.*;


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

//        Filter filter = new Filter();
//        filter.addField("Id", "3");
//        List state = MovieStateDAO.getAllByFilter(filter);
//        System.out.println(state);

        List<MovieType> movieTypes = MovieTypeDAO.getAll();
        List<MovieState> movieStates = MovieStateDAO.getAll();
        List<Person> persons = PersonDAO.getAllById(3);
        List<PersonType> personTypes = PersonTypeDAO.getAll();
        LinkedList<PersonJob> personJobs = new LinkedList<PersonJob>();
        personJobs.add(new PersonJob(persons.get(0), personTypes.get(1)));
        personJobs.add(new PersonJob(persons.get(0), personTypes.get(0)));
        MovieManager.createMovie((short)1, (short)0, (short)1, movieTypes.get(1), movieStates.get(0),
                "bella fantastica", "molto bene", Time.valueOf("1:34:00"), personJobs);
    }

}
