import DBO.HallDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.Movie;
import Tools.Filter;
import lombok.var;

import java.sql.Time;
import java.time.LocalTime;

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

        Movie movie = new Movie((short)1, (short)0 , (short)1, new MovieType("komedia"),
                new MovieState("bedzie grany"), "fajny film", "super komedia", new Time(0*60*60*1000 + 23*60*1000 + 10*1000));

        System.out.println(movie);
    }

}
