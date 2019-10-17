package login;
import java.sql.*;
import login.connectionProvider;
public class loginManager {
    private static loginManager ourInstance;
    public static loginManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new loginManager();
        }
        return ourInstance;
    }
    private loginManager() {
        currentUser=new User();
    }

    private Connection conn = new connectionProvider().connectToDataBase();
    private Statement stat;
    private User currentUser;

    private boolean checkLogin(String login) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet result = stat.executeQuery("Select username from users");
            String username;
            while (result.next()) {
                username = result.getString("username");
                if (username.equals(login)) {
                    System.out.println("[Phase1] Login sie zgadza");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Brak loginu w bazie");
        return false;
    }

    private boolean checkPassword(String login, String password) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "Select username,password from users where username='" + login + "'";
            ResultSet result = stat.executeQuery(query);
//            System.out.println(result);
            String username;
            String passwordFromDataBase;
            username = result.getString("username");
            passwordFromDataBase = result.getString("password");
            if (username.equals(login) && passwordFromDataBase.equals(password)) {
                password = "";
                passwordFromDataBase = "";
                currentUser.setUserName(login);
                System.out.println("[Phase2] Haslo sie zgadza");
                return true;
            }
            else
            {
                System.out.println("Haslo sie nie zgadza");
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setAccessType() {
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "Select accessType from users where username='" + currentUser.getUserName() + "'";
        try {
            ResultSet result = stat.executeQuery(query);
            String access;
            access = result.getString("accessType");

            switch (access) {
                case "admin": {
                    currentUser.setAccessType(levelProvided.ADMIN);
                    System.out.println("[Phase3] Tryb dostepu ustawiony na admin");
                    break;
                }
                case "user": {
                    currentUser.setAccessType(levelProvided.USER);
                    System.out.println("[Phase3] Tryb dostepu ustawiony na uzytkownik");
                    break;
                }
                case "null":
                default:
                {
                    currentUser.setAccessType(levelProvided.NOTLOGGED);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String login, String password) {
        if (checkLogin(login)) {
            if (checkPassword(login, password)) {
                password = "";
                setAccessType();
            }
        }
        return false;
    }


    public boolean logout() {
        if(currentUser.getAccessType()==null) {
            System.out.println("Nie ma nikogo do wylogowania");
            return true;
        }
        try {
            currentUser.setUserName(null);
            currentUser.setAccessType(null);
            System.out.println("Wylogowano sie z systemu");
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    levelProvided getAccessLevel() {
        return currentUser.getAccessType();
    }
    public String getCurrentUserName()
    {
        return currentUser.getUserName();
    }
    public String user()
    {
        return currentUser.toString();
    }

}


