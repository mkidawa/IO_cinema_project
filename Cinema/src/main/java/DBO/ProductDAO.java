package DBO;

import Model.Product;
import Tools.BaseDB;
import lombok.var;

import java.util.List;

public class ProductDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Product").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insert(Product object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.save(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static String getNameById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createSQLQuery("Select Name from Product where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result.get(0).toString();
    }

    public static List getAllByID(long Id){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Product where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }
}