package Controller;

import DBO.MovieDAO;
import Model.Movie;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.mapping.Table;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}
