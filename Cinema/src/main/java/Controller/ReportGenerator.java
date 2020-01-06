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
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    public static void generateSalariesReport() {
        System.out.println("generateSalariesReport()");
    }

    public static void generateAllMoviesReport() throws IOException {

        try {

            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Document document = new Document();
//            document.setMargins(1,1, 1,1);

            PdfWriter.getInstance(document, new FileOutputStream("AllMoviesReport.pdf"));

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);



            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk("All movies report", fontReportTitle);

            Paragraph preface = new Paragraph();
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

    public static void generateWorkTimeReport() throws IOException {
        System.out.println();


        try {
            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Document document = new Document();
//            document.setMargins(1,1, 1,1);

            PdfWriter.getInstance(document, new FileOutputStream("WorkTimeReport.pdf"));

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);



            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk("Work Time report", fontReportTitle);

            Paragraph preface = new Paragraph();
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


            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = so.createQuery("from TnAData").list();
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

    public static void generateIndividualWorkTimeReport(long userId) throws IOException {


        try {
            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Document document = new Document();
//            document.setMargins(1,1, 1,1);

            PdfWriter.getInstance(document, new FileOutputStream("WorkTimeReport.pdf"));

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);



            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk("Work Time report", fontReportTitle);

            Paragraph preface = new Paragraph();
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


            Paragraph content = new Paragraph();

            var so = BaseDB.openConnection();
            so.beginTransaction();
            List<TnAData> tnadata = so.createQuery("from TnAData where Id = " + String.valueOf(userId)).list();
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


    public static void generateIncomesReport() throws IOException {

        try {

            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("IncomesReport.pdf"));

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);

            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk("Incomes report", fontReportTitle);

            Paragraph preface = new Paragraph();
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
                table.addCell("Reservation");
                table.addCell("Timetable: " + String.valueOf(res.getTimeTable().getId()));
                table.addCell(res.getReservationDate().toString());
                table.addCell(String.valueOf(res.getReservationPrice()));
                fSum += res.getReservationPrice();
            }

            BigDecimal sum = new BigDecimal(fSum);

            for(Sale s : sales) {
                if (s.getPrice().equals(BigDecimal.ZERO)) continue;
                table.addCell("Food Sale");
                table.addCell(String.valueOf(s.getId()));
                table.addCell(s.getSaleDate().toString());
                table.addCell(String.valueOf(s.getPrice()));
                sum = sum.add(s.getPrice());
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

    public static void generateFoodSaleReport() throws IOException {

        try {

            Path path = Paths.get(ClassLoader.getSystemResource("Images/logo.png").toURI());

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("FoodSaleReport.pdf"));

            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
            Font fontReportTitle = FontFactory.getFont(FontFactory.COURIER, 28, BaseColor.BLACK);
            Font fontContent = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            Image img = Image.getInstance(path.toAbsolutePath().toString());

            img.scaleToFit(PageSize.A4.getWidth()/5, PageSize.A4.getHeight()/5);
            float x = (float) ((PageSize.A4.getWidth() - img.getScaledWidth()) * 0.5);
            float y = (float) (PageSize.A4.getHeight() - img.getScaledHeight() * 1.1);
            img.setAbsolutePosition(x, y);

            Chunk title = new Chunk("Cinema management system", fontTitle);
            Chunk reportTitle = new Chunk("Food Sale Report", fontReportTitle);

            Paragraph preface = new Paragraph();
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
                table.addCell(String.valueOf(pos.getPack().getName()));
                table.addCell(String.valueOf(pos.getAmount().intValue()));
                table.addCell(s.get(0).getSaleDate().toString());
                table.addCell(String.valueOf(pos.getPrice()));
                sum = sum.add(pos.getPrice());
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
