package sample;

import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Created by slovi on 03.04.2017.
 */
public class AsyncTask extends Task {

    private Canvas canvas;
    private GraphicsContext gc;
    PixelWriter pixelWriter;

    public AsyncTask(GraphicsContext gcc, Canvas canvass) {
        gc = gcc;
        canvas = canvass;
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
    }

    @Override
    protected Object call() throws Exception
    {
        for(int i = 0; i < canvas.getWidth();i+=10)
            for(int j = 1; j < canvas.getHeight(); j++)
                pixelWriter.setColor(i, j, Color.YELLOW);
        for(int i = 0; i < canvas.getWidth();i++)
            for(int j = 1; j < canvas.getHeight(); j+=10)
                pixelWriter.setColor(i, j, Color.YELLOW);

        while (true)
        {
            System.out.println(".");
            if (isCancelled()) break;
        }

        return canvas;
    }

}
