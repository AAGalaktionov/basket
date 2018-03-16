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
    private Alert alert = null;
    private double v0;
    private double angle;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        alert = new Alert(Alert.AlertType.INFORMATION);

        buttonNumerical.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            getParams();
            show(Calculate.calculateTraectory(v0, angle));
        });

        buttonAnalytically.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            getParams();
            show(Calculate.calculateAnaliticalTraectory(v0, angle));
        });

        buttonAirResistance.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            getParams();
            show(Calculate.calculateAirResTraectory(v0, angle));
        });

        textField1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> textField1.setText(""));
        textField2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> textField2.setText(""));

    }

    private void getParams() {
        try {
            v0 = Double.parseDouble(textField1.getText());
            angle = Double.parseDouble(textField2.getText());
            if (v0 <= 0 || (angle < 0 && angle > 360))
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            v0 = 0;
            angle = 0;
            alert.setContentText("Некорректные входные параметры!!!");
            alert.show();
        }
    }

    private void show(Map<Double, Double> trace) {
        //При пустом решение и отрисовывать нечего
        if (trace.size() <= 1) return;
        alert.setContentText("Конец!!!");
        Iterator<Map.Entry<Double, Double>> iterator = trace.entrySet().iterator();
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50),
                event -> {
                    if (iterator.hasNext()) {
                        Map.Entry<Double, Double> entry = iterator.next();
                        //умножение и сложение это смещение для координат отрисовки
                        button.relocate(entry.getKey() * 100, -entry.getValue() * 100 + 400);
                    } else {
                        alert.show();
                        fiveSecondsWonder.stop();
                    }
                }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
}
