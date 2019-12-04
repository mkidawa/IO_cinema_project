package Controller;

import DBO.MovieDAO;
import DBO.PersonDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.DICT.PersonType;
import Model.Movie;
import Model.Person;
import Model.PersonJob;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {

    public static Movie currentMovie;
    public static List<PersonJob> workingPersons = new ArrayList<>();
    public static boolean isEdit = false;
    //public static List<PersonJob> createPersonJobList = new ArrayList<>();
    public static void createMovie(short flg2D, short flg3D, short flgVR, MovieType type,
                                   MovieState state, String title, String description,
                                   Time duration, List<PersonJob> persons) {


        Movie movie = new Movie(flg2D, flg3D, flgVR, type, state, title, description, duration);
        for (int i=0; i<persons.size(); i++)
            movie.addPerson(persons.get(i));
        MovieDAO.insertUpdate(movie);
    }
    public static Person createPerson(String name, String lastname, Timestamp date){
        Person person = new Person(name,lastname,date);
        PersonDAO.insertUpdate(person);
        return person;
    }


}
