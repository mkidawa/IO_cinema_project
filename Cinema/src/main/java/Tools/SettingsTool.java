package Tools;

import DBO.SettingsDAO;
import Model.SETTINGS;
import lombok.var;

import java.util.HashMap;
import java.util.List;

public class SettingsTool {

    private static HashMap<String, String> settings = new HashMap<>();

    public static void loadSettings() {
        List<SETTINGS> data = SettingsDAO.getAll();
        settings.clear();
        for (var item : data) {
            settings.put(item.getSymbol(), item.getValue());
        }
    }

    public static void setSettings(String key, String value) {
        settings.put(key, value);
    }

    public static void saveSettings() {
        for (var item : settings.entrySet()) {
            var setting = SettingsDAO.getByKey(item.getKey());
            if (setting != null) {
                setting.setValue(item.getValue());
            } else {
                setting = new SETTINGS(item.getKey(), item.getValue());
            }
            SettingsDAO.insertUpdate(setting);
        }
    }

    public static String getSetting(String key) {
        return settings.getOrDefault(key, "");
    }

    public static Integer getSettingInt(String key) {
        return Integer.valueOf(settings.getOrDefault(key, "0"));
    }
}
