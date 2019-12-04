package View.TimetableModule.Exception.Performance;

public class WrongHallTypeException extends PerformanceCreationException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public WrongHallTypeException() {
    }

    public WrongHallTypeException(String message) {
        super(message);
    }

    public WrongHallTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
