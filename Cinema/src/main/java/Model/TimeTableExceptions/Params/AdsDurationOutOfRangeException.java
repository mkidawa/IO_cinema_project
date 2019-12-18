package Model.TimeTableExceptions.Params;

public class AdsDurationOutOfRangeException extends SystemParamsException {

    public AdsDurationOutOfRangeException() {}

    public AdsDurationOutOfRangeException(String message) {
        super(message);
    }

    public AdsDurationOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
