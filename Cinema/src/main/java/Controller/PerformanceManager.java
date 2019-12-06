package Controller;

import Model.Performance;

public class PerformanceManager {

    /*------------------------ FIELDS REGION ------------------------*/
    private static Performance currentPerformance;
    private static Boolean isEditable;

    /*------------------------ METHODS REGION ------------------------*/
    public static Performance getCurrentPerformance() {
        return currentPerformance;
    }

    public static void setCurrentPerformance(Performance currentPerformance) {
        if (currentPerformance != null) {
            PerformanceManager.currentPerformance = currentPerformance;
        }
    }

    public static Boolean getIsEditable() {
        return isEditable;
    }

    public static void setIsEditable(Boolean isEditable) {
        if (isEditable != null) {
            PerformanceManager.isEditable = isEditable;
        }
    }
}
