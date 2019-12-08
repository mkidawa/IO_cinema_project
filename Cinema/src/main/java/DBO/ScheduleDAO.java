package DBO;

import Model.DICT.ScheduleStatus;
import Model.Schedule;
import Tools.BaseDB;
import lombok.var;

import java.sql.Timestamp;
import java.util.List;

public class ScheduleDAO {

    public static List getStatusList() {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List<ScheduleStatus> result = so.createQuery("from ScheduleStatus").list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void insertUpdate(Schedule object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.saveOrUpdate(object);
        so.getTransaction()
                .commit();
        so.close();
    }

    public static List<Schedule> getScheduleBetween(Timestamp from, Timestamp to) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        List result =
                so.createQuery("from Schedule where DateFrom between :from and :to")
                        .setParameter("from", from)
                        .setParameter("to", to)
                        .list();
        so.getTransaction().commit();
        so.close();
        return result;
    }

    public static void delete(Schedule object) {
        var so = BaseDB.openConnection();
        so.beginTransaction();
        so.delete(object);
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
