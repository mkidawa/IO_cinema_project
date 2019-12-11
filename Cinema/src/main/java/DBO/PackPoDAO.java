package DBO;


import Model.PackPO;
import Tools.BaseDB;
import lombok.var;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class PackPoDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from PackPO").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insert(PackPO object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.save(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static List getAllById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from PackPO where PackHId = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void instertInto(long Hid, long productId, BigDecimal amount){
        var so = BaseDB.openConnection();
    }

    public static void removeById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("Delete from PackPO where Id = " + Id);
        query.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
    }
}
