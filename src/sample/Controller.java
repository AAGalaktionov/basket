package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private JFXButton button;
    @FXML
    private JFXButton buttonAnalytically;
    @FXML
    private JFXButton buttonNumerical;
    @FXML
    private JFXButton buttonAirResistance;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;


    private Timeline fiveSecondsWonder;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        buttonNumerical.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Map<Double, Double> trace = Calculate.calculateTraectory(8.0, 45.0);
            Iterator<Double> xIter = trace.keySet().iterator();
            Iterator<Double> yIter = trace.values().iterator();

            fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50),
                    event -> {
                        if (xIter.hasNext()) {
                            //умножение это смещение для координат отрисовки
                            button.relocate(xIter.next() * 100, -yIter.next() * 100 + 400);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("УСЁ!!!");
                            alert.show();
                            fiveSecondsWonder.stop();
                        }
                    }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        });

        buttonAnalytically.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Map<Double, Double> trace = Calculate.calculateAnaliticalTraectory(8.0, 45.0);
            Iterator<Double> xIter = trace.keySet().iterator();
            Iterator<Double> yIter = trace.values().iterator();

            fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50),
                    event -> {
                        if (xIter.hasNext()) {
                            //умножение это смещение для координат отрисовки
                            button.relocate(xIter.next() * 100, -yIter.next() * 100 + 400);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("УСЁ!!!");
                            alert.show();
                            fiveSecondsWonder.stop();
                        }
                    }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        });

        buttonAirResistance.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Map<Double, Double> trace = Calculate.calculateAirResTraectory(8.0, 45.0);
            Iterator<Double> xIter = trace.keySet().iterator();
            Iterator<Double> yIter = trace.values().iterator();

            fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50),
                    event -> {
                        if (xIter.hasNext()) {
                            //умножение это смещение для координат отрисовки
                            button.relocate(xIter.next() * 100, -yIter.next() * 100 + 400);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("УСЁ!!!");
                            alert.show();
                            fiveSecondsWonder.stop();
                        }
                    }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        });


    }
}
