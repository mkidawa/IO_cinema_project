import DBO.SaleDAO;
import Model.*;
import Tools.BaseDB;
import lombok.var;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;

public class Program {
    public static void main(String[] args) {
        //TODO Main program file. System starts here
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void test() throws Exception {
        // create a couple of events...
       /* Session session = BaseDB.openConnection();
        session.beginTransaction();
        //session.save(new MovieType("Test"));
        //session.save(new MovieState("Test"));
        Product p1 = new Product("Product1", new BigDecimal(17.2).setScale(2, RoundingMode.HALF_UP));
        Product p2 = new Product("Product2", new BigDecimal(30.7).setScale(2, RoundingMode.HALF_UP));
        Product p3 = new Product("Product3", new BigDecimal(11.23).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO1 = new PackPO(p1, new BigDecimal(3).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO2 = new PackPO(p2, new BigDecimal(1).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO3 = new PackPO(p3, new BigDecimal(2).setScale(2, RoundingMode.HALF_UP));
        Pack pack1 = new Pack(new LinkedList<>(), "Test");
        pack1.getPositions().add(packPO1);
        pack1.getPositions().add(packPO2);
        Pack pack2 = new Pack(new LinkedList<>(), "Test");
        pack2.getPositions().add(packPO3);
        SalePO salePO1 = new SalePO(pack1, new BigDecimal(2).setScale(2, RoundingMode.HALF_UP));
        SalePO salePO2 = new SalePO(pack2, new BigDecimal(3).setScale(2, RoundingMode.HALF_UP));
        Sale sale = new Sale(new LinkedList<>(), new Timestamp(new Date().getTime()));
        sale.getPositions().add(salePO1);
        sale.getPositions().add(salePO2);
        session.save(sale);
        session.getTransaction()
                .commit();
        session.close();*/

        // now lets pull events from the database and list them
        /*session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Movie").list();
        for (Movie item : (List<Movie>) result) {
            System.out.println("Movie (" + item.getTitle() + ") : " + item.getDescription() + " Type = " + item.getMovieType().getName());
        }
        session.getTransaction().commit();
        session.close();*/


        Product p1 = new Product("Product1", new BigDecimal(17.2).setScale(2, RoundingMode.HALF_UP));
        Product p2 = new Product("Product2", new BigDecimal(30.7).setScale(2, RoundingMode.HALF_UP));
        Product p3 = new Product("Product3", new BigDecimal(11.23).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO1 = new PackPO(p1, new BigDecimal(3).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO2 = new PackPO(p2, new BigDecimal(1).setScale(2, RoundingMode.HALF_UP));
        PackPO packPO3 = new PackPO(p3, new BigDecimal(2).setScale(2, RoundingMode.HALF_UP));
        Pack pack1 = new Pack("Test");
        pack1.add(packPO1);
        pack1.add(packPO2);
        Pack pack2 = new Pack("Test");
        pack2.add(packPO3);
        SalePO salePO1 = new SalePO(pack1, new BigDecimal(2).setScale(2, RoundingMode.HALF_UP));
        SalePO salePO2 = new SalePO(pack2, new BigDecimal(3).setScale(2, RoundingMode.HALF_UP));
        Sale sale = new Sale();
        sale.add(salePO1);
        sale.add(salePO2);

        SaleDAO.insert(sale);
        var result = SaleDAO.getAll();
        System.out.println("Test");
    }

}
