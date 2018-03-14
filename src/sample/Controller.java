package sample;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;



public class Controller implements Initializable{

    @FXML
    private JFXButton button;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(4.0));
        transition.setNode(button);

        transition.setToX(700.0);
        transition.setToY(-400.0);
        transition.setAutoReverse(true);
        transition.setCycleCount(5);

         transition.play();

    }
}
