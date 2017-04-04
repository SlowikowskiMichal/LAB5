package sample;

import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.concurrent.TimeUnit;

public class Controller {

    private AsyncTask task;

    @FXML
    private ProgressBar progressBar;
    @FXML
    TextField textField;
    @FXML
    private Canvas canvas;
    @FXML
    private void handleRunBtnAction() {

        int count = Integer.parseInt(textField.getText());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        task = new AsyncTask(gc,canvas,count,progressBar);

        progressBar.progressProperty().bind(task.progressProperty());


        //==============================================================================================================

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println((String) task.getValue());
            }
        });

        task.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Przerwałem");
            }
        });

        //==============================================================================================================

        new Thread(task).start();

     //   drawShapes(gc);
    }
    @FXML
    private void handleStopBtnAction(){
        if(!task.isCancelled())
            task.cancel();
        //   drawShapes(gc);
    }

 /*   private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                        gc.getCanvas().getLayoutY(),
                        gc.getCanvas().getWidth(),
                        gc.getCanvas().getHeight());

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for(int i = 0; i < canvas.getWidth();i+=10)
            for(int j = 1; j < canvas.getHeight(); j++)
                pixelWriter.setColor(i, j, Color.YELLOW);
        for(int i = 0; i < canvas.getWidth();i++)
            for(int j = 1; j < canvas.getHeight(); j+=10)
                pixelWriter.setColor(i, j, Color.YELLOW);
    }*/

}