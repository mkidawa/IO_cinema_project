package Tools;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public class LoginException extends RuntimeException {
=======
public class LoginException extends Exception {
>>>>>>> implemented LoginException
=======
public class LoginException extends RuntimeException {
>>>>>>> Changed Errors for LoginException
=======
public class LoginException extends RuntimeException {
>>>>>>> master
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
public class LoginException {
>>>>>>> Created loginException
=======
>>>>>>> implemented LoginException
=======
>>>>>>> master
}
