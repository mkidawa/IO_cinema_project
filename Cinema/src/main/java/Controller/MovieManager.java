package Controller;

import DBO.MovieDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.DICT.PersonType;
import Model.Movie;
import Model.Person;
import Model.PersonJob;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {

    public static Movie workingMovie;
    public static List<Person> workingPersons = new ArrayList<>();
    public static PersonType WorkingPersonType;
    public static PersonType workingPersonType;
    public static void createMovie(short flg2D, short flg3D, short flgVR, MovieType type,
                                   MovieState state, String title, String description,
                                   Time duration, List<PersonJob> persons) {


        Movie movie = new Movie(flg2D, flg3D, flgVR, type, state, title, description, duration);
        for (int i=0; i<persons.size(); i++)
            movie.addPerson(persons.get(i));
        MovieDAO.insertUpdate(movie);
    }
    public static void createMovieWithoutPeople(short flg2D, short flg3D, short flgVR, MovieType type,
                                   MovieState state, String title, String description,
                                   Time duration) {


        Movie movie = new Movie(flg2D, flg3D, flgVR, type, state, title, description, duration);
        MovieDAO.insertUpdate(movie);
    }


}
