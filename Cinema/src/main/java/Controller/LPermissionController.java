package Controller;

import DBO.UserDAO;
import Model.DICT.Permissions;
import Model.User;
import Tools.Filter;

import Tools.LoginException;


import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    Timer timer;
    boolean timerSet;
    private User currentUser;
    private static LPermissionController ourInstance;

    public Integer getFailCounter() {
        return failCounter;
    }
    private Integer failCounter = 0;
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
            System.err.println("No user in the database");
            failCounter++;
            throw new LoginException("No user in the database");

        } else if (result.size() == 1 && result.get(0).toString().equals(login)) {
            System.out.println("Found login: " + login);
            return true;
        }
        return false;
    }

    private boolean checkPassword(String login, String password) {
        String sql = "from User U WHERE U.login='" + login +
                "'";
        User result = (User) UserDAO.execSQL(sql).get(0);
        if (result.getPasswordHash().equals(password)) {
            currentUser = result;
            System.out.println("Correct Password");
            return true;
        }
        else {
            System.err.println("Password is not correct");
            failCounter++;
            throw new LoginException("Password is not correct");
//        return false;
        }
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
                failCounter=0;
                return true;
            }
        }
        throw new LoginException("Logowanie nie powiodło się");
    }
    private boolean checkLoginCode(String code) {
//
        String sql = "SELECT U.login from User U WHERE U.codeHash='" + code +
                "'";
        List result = UserDAO.execSQL(sql);
        if (result.size() == 0) {
            System.err.println("Invalid code");
            failCounter++;
            throw new LoginException("Invalid code");
//            return false;
        } else if (result.size() == 1) {
            System.out.println(result.toString());
            System.out.println("Found login: " + result.get(0));
            return true;
        }
        return false;
    }

    private boolean checkCode(String code){
        String sql = "from User U WHERE U.codeHash='" + code + "'";
        User result = (User) UserDAO.execSQL(sql).get(0);
        if (result.getCodeHash().equals(code)) {
            currentUser = result;
            System.out.println("Correct Code");
            return true;
        }
        System.err.println("The code is not valid");
        failCounter++;
        throw new LoginException("The code is not valid");

    }

        public boolean login(String code){
        if(checkLoginCode(code)){
            if(checkCode(code)){
                currentUser.setPasswordHash("");
                failCounter=0;
                return true;
            }
        }
        throw new LoginException("Login Failed");
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
                System.out.println("The user has access to " + perm.getName());
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
                System.out.println("The user has access to " + perm.getName());
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLogged() {
        return currentUser.getLogin() == null;
    }
    public void checkFailCounter() throws Exception {
        class RemindTask extends TimerTask {
            public void run() {
                failCounter=0;
                timerSet=false;
                System.out.println("The trial counter has been reset");
                timer.cancel(); //Terminate the timer thread
            }
        }
        if(getFailCounter()>=4)
        {
            failCounter=5;
            if(!timerSet) {
                timer = new Timer();
//                How many seconds for timeout
                int seconds = 5;
                timer.schedule(new RemindTask(), seconds * 1000);
                timerSet=true;
            }
            throw new Exception("Limit of 5 login attempts exceeded");
        }
    }


    public User getCurrentUser() {
        return currentUser;
    }
    public void logOut(){
        System.out.println("LogOut currentUser");
        currentUser=null;
        System.out.println(currentUser);
    }
}
