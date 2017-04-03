package view;

import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.Equation;
import model.FoundEvent;
import model.FoundListener;
import model.MyTask;
import org.w3c.dom.css.RGBColor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by qwerty on 01-Apr-17.
 */
public class guiController {
    @FXML
    private Canvas canvas;
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private ProgressBar bar;
    @FXML
    private TextField text;
    @FXML
    private Label area;


    private MyTask zadanie;
    private int repeats;

    private static class MySpeedListener implements FoundListener {
        private GraphicsContext gc;
        @Override
        public void found(FoundEvent e) {
            gc.drawImage(SwingFXUtils.toFXImage(e.getBi(), null), 0,0 );
        }

        public MySpeedListener(GraphicsContext gc) {
            this.gc = gc;
        }
    }

    @FXML
    private void handleRunBtnAction()
    {
        try {
            repeats = Integer.parseInt(text.getText());
            start.setDisable(true);
            stop.setDisable(false);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            zadanie = new MyTask(repeats);
            FoundListener listener = new MySpeedListener(gc);
            zadanie.addFoundListener(listener);
            zadanie.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    long number = (long) zadanie.getValue();
                    start.setDisable(false);
                    stop.setDisable(true);

                    long finish=number*600*400/repeats;
                    area.setText("Pole: "+ finish);
                }
            });
            bar.progressProperty().bind(zadanie.progressProperty());
            new Thread(zadanie).start();

        }catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie podano danych");
            alert.setHeaderText("Podaj liczbe powtorzen pomiaru");
            alert.setContentText("Podaj intigera");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleStop()
    {
        start.setDisable(false);
        stop.setDisable(true);
        zadanie.cancel();
    }

    @FXML
    private void initialize() {

        stop.setDisable(true);
    }
}
