package Model.TimeTableExceptions.Params;

public abstract class SystemParamsException extends Exception {

    public SystemParamsException() {}

    public SystemParamsException(String message) {
        super(message);
    }

    public SystemParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
