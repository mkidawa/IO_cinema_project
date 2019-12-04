package DBO;

import Model.Performance;
import Tools.BaseDB;
import Tools.Filter;
import lombok.var;

import java.util.List;

public class PerformanceDAO {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public static List<Performance> getAll() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List<Performance> result = so.createQuery("from Performance").list();
        so.getTransaction().commit();
        so.close();

        return result;
    }

    public static List<Performance> getAllById(long Id) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List<Performance> result = so
                .createQuery("from Performance where Id = " + String.valueOf(Id)).list();
        so.getTransaction().commit();
        so.close();

        return result;
    }

    public static List getAllByFilter(Filter filter) {
        StringBuilder sql = new StringBuilder("from Performance ");
        int size = filter.getFilters().size();
        if (size > 0) {
            int count = 0;
            sql.append("where ");
            for (var item : filter.getFilters().entrySet()) {
                sql.append(item.getKey()).append(" = \'")
                        .append(item.getValue().toString()).append("\'");

                count++;
                if (count < size) {
                    sql.append(" and ");
                }
            }
        }
        //System.out.println(sql);
        return execSQL(sql.toString());
    }

    public static void insertUpdate(Performance object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.saveOrUpdate(object);
        so.getTransaction().commit();
        so.close();
    }

    public static void delete(Performance object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();

        String hql = "delete Performance where Id = :id";
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

    //    TODO ADD REAL IMPLEMENTATION
    public static Integer getAdsDuration() {
        return 1;
    }

    //    TODO ADD REAL IMPLEMENTATION
    public static void updateAdsDuration(Integer value) {

    }

    //    TODO ADD REAL IMPLEMENTATION
    public static Integer getMinTimeInterval() {
        return 2;
    }

    //    TODO ADD REAL IMPLEMENTATION
    public static void updateMinTimeInterval(Integer value) {

    }
}
