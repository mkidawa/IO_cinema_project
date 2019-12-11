package DBO;

import Model.Pack;
import Tools.BaseDB;
import lombok.var;
import org.hibernate.query.Query;

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

    public static List getAllByID(long Id){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Pack where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void removeById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("Delete from Pack where Id = " + Id);
        query.executeUpdate();
        Query query1= so.createQuery("Delete from PackPO where PackHId = " + Id);
        query1.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
    }

    public static boolean changeName(long id, String newName){

        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("UPDATE Pack SET Name = '" + newName + "' WHERE id = " + id);
        int res = query.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
        return res == 1;
    }

    public static boolean changePrice(long id, String newPrice){

        var so = BaseDB.openConnection();
        so.beginTransaction();
        Query query = so.createQuery("UPDATE Pack SET _Price = '" + newPrice + "' WHERE id = " + id);
        int res = query.executeUpdate();
        so.getTransaction()
                .commit();
        so.close();
        return res == 1;
    }
}
