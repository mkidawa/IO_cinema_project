package View.TimetableModule.Exception.Performance;

public class MovieNotAvailableException extends PerformanceCreationException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public MovieNotAvailableException() {
    }

    public MovieNotAvailableException(String message) {
        super(message);
    }

    public MovieNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
