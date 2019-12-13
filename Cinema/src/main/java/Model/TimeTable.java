package Model;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

import DBO.PerformanceDAO;
import Tools.Filter;
import View.TimetableModule.Exception.Params.AdsDurationOutOfRangeException;
import View.TimetableModule.Exception.Params.MinTimeIntervalOutOfRangeException;
import View.TimetableModule.Exception.Performance.HallNotAvailableException;
import View.TimetableModule.Exception.Performance.MovieNotAvailableException;
import View.TimetableModule.Exception.Performance.WrongHallTypeException;

import static View.TimetableModule.Util.Constants.*;

public class TimeTable {


    /** Create and add new performance to the timetable
     *
     * @param movie related movie
     * @param movieType type of performance, available values ("2D", "3D", "VR"),
     *                  has to be available for selected movie
     * @param hall where performance will take place
     * @param startTime date and time of the performance's beginning
     * @param duration duration of performance (with advertisments)
     */
    public void addPerformance(Movie movie, String movieType, Hall hall,
                                Timestamp startTime, Duration duration){
        Performance performance = new Performance(movie, movieType,
                                        hall, startTime, duration);
    }

    /**
     * CHECK IF SELECTED HALL WAS PREVIOUSLY CHOSEN TO ANOTHER PERFORMANCE IN DATABASE
     *
     * @param performance
     * @return
     */
    private boolean isFreeHall(Performance performance) {
//        for (Performance it : PerformanceDAO.getAll()) {
//            if (it.getHall().getId() == performance.getHall().getId()) {
//                return false;
//            }
//        }
//
        return true;
    }

    /**
     * CHECK IF SELECTED HOUR IS EQUAL TO AVAILABLE PERFORMANCES HOURS
     *
     * @param performance
     * @return
     */
    private boolean isCorrectHour(Performance performance) {
//        for (Performance it : PerformanceDAO.getAll()) {
////            TODO NOT SURE IF addTime means start date of performance
//            if (it.getAddTime().equals(performance.getAddTime())) {
//                return false;
//            }
//        }

        return true;
    }

    /**
     * CHECK IF MOVIETYPE EQUALS TO "Current" - types from dictionary
     *
     * @param performance
     * @return
     */
    private boolean isMovieAvailable(Performance performance) {
        if (performance.getMovie().getMovieType().getName().equals("Current")) {
            return true;
        }

        return false;
    }

    /**
     * CHECK IF TYPE OF HALL IS CORRECT - 2D, 3D, VR FLAGS IN HALL AND MOVIE
     *
     * @param performance
     * @return
     */
    private boolean isCorrectHallType(Performance performance) {
//        if (performance.getHall().getFlg2D() == performance.getMovie().getFlg2D()
//                && performance.getHall().getFlg2D() == performance.getMovie().getFlg2D()
//                && performance.getHall().getFlg2D() == performance.getMovie().getFlg2D()) {
//            return true;
//        }

        return false;
    }

    /**
     * ADD PERFORMANCE AFTER CHECKING CONDITIONS
     *
     * @param performance
     * @throws HallNotAvailableException  - WHEN THERE IS NO FREE HALL IN CHOSEN TIME
     * @throws MovieNotAvailableException - WHEN MOVIE IS MARKED AS NOT AVAILABLE
     * @throws WrongHallTypeException     - WHEN TYPE OF DISPLAYING MOVIE AND TYPE OF HALL
     *                                    DOES NOT MATCH - 2D MOVIE WITH 2D HALL
     */
    public void addPerformance(Performance performance)
            throws HallNotAvailableException, MovieNotAvailableException, WrongHallTypeException {
//        if (!(isFreeHall(performance) && isCorrectHour(performance))) {
//            throw new HallNotAvailableException();
//        } else if (!isMovieAvailable(performance)) {
//            throw new MovieNotAvailableException();
//        } else if (!isCorrectHallType(performance)) {
//            throw new WrongHallTypeException();
//        } else {
//            PerformanceDAO.insertUpdate(performance);
//        }
    }

    public void removePerformance(Performance performance) {
//        if () {
        PerformanceDAO.delete(performance);
//        } else {
//
//        }
    }

    /**
     * Method compute performance length by adding movie time in minutes and adsDuration param
     *
     * @param performance
     * @return
     */
    public Integer computePerformanceLengthMinutes(Performance performance) {
        return performance.getMovie().getMovieTime().getMinutes() + PerformanceDAO.getAdsDuration();
    }

    public List<Performance> getPerformanceList() {
        return PerformanceDAO.getAll();
    }

    public List<Performance> getPerformanceListById(long id) {
        return PerformanceDAO.getAllById(id);
    }

    public List<Performance> getFilteredPerformanceList(Filter filter) {
        return PerformanceDAO.getAllByFilter(filter);
    }

    public void setAdsDuration(Integer value) throws AdsDurationOutOfRangeException {
        if (value >= MIN_ADS_VALUE && value <= MAX_ADS_VALUE) {
            PerformanceDAO.updateAdsDuration(value);
        } else {
            throw new AdsDurationOutOfRangeException();
        }
    }

    public void setMinTimeInterval(Integer value) throws MinTimeIntervalOutOfRangeException {
        if (value >= MIN_PERFORMANCE_GAP_VALUE && value <= MAX_PERFORMANCE_GAP_VALUE) {
            PerformanceDAO.updateMinTimeInterval(value);
        } else {
            throw new MinTimeIntervalOutOfRangeException();
        }
    }
}
