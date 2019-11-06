package Model;

import java.util.Date;

public class CinemaMan {
    private long cinemaManId;
    private String name;
    private String surname;
    private Date bornDate;

    public CinemaMan(long cinemaManId, String name, String surname, Date bornDate) {
        this.cinemaManId = cinemaManId;
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
    }

    public long getCinemaManId() {
        return cinemaManId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBornDate() {
        return bornDate;
    }

    @Override
    public String toString() {
        return "id=" + cinemaManId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bornDate=" + bornDate;
    }
}
