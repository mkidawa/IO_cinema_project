package Controller;

import Model.Performance;

public class PerformanceManager {

    /*------------------------ FIELDS REGION ------------------------*/
    private static Performance currentPerformance;

    /*------------------------ METHODS REGION ------------------------*/
    public static Performance getCurrentPerformance() {
        return currentPerformance;
    }

    public static void setCurrentPerformance(Performance currentPerformance) {
        if (currentPerformance != null) {
            PerformanceManager.currentPerformance = currentPerformance;
        }
    }
}
