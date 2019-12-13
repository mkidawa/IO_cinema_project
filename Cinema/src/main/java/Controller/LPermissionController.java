package Controller;

import DBO.UserDAO;
import Model.DICT.Permissions;
import Model.User;
import Tools.Filter;

import java.util.Collections;
import java.util.List;


/*
TODO Szyfrowanie hasel ?
TODO GUI
*/

/**
 * Kontroler odpowiedzialny za autoryzacje uzytkownika
 * Uzywac tylko przez PermissionChecker
 * @see Tools.PermissionChecker
 */
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
            System.err.println("Brak uzytkownika w bazie");
            throw new Error("Brak uzytkownika w bazie");
//            return false;
        } else if (result.size() == 1 && result.get(0).toString().equals(login)) {
            System.out.println("Znalazlem login: " + login);
            return true;
        }
        return false;
    }


    /**
     * Autoryzacja uzytkownika w systemie
     * @param login Login uzytkownika
     * @param password Haslo Uzytkownika
     * @return true jezeli autoryzacja przebiegla pomyslnie , inaczej false
     */
    public boolean login(String login, String password) {
        if (checkLogin(login)) {
            if (checkPassword(login, password)) {
                currentUser.setPasswordHash("");
                return true;
            }
        }
        throw new Error("Logowanie nie powiodło się");
//        return false;
    }
    private boolean checkLoginCode(String code) {
//
        String sql = "SELECT U.login from User U WHERE U.codeHash='" + code +
                "'";
        List result = UserDAO.execSQL(sql);
        if (result.size() == 0) {
            System.err.println("Bledny Kod");
            throw new Error("Bledny kod");
//            return false;
        } else if (result.size() == 1) {
            System.out.println(result.toString());
            System.out.println("Znalazlem login: " + result.get(0));
            return true;
        }
        return false;
    }
    private boolean checkCode(String code){
        String sql = "from User U WHERE U.codeHash='" + code + "'";
        User result = (User) UserDAO.execSQL(sql).get(0);
        if (result.getCodeHash().equals(code)) {
            currentUser = result;
            System.out.println("Prawidlowe Kod");
            return true;
        }
        System.err.println("Kod nie jest prawidlowy");
        throw new Error("Kod nie jest prawidlowy");

    }

        public boolean login(String code){
        if(checkLoginCode(code)){
            if(checkCode(code)){
                currentUser.setPasswordHash("");
                return true;
            }
        }
        throw new Error("Logowanie nie powiodło się");
//        return false;

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
        System.err.println("Haslo nie prawidlowe");
        throw new Error("Haslo nie prawidlowe");
//        return false;
    }

    /**
     * Lista permitow dla aktualnego uzytkownika
     * @return Zwraca liste wszystkich zezwolen jakie ma uzytkownik
     */
    public List getPermissionsList() {
        if (checkIfLogged()) {
            return Collections.emptyList();
        }
        System.out.println(currentUser.getPermissionsList());
        return currentUser.getPermissionsList();
    }

    /**
     * Sprawdza czy uzytkownik posiada permita z pomoca inta
     * @param PermissionCode kod zezwolenia jako int
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o kodzie PermissionCode
     */
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

    /**
     * Sprawdza czy uzytkownik posiada permita z pomoca stringa
     * @param PermissionName nazwa zezwolenia jako string
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o nazwie PermissionName
     */
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
