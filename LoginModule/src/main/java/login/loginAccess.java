package login;
import login.loginManager;
public class loginAccess {
    loginManager lm;
    public levelProvided getAccessLevel() {
        return lm.getAccessLevel();
    }

    public loginAccess() {
        lm = loginManager.getInstance();
    }
}
