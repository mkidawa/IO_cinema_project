package Model;

import java.util.Date;

public class Director extends CinemaMan{

    public Director(long id, String name, String surname, Date bornDate) {
        super(id, name, surname, bornDate);
    }

    @Override
    public String toString() {
        return "Director {" + super.toString() + "}";
    }
}
