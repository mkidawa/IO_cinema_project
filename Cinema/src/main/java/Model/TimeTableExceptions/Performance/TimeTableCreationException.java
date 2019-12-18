package Model.TimeTableExceptions.Performance;

public abstract class TimeTableCreationException extends Exception {

    public TimeTableCreationException() {}

    public TimeTableCreationException(String message) {
        super(message);
    }

    public TimeTableCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
