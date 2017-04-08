package sample;


import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class Controller {

    private AsyncTask task;

    @FXML
    private Label label;
    @FXML
    private ProgressBar progressBar;
    @FXML
    TextField textField;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    private void handleRunBtnAction() {

        long count = Integer.parseInt(textField.getText());
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        task = new AsyncTask(gc,count,progressBar);

        progressBar.progressProperty().bind(task.progressProperty());


        //==============================================================================================================

        task.setOnSucceeded(event -> label.setText((String)task.getValue()));

        task.setOnCancelled(event -> System.out.println("Przerwałem"));

        //==============================================================================================================

        new Thread(task).start();

    }
    @FXML
    private void handleStopBtnAction(){
        if(!task.isCancelled())
            task.cancel();
    }
}