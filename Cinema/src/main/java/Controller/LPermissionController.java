package Controller;

public class LPermissionController {
    private static LPermissionController ourInstance;
    public static LPermissionController getInstance() {
        if (ourInstance == null) {
            ourInstance = new LPermissionController();
        }
        return ourInstance;
    }
    public void getAccessLevel(){

    }
}
