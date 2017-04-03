package sample;

import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

//import java.awt.Color;
//import java.awt.image.BufferedImage;

public class Controller {

    private AsyncTask task;

    @FXML
    private Canvas canvas;
    @FXML
    private void handleRunBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        task = new AsyncTask(gc,canvas);
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                canvas = (Canvas)task.getValue();
            }
        });
        task.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Przerwałem");
            }
        });
        new Thread(task).start();

     //   drawShapes(gc);
    }
    @FXML
    private void handleStopBtnAction(){
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