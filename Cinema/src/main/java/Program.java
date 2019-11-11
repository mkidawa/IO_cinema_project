import DBO.HallDAO;
import Model.Hall;
import Tools.Filter;
import lombok.var;

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
        /*Product p1 = new Product("Product1", new BigDecimal(17.2).setScale(2, RoundingMode.HALF_UP));
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
        sale.add(salePO2);*/

        //SaleDAO.insert(sale);
        //var result = SaleDAO.getAll();
        Hall h1 = new Hall((short) 1, (short) 0, (short) 0, 30, 20, "Sala 1", "Opis sali 1");
        Hall h2 = new Hall((short) 1, (short) 0, (short) 0, 20, 15, "Sala 2", "Opis sali 2");

        HallDAO.insertUpdate(h1);
        HallDAO.insertUpdate(h2);

        Filter f = new Filter();
        f.addField("flgX", 30);

        var result = HallDAO.getAllByFilter(f);

        System.out.println("Test");
    }

}
