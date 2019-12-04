package View.TimetableModule.Exception.Params;

public abstract class SystemParamsException extends Exception {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public SystemParamsException() {
    }

    public SystemParamsException(String message) {
        super(message);
    }

    public SystemParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
