package Controller;

import DBO.PermissionsDAO;
import Model.DICT.Permissions;

//import DBO.MovieDAO;
import Model.DICT.MovieState;
import Model.DICT.MovieType;
import Model.DICT.PersonType;
import Model.Movie;
import Model.Person;
import Model.PersonJob;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LPermissionController {
    private static LPermissionController ourInstance;
    public static LPermissionController getInstance() {
        if (ourInstance == null) {
            ourInstance = new LPermissionController();
        }
        return ourInstance;
    }
    public void getAccessLevel(){

    }
    public void getAllPermissions()
    {
        System.out.println(PermissionsDAO.getAll().toString());
    }

}
