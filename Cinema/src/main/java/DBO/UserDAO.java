package DBO;

import Model.User;
import Tools.BaseDB;
import Tools.Filter;
import lombok.var;
import java.util.List;

public class UserDAO {
    public static List<User> getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List<User> result = so.createQuery("from User").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAllById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from User where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAllByFilter(Filter filter) {
        StringBuilder sql = new StringBuilder("from User ");
        int size = filter.getFilters().size();
        if (size > 0) {
            int count = 0;
            sql.append("where ");
            for (var item : filter.getFilters().entrySet()) {
                sql.append(item.getKey()).append(" = \'").append(item.getValue().toString()).append("\'");
                count++;
                if (count < size) {
                    sql.append(" and ");
                }
            }
        }
        return execSQL(sql.toString());
    }

    public static List execSQL(String sql) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery(sql).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }
}
