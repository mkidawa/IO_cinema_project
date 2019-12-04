package View.TimetableModule.Exception;

public class MinTimeIntervalOutOfRangeException extends SystemParamsException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public MinTimeIntervalOutOfRangeException() {
    }

    public MinTimeIntervalOutOfRangeException(String message) {
        super(message);
    }

    public MinTimeIntervalOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
