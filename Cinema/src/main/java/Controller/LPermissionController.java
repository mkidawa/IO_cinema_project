package Controller;

import DBO.PermissionsDAO;
import DBO.UserDAO;

import Model.DICT.Permissions;
import Model.User;
import Tools.Filter;

import java.util.Collections;
import java.util.List;

public class LPermissionController {
    private User currentUser;
    private static LPermissionController ourInstance;

    public static LPermissionController getInstance() {
        if (ourInstance == null) {
            ourInstance = new LPermissionController();
        }
        return ourInstance;
    }

    private LPermissionController() {
        currentUser = new User();
    }


    private boolean checkLogin(String login) {
        Filter fl = new Filter();
        fl.addField("Login", login);
        String sql = "SELECT U.login from User U WHERE U.login='" + login +
                "'";
        List result = UserDAO.execSQL(sql);
        if (result.size() == 0) {
            System.out.println("Brak uzytkownika w bazie");
            return false;
        } else if (result.size() == 1 && result.get(0).toString().equals(login)) {
            System.out.println("Znalazlem login: " + login);
            return true;
        }
        return false;
    }

    public boolean login(String login, String password) {
        if (checkLogin(login)) {
            if (checkPassword(login, password)) {
                currentUser.setPasswordHash("");
                return true;
            }
        }
        return false;
    }

    private boolean checkPassword(String login, String password) {
        String sql = "from User U WHERE U.login='" + login +
                "'";
        User result = (User) UserDAO.execSQL(sql).get(0);
        if (result.getPasswordHash().equals(password)) {
            currentUser = result;
            System.out.println("Prawidlowe Haslo");
            return true;
        }
        System.out.println("Haslo nie prawidlowe");
        return false;
    }

    public List getPermissionsList() {
        if (checkIfLogged()) {
            return Collections.emptyList();
        }
        System.out.println(currentUser.getPermissionsList());
        return currentUser.getPermissionsList();
    }

    public boolean checkPermission(int PermissionCode) {
        if (checkIfLogged()) {
            return false;
        }
        for (Permissions perm : currentUser.getPermissionsList()) {
            if (perm.getCode() == PermissionCode) {
                System.out.println("Uzytkownik ma dostep do " + perm.getName());
                return true;
            }
        }
        return false;
    }

    public boolean checkPermission(String PermissionName) {
        if (checkIfLogged()) {
            return false;
        }
        for (Permissions perm : currentUser.getPermissionsList()) {
            if (perm.getName().equals(PermissionName)) {
                System.out.println("Uzytkownik ma dostep do " + perm.getName());
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLogged() {
        return currentUser.getLogin() == null;
    }


}
