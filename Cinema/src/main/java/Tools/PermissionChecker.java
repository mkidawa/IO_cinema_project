package Tools;

import Controller.LPermissionController;

public class PermissionChecker {
    LPermissionController lm;
    public void getAccessLevel() {
         lm.getAccessLevel();
    }

    public PermissionChecker() {
        lm = LPermissionController.getInstance();
    }
}
