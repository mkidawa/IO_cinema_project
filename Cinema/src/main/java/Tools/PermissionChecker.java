package Tools;

import Controller.LPermissionController;

import java.util.List;

public class PermissionChecker {
    private LPermissionController lm;
    public PermissionChecker() {
        lm = LPermissionController.getInstance();
    }
    public boolean checkPermission(int PermissionCode){
        return lm.checkPermission(PermissionCode);
    }
    public boolean checkPermission(String PermissionName){
        return lm.checkPermission(PermissionName);
    }
    public List getPermissionsList() {
        return lm.getPermissionsList();
    }
}
