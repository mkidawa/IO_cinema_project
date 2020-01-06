package DBO;

import Model.Sale;
import Tools.BaseDB;
import lombok.var;

import java.util.List;

public class SaleDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Sale").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAllById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Sale where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insert(Sale object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.save(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static List getOrderContent(long Id){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from SalePO where SaleHId = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }
}
