package View.Components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonWithImage extends StackPane implements Initializable {


    @FXML
    private Label buttonText;

    @FXML
    private ImageView buttonImage;

    public ButtonWithImage() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Components/ButtonWithImage.fxml"));
            //fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            Node n = fxmlLoader.load();
            this.getChildren().add(n);
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    onCustomClickProperty().get().handle(mouseEvent);
                }
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setImage(String imagePath) {
        buttonImage.setImage(new Image(imagePath));
    }

    public void setText(String text) {
        buttonText.setText(text);
    }

    private ObjectProperty<EventHandler<MouseEvent>> propertyOnCustomClick = new SimpleObjectProperty<EventHandler<MouseEvent>>();

    public final ObjectProperty<EventHandler<MouseEvent>> onCustomClickProperty() {
        return propertyOnCustomClick;
    }

    public final void setOnCustomClick(EventHandler<MouseEvent> handler) {
        propertyOnCustomClick.set(handler);
    }

    public final EventHandler<MouseEvent> getOnCustomClick() {
        return propertyOnCustomClick.get();
    }


}
