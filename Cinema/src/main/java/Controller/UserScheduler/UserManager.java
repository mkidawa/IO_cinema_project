package Controller.UserScheduler;

import DBO.UserDAO;
import Model.User;

import java.util.List;

public class UserManager {

    private static List<User> users = null;

    public List<User> getAllUsers() {
        if (users == null) {
            users = UserDAO.getAll();
        }
        return users;
    }
    public void updateUser(User user) {
        UserDAO.insertUpdate(user);
    }

    public void deleteUser(User user) {
        UserDAO.delete(user);
    }

}
