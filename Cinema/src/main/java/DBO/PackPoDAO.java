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


    public static Boolean checkAmount(long Id, int amount, int prevAmount){

        var so = BaseDB.openConnection();
        so.beginTransaction();

        List result = so.createQuery("from PackPO WHERE PackHId = " + Id).list();
        int am, magAmount;
        long idProd;

        for(int i = 0; i < result.size(); i++) {
            am = prevAmount + (((PackPO) result.get(i)).getAmount()).intValue() * amount;
            idProd = ((PackPO) result.get(i)).getProduct().getId();
            magAmount = ((Integer) ProductDAO.getAmountById(idProd).get(0));
            if(magAmount < am) {
                return false;
            }

        }
        so.getTransaction().commit();
        System.out.print(result.get(0).toString());
        so.close();
        return true;
    }
}
