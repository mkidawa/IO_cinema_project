package Model.TimeTableExceptions.Params;

public class MinTimeIntervalOutOfRangeException extends SystemParamsException {

    public MinTimeIntervalOutOfRangeException() {}

    public MinTimeIntervalOutOfRangeException(String message) {
        super(message);
    }

    public MinTimeIntervalOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
