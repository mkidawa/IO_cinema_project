package View.TimetableModule.Util;

import Controller.StageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlStageSetup {

    /*------------------------ FIELDS REGION ------------------------*/
    private static PopOutWindow popOutWindow = new PopOutWindow();

    /*------------------------ METHODS REGION ------------------------*/
    private FxmlStageSetup() {
    }

    /**
     * LOAD SELECTED STAGE AND ITS CSS STYLING
     *
     * @param fxmlPath
     * @param fxmlStylePath
     * @param title
     * @return
     * @throws IOException
     */
    public static Stage loadFxmlStage(String fxmlPath, String fxmlStylePath, String title) {
        try {
            Scene scene = new Scene(FXMLLoader.load(FxmlStageSetup.class.getResource(fxmlPath)));
            scene.getStylesheets().add(FxmlStageSetup.class.getResource(fxmlStylePath).toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();

            return stage;
        } catch (IOException e) {
            popOutWindow.messageBox("Stage Loading Error",
                    "Cannot Properly Load Main Stage", Alert.AlertType.ERROR);
        }

        return null;
    }

    /**
     * CLOSE CURRENT STAGE BASED ON SCENE GET FROM BUTTON
     *
     * @param button
     */
    public static void closeStage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
    }

    /**
     * CLOSE CURRENT STAGE AND RELOAD SELECTED STAGE
     *
     * @param button
     * @param fxmlPath
     * @param fxmlStylePath
     * @param title
     */
    public static void reloadStage(Button button, String fxmlPath, String fxmlStylePath,
                                   String title) {
        closeStage(button);
        Stage mainStage = loadFxmlStage(fxmlPath, fxmlStylePath, title);
        StageManager.mainStage.close();
        StageManager.mainStage = mainStage;
    }
}
