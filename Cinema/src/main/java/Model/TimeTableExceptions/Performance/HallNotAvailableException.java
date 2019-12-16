package Model.TimeTableExceptions.Performance;

public class HallNotAvailableException extends TimeTableCreationException {

    public HallNotAvailableException() {}

    public HallNotAvailableException(String message) {
        super(message);
    }

    public HallNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
