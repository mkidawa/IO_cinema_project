import login.loginManager;
import login.loginAccess;
public class main {
    public static void main(String[] args) {
        loginManager lm = loginManager.getInstance();
        System.out.println(lm.user());
        lm.login("admin","nie");
        System.out.println(lm.user());
        loginAccess la = new loginAccess();
        System.out.println(la.getAccessLevel());
        lm.logout();
        System.out.println(la.getAccessLevel());
        lm.login("user","user");
        System.out.println(la.getAccessLevel());
        System.out.println(lm.user());
        lm.logout();
        System.out.println(lm.user());

    }
}
