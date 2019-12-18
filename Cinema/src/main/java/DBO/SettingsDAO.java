package DBO;

import Model.SETTINGS;
import Model.Sale;
import Tools.BaseDB;
import lombok.var;

import java.util.List;

public class SettingsDAO {

    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from SETTINGS").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insertUpdate(SETTINGS object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.saveOrUpdate(object);
        so.getTransaction().commit();
        so.close();
    }

    public static SETTINGS getByKey(String key){
        var so = BaseDB.openConnection();
        so.beginTransaction();
        var result = so.createQuery("from SETTINGS where Symbol = :Symbol").setParameter("Symbol", key).uniqueResult();
        so.getTransaction().commit();
        so.close();
        return (SETTINGS) result;
    }
}
