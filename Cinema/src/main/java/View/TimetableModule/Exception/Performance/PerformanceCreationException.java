package View.TimetableModule.Exception.Performance;

public abstract class PerformanceCreationException extends Exception {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public PerformanceCreationException() {
    }

    public PerformanceCreationException(String message) {
        super(message);
    }

    public PerformanceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
