package DBO;

import Model.Pack;
import Tools.BaseDB;
import lombok.var;

import java.util.List;

public class PackDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Pack").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insert(Pack object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.save(object);
        so.getTransaction()
                .commit();
        so.close();
    }
}
