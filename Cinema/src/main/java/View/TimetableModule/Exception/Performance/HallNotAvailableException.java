package View.TimetableModule.Exception.Performance;

public class HallNotAvailableException extends PerformanceCreationException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public HallNotAvailableException() {
    }

    public HallNotAvailableException(String message) {
        super(message);
    }

    public HallNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
