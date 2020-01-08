package DBO;

import Model.Reservation;
import Tools.BaseDB;
import lombok.var;

import java.util.List;

public class ReservationDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Reservation").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insert(Reservation object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.save(object);
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
