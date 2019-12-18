package DBO;

import Model.Product;
import Tools.BaseDB;
import lombok.var;
import org.hibernate.query.Query;

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


    public static void removeById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("Delete from Product where Id = " + Id);
        query.executeUpdate();
        Query query1= so.createQuery("Delete from PackPO where ProductId = " + Id);
        query1.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
    }

    public static List getAllByID(long Id){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Product where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAmountById(long Id){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createSQLQuery("Select amount from Product where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static boolean updateAmount(long Id, int amount) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("UPDATE Product SET Amount = Amount -'" + amount + "' WHERE Id = " + Id);
        int res = query.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
        return res == 1;
    }



}