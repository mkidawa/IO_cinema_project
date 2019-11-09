import Model.*;
import DBO.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        BaseDB.setHost("localhost");
        BaseDB.setDbname("cinema");
        BaseDB.setUsername("sa");
        BaseDB.setPassword("12345");

        System.out.println("test connection answer: "  + BaseDB.testConnection());

        ResultSet result = BaseDB.execStoredProcedure("Select *from Movie where Id > 2");
        while (result.next()) {
            String title = result.getString("Title");
            System.out.println(title);
        }
    }
}
