package Tools;

import Controller.LPermissionController;

import java.util.List;

/**
 * Klasa odpowiedzialna za sprawdzanie zezwolen
 */
public class PermissionChecker {
    private LPermissionController lm;

    /**
     * Tworzy obiekt pozwalajacy na sprawdzanie zezwolen
     */
    public PermissionChecker() {
        lm = LPermissionController.getInstance();
    }

    /**
     * Sprawdza czy uzytkownik posiada permita z pomoca inta
     * @param PermissionCode kod zezwolenia jako int
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o kodzie PermissionCode
     */
    public boolean checkPermission(int PermissionCode) {
        return lm.checkPermission(PermissionCode);
    }

    /**
     * Sprawdza czy uzytkownik posiada permita z pomoca stringa
     * @param PermissionName nazwa zezwolenia jako string
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o nazwie PermissionName
     */
    public boolean checkPermission(String PermissionName) {
        return lm.checkPermission(PermissionName);
    }

    /**
     * Lista permitow dla aktualnego uzytkownika
     * @return Zwraca liste wszystkich zezwolen jakie ma uzytkownik
     */
    public List getPermissionsList() {
        return lm.getPermissionsList();
    }
}
