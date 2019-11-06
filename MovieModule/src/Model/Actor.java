package Model;

import java.util.Date;

public class Actor extends CinemaMan{
    public Actor(long id, String name, String surname, Date bornDate) {
        super(id, name, surname, bornDate);
    }

    @Override
    public String toString() {
        return "Actor {" + super.toString() + "}";
    }
}
