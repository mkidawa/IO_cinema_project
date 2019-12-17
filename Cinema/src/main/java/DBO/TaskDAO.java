package DBO;

import Model.Task;
import Tools.BaseDB;
import lombok.var;
import java.util.List;

public class TaskDAO {

    public static List<Task> getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List<Task> result = so.createQuery("from Task").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insertUpdate(Task object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.saveOrUpdate(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static void delete(Task object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.delete(object);
        so.getTransaction()
                .commit();
        so.close();
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
