package DBO;
import java.sql.*;

public class BaseDB {
    private static String username;
    private static String password;
    private static String host;
    private static String dbname;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        BaseDB.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        BaseDB.password = password;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        BaseDB.host = host;
    }

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        BaseDB.dbname = dbname;
    }

    public static boolean testConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connection_string = String.format("jdbc:sqlserver://%s;databaseName=%s", BaseDB.host, BaseDB.dbname);
            Connection conn = DriverManager.getConnection(connection_string, BaseDB.username, BaseDB.password);
            return (conn != null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ResultSet execStoredProcedure(String queryString) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connection_string = String.format("jdbc:sqlserver://%s;databaseName=%s", host, dbname);
            Connection conn = DriverManager.getConnection(connection_string, username, password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
