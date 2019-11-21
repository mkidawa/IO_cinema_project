package Tools;

import Controller.LPermissionController;

import java.util.List;

/**
 * Klasa odpowiedzialna za sprawdzanie zezwolen
 * Uzywanie bez gui
 * Najpierw sie zalogowac za pomoca kontrolera
 *
 * LPermissionController.getInstance().login(login,password);
 *
 * Nastepnie korzystac tylko z tej klasy do sprawdzania permitow
 *
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
     * @see LPermissionController#checkPermission(int)
     * @param PermissionCode kod zezwolenia jako int
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o kodzie PermissionCode
     */
    public boolean checkPermission(int PermissionCode) {
        return lm.checkPermission(PermissionCode);
    }

    /**
     * Sprawdza czy uzytkownik posiada permita z pomoca stringa
     * @see LPermissionController#checkPermission(String)
     * @param PermissionName nazwa zezwolenia jako string
     * @return Zwraca true jezeli uzytkownik ma przypisane zezwolenie o nazwie PermissionName
     */
    public boolean checkPermission(String PermissionName) {
        return lm.checkPermission(PermissionName);
    }

    /**
     * Lista permitow dla aktualnego uzytkownika
     * @see LPermissionController#getPermissionsList()
     * @return Zwraca liste wszystkich zezwolen jakie ma uzytkownik
     */
    public List getPermissionsList() {
        return lm.getPermissionsList();
    }
}
