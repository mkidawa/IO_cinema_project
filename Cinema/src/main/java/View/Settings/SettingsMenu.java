package View.Settings;

import Tools.SettingsTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsMenu implements Initializable {

    @FXML
    private TextField addTime;

    @FXML
    private TextField someSettings;

    @FXML private TextField ticketPrice2D;
    @FXML private TextField ticketPrice3D;
    @FXML private TextField ticketPriceVR;

    @FXML
    private Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTime.setText(SettingsTool.getSetting("AddTime"));
        ticketPrice2D.setText(SettingsTool.getSetting("TicketPrice2D"));
        ticketPrice3D.setText(SettingsTool.getSetting("TicketPrice3D"));
        ticketPriceVR.setText(SettingsTool.getSetting("TicketPriceVR"));
    }

    public void save(MouseEvent mouseEvent){
        SettingsTool.setSettings("AddTime", addTime.getText());
        SettingsTool.setSettings("TicketPrice2D", ticketPrice2D.getText());
        SettingsTool.setSettings("TicketPrice3D", ticketPrice3D.getText());
        SettingsTool.setSettings("TicketPriceVR", ticketPriceVR.getText());
        SettingsTool.saveSettings();
        ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
    }

    public void cancel(MouseEvent mouseEvent){
        ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
    }
}
