package Controller;

import DBO.MovieDAO;
import DBO.ReservationDAO;
import DBO.SaleDAO;
import DBO.UserDAO;
import Model.*;
import Tools.BaseDB;
import View.Report.ReportPanel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.var;
import org.hibernate.mapping.Table;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    static Paragraph prepareFile(String reportName) {

        Paragraph preface = new Paragraph();

        try {
            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);

            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk(reportName, fontReportTitle);

            preface.setAlignment(Element.ALIGN_CENTER);

            preface.add(img);
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add(title);
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );
            preface.add(reportTitle);
            preface.add( Chunk.NEWLINE );
            preface.add( Chunk.NEWLINE );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return preface;
    }

    public static void generateSalariesReport() {
        System.out.println("generateSalariesReport()");
    }

    public static void generateAllMoviesReport() throws IOException {

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("AllMoviesReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("All movies report");
            Paragraph content = new Paragraph();

            List<Movie> movies = MovieDAO.getAll();

            PdfPTable table = new PdfPTable(4);

            table.addCell("Title");
            table.addCell("Description");
            table.addCell("Duration");
            table.addCell("State");

            for(Movie movie : movies) {
                table.addCell(movie.getTitle());
                table.addCell(movie.getDescription());
                table.addCell(movie.getMovieTime().toString());
                table.addCell(movie.getMovieState().getName());
//                content.add(new Chunk(movie.toString(), fontContent));
            }

            content.add(table);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateWorkTimeReport(LocalDate dateFrom, LocalDate dateTo) throws IOException {

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("WorkTimeReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Work time report");
            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = null;
            if(dateFrom != null && dateTo != null) {
                tnadata = so.createQuery("from TnAData where DateDay >= '" + String.valueOf(dateFrom) + "' AND DateDay <= '" + String.valueOf(dateTo) + "'").list();
            } else {
                tnadata = so.createQuery("from TnAData").list();
            }
            so.getTransaction().commit();
            so.close();


            PdfPTable table = new PdfPTable(4);

            table.addCell("User");
            table.addCell("Date");
            table.addCell("Time from");
            table.addCell("Time to");


            List<User> currentUser = null;
            for(TnAData data : tnadata) {
                currentUser = UserDAO.getAllById(data.getUserId());
                table.addCell(String.join(" ", currentUser.get(0).getFirstName(), currentUser.get(0).getLastName()));
                table.addCell(String.valueOf(data.getDateDay()));
                table.addCell(String.valueOf(data.getTimeFrom()));
                table.addCell(String.valueOf(data.getTimeTo()));
            }

            content.add(table);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateIndividualWorkTimeReport(LocalDate dateFrom, LocalDate dateTo, long userId) throws IOException {

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("IndividualWorkTimeReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Individual Work Time report");
            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = null;
            if(dateFrom != null && dateTo != null) {
                tnadata = so.createQuery("from TnAData where  UsersId = " + String.valueOf(userId)+ " AND DateDay >= '" + String.valueOf(dateFrom) + "' AND DateDay <= '" + String.valueOf(dateTo) + "'").list();
            } else {
                tnadata = so.createQuery("from TnAData where UsersId = " + String.valueOf(userId)).list();
            }
            so.getTransaction().commit();
            so.close();



            PdfPTable table = new PdfPTable(4);

            table.addCell("User");
            table.addCell("Date");
            table.addCell("Time from");
            table.addCell("Time to");


            List<User> currentUser = null;
            for(TnAData data : tnadata) {
                currentUser = UserDAO.getAllById(data.getUserId());
                table.addCell(String.join(" ", currentUser.get(0).getFirstName(), currentUser.get(0).getLastName()));
                table.addCell(String.valueOf(data.getDateDay()));
                table.addCell(String.valueOf(data.getTimeFrom()));
                table.addCell(String.valueOf(data.getTimeTo()));
            }

            content.add(table);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateSalaryReport(LocalDate dateFrom, LocalDate dateTo) throws IOException {

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("SalaryReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Salary report");
            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = null;
            if(dateFrom != null && dateTo != null) {
                tnadata = so.createQuery("from TnAData where DateDay >= '" + String.valueOf(dateFrom) + "' AND DateDay <= '" + String.valueOf(dateTo) + "'").list();
            } else {
                tnadata = so.createQuery("from TnAData").list();
            }
            so.getTransaction().commit();
            so.close();


            PdfPTable table = new PdfPTable(4);

            table.addCell("User");
            table.addCell("Hours");
            table.addCell("Hourly rate");
            table.addCell("Salary");

            HashMap<Long, Integer> usersUniqe = new HashMap<>();

            for(TnAData data: tnadata) {
                usersUniqe.putIfAbsent(data.getUserId(), 0);
            }

            int hours = 0;
            for(TnAData data: tnadata) {
                hours = usersUniqe.get(data.getUserId());
                hours += data.getTimeTo().getHours() - data.getTimeFrom().getHours();
                usersUniqe.replace(data.getUserId(), hours);
            }

            List<User> currentUser = null;
            for(Map.Entry<Long, Integer> entry : usersUniqe.entrySet()) {
                currentUser = UserDAO.getAllById(entry.getKey());
                table.addCell(String.join(" ", currentUser.get(0).getFirstName(), currentUser.get(0).getLastName()));
                table.addCell(String.valueOf(entry.getValue()));
                table.addCell(String.valueOf(currentUser.get(0).getHourlyRate()));
                table.addCell(String.valueOf(currentUser.get(0).getHourlyRate().multiply(BigDecimal.valueOf(entry.getValue()))));
            }

            content.add(table);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateIndividualSalaryReport(LocalDate dateFrom, LocalDate dateTo, long userId) throws IOException {

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("SalaryReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Salary report");
            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = null;
            if(dateFrom != null && dateTo != null) {
                tnadata = so.createQuery("from TnAData where  UsersId = " + String.valueOf(userId)+ " AND DateDay >= '" + String.valueOf(dateFrom) + "' AND DateDay <= '" + String.valueOf(dateTo) + "'").list();
            } else {
                tnadata = so.createQuery("from TnAData where UsersId = " + String.valueOf(userId)).list();
            }
            so.getTransaction().commit();
            so.close();


            PdfPTable table = new PdfPTable(4);

            table.addCell("User");
            table.addCell("Hours");
            table.addCell("Hourly rate");
            table.addCell("Salary");

            HashMap<Long, Integer> usersUniqe = new HashMap<>();

            for(TnAData data: tnadata) {
                usersUniqe.putIfAbsent(data.getUserId(), 0);
            }

            int hours = 0;
            for(TnAData data: tnadata) {
                hours = usersUniqe.get(data.getUserId());
                hours += data.getTimeTo().getHours() - data.getTimeFrom().getHours();
                usersUniqe.replace(data.getUserId(), hours);
            }

            List<User> currentUser = null;
            for(Map.Entry<Long, Integer> entry : usersUniqe.entrySet()) {
                currentUser = UserDAO.getAllById(entry.getKey());
                table.addCell(String.join(" ", currentUser.get(0).getFirstName(), currentUser.get(0).getLastName()));
                table.addCell(String.valueOf(entry.getValue()));
                table.addCell(String.valueOf(currentUser.get(0).getHourlyRate()));
                table.addCell(String.valueOf(currentUser.get(0).getHourlyRate().multiply(BigDecimal.valueOf(entry.getValue()))));
            }

            content.add(table);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateIncomesReport(LocalDate fDate, LocalDate tDate) throws IOException {

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("IncomesReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Incomes Report");
            Paragraph content = new Paragraph();

            List<Reservation> reservations = ReservationDAO.getAll();
            List<Sale> sales = SaleDAO.getAll();

            PdfPTable table = new PdfPTable(4);
            PdfPTable tableSum = new PdfPTable(2);

            table.addCell("Income Type");
            table.addCell("Description");
            table.addCell("Date");
            table.addCell("Value");

            float fSum = 0;

            for(Reservation res : reservations) {
                if (res.getReservationPrice() == 0) continue;
                if ((fDate.compareTo(res.getReservationDate().toLocalDateTime().toLocalDate()) < 0
                        &&  tDate.compareTo(res.getReservationDate().toLocalDateTime().toLocalDate()) > 0) ||
                        (fDate.compareTo(res.getReservationDate().toLocalDateTime().toLocalDate()) == 0
                                && tDate.compareTo(res.getReservationDate().toLocalDateTime().toLocalDate()) == 0)) {
                    table.addCell("Reservation");
                    table.addCell("Timetable: " + String.valueOf(res.getTimeTable().getId()));
                    table.addCell(res.getReservationDate().toString());
                    table.addCell(String.valueOf(res.getReservationPrice()));
                    fSum += res.getReservationPrice();
                }
            }

            BigDecimal sum = new BigDecimal(fSum);

            for(Sale s : sales) {
                if (s.getPrice().equals(BigDecimal.ZERO)) continue;
                if ((fDate.compareTo(s.getSaleDate().toLocalDateTime().toLocalDate()) < 0
                        &&  tDate.compareTo(s.getSaleDate().toLocalDateTime().toLocalDate()) > 0) ||
                        (fDate.compareTo(s.getSaleDate().toLocalDateTime().toLocalDate()) == 0
                                && tDate.compareTo(s.getSaleDate().toLocalDateTime().toLocalDate()) == 0)) {
                    table.addCell("Food Sale");
                    table.addCell(String.valueOf(s.getId()));
                    table.addCell(s.getSaleDate().toString());
                    table.addCell(String.valueOf(s.getPrice()));
                    sum = sum.add(s.getPrice());
                }
            }

            tableSum.addCell("Sum:");
            tableSum.addCell(sum.toString());
            //System.out.println("SUMA " + sum.toString());

            content.add(table);
            content.add(Chunk.NEWLINE);
            content.add(Chunk.NEWLINE);
            content.add(tableSum);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateFoodSaleReport(LocalDate fDate, LocalDate tDate) throws IOException {

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("FoodSaleReport.pdf"));

            document.open();

            Paragraph preface = prepareFile("Food Sale Report");
            Paragraph content = new Paragraph();

            List<Sale> sales = SaleDAO.getAll();
            ArrayList<SalePO> positions = new ArrayList<SalePO>();
            for(Sale s : sales) {
                List<SalePO> pos = SaleDAO.getOrderContent(s.getId());
                positions.addAll(pos);
            }

            PdfPTable table = new PdfPTable(4);
            PdfPTable tableSum = new PdfPTable(2);

            table.addCell("Pack");
            table.addCell("Amount");
            table.addCell("Date");
            table.addCell("Value");

            BigDecimal sum = new BigDecimal("0.0");

            for(SalePO pos : positions) {
                List<Sale> s = SaleDAO.getAllById(pos.getSale().getId());
                if ((fDate.compareTo(s.get(0).getSaleDate().toLocalDateTime().toLocalDate()) < 0
                        &&  tDate.compareTo(s.get(0).getSaleDate().toLocalDateTime().toLocalDate()) > 0) ||
                        (fDate.compareTo(s.get(0).getSaleDate().toLocalDateTime().toLocalDate()) == 0
                                && tDate.compareTo(s.get(0).getSaleDate().toLocalDateTime().toLocalDate()) == 0)) {
                    table.addCell(String.valueOf(pos.getPack().getName()));
                    table.addCell(String.valueOf(pos.getAmount().intValue()));
                    table.addCell(s.get(0).getSaleDate().toString());
                    table.addCell(String.valueOf(pos.getPrice()));
                    sum = sum.add(pos.getPrice());
                }
            }

            tableSum.addCell("Sum:");
            tableSum.addCell(sum.toString());
            //System.out.println("SUMA " + sum.toString());


            content.add(table);
            content.add(Chunk.NEWLINE);
            content.add(Chunk.NEWLINE);
            content.add(tableSum);

            document.add(preface);
            document.add(content);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
