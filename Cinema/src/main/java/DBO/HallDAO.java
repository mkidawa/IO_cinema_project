package DBO;

import Model.Hall;
import Tools.BaseDB;
import Tools.Filter;
import lombok.var;

import java.util.List;

public class HallDAO {
    public static List getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Hall").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAllById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result = so.createQuery("from Hall where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static List getAllByFilter(Filter filter) {
        StringBuilder sql = new StringBuilder("from Hall ");
        int size = filter.getFilters().size();
        if (size > 0) {
            int count = 0;
            sql.append("where ");
            for (var item : filter.getFilters().entrySet()) {
                sql.append(item.getKey()).append(" = \'").append(item.getValue().toString()).append("\'");
                count++;
                if (count < size) {
                    sql.append(" and ");
                }
            }
        }
        //System.out.println(sql);
        return execSQL(sql.toString());
    }

    public static void insertUpdate(Hall object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.saveOrUpdate(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static void delete(Hall object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        String hql = "delete Hall where Id = :id";
        var q = so.createQuery(hql).setParameter("id", object.getId());
        q.executeUpdate();
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
