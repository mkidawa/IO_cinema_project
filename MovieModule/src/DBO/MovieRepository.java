package DBO;

import Model.Actor;
import Model.Director;
import Model.Movie;
import Model.MovieState;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class MovieRepository implements RepositoryDBO<Movie> {

    public Movie getClassFromResult(ResultSet result) throws SQLException {
        long id = result.getLong("Id");
        String title = result.getString("Title");
        String description = result.getString("Description");
        MovieState state = MovieState.values()[result.getShort("MovieStateId") - 1];
        Duration duration = Duration.between(LocalTime.MIDNIGHT, result.getTime("MovieTime").toLocalTime());
        boolean flg2D = result.getBoolean("flg2D");
        boolean flg3D = result.getBoolean("flg3D");
        boolean flgVR = result.getBoolean("flgVR");

        //todo : adding directors and actors lists to Movie when CinemaManRepository will be done
        LinkedList<Director> directors = new LinkedList<Director>();
        LinkedList<Actor> actors = new LinkedList<Actor>();

        Movie movie = new Movie(id, title, description, state, duration, flg2D, flg3D, flgVR, directors, actors);
        return movie;
    }

    @Override
    public List<Movie> getAll() throws SQLException {
        LinkedList<Movie> movies = new LinkedList<Movie>();
        ResultSet result = BaseDB.execStoredProcedure("Select *from Movie");
        while (result.next()) {
            movies.add(getClassFromResult(result));
        }
        return movies;
    }

    @Override
    public Movie getUnique(int objectID) throws SQLException {
        String queryString = "Select *from Movie where Id = " + objectID;
        ResultSet result = BaseDB.execStoredProcedure(queryString);
        result.next();
        return getClassFromResult(result);
    }

    @Override
    public boolean insert(Movie object) throws SQLException {
        //conversion to sql time from java duration
        LocalTime localTime = LocalTime.MIDNIGHT.plus(object.getMovieTime());
        java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
        //flags conversion to int
        int flg2D = object.isFlg2D() ? 1 : 0;
        int flg3D = object.isFlg3D() ? 1 : 0;
        int flgVR = object.isFlgVR() ? 1 : 0;

        Connection conn = BaseDB.returnConnection();
        PreparedStatement prpstm = conn.prepareStatement("insert into Movie (flg2D, flg3D, flgVR, MovieStateId, Title, Description, MovieTime) values (?, ?, ?, ?, ?, ?, ?)");
        prpstm.setInt(1, flg2D);
        prpstm.setInt(2, flg3D);
        prpstm.setInt(3, flgVR);
        prpstm.setInt(4, object.getState().ordinal());
        prpstm.setString(5, object.getTitle());
        prpstm.setString(6, object.getDescription());
        prpstm.setTime(7, sqlTime);

        if (prpstm.executeUpdate() > 0)
            return true;

        return false;
    }

    @Override
    public boolean update(Movie object) {
        return false;
    }

    @Override
    public boolean delete(Movie object) {
        return false;
    }
}
