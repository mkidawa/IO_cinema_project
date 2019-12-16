package Model.TimeTableExceptions.Performance;

public class MovieNotAvailableException extends TimeTableCreationException {

    public MovieNotAvailableException() {}

    public MovieNotAvailableException(String message) {
        super(message);
    }

    public MovieNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
