import Module.CinemaMan;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CinemaMan cinemaMan = new CinemaMan(1, "Mateusz", "Walczak", new Date());
        System.out.println(cinemaMan);
    }
}
