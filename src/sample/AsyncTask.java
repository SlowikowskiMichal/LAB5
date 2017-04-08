package sample;

import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsyncTask extends Task {
    private double hit;
    private WritableImage writableImage;
    private GraphicsContext gc;
    private long count;
    private ProgressBar progressBar;

    public AsyncTask(GraphicsContext gcc, long c, ProgressBar pB) {

        hit = 0;
        progressBar = pB;
        count = c;
        gc = gcc;
        writableImage = new WritableImage((int)gc.getCanvas().getWidth(),(int)gc.getCanvas().getWidth());
    }

    @Override
    protected Object call() throws Exception
    {
        Equation equation = new Equation();
        Random random = new Random();
        double x;
        double y;
        for(long i = 0; i < count; i++) {
            x = -8 + 16*random.nextDouble();
            y = -8 + 16*random.nextDouble();
            if(equation.calc(x,y)) {
                writableImage.getPixelWriter().setColor((int)((x + 8) * gc.getCanvas().getWidth()/16), (int) (-(y + 8) * gc.getCanvas().getHeight()/16 + gc.getCanvas().getHeight()), Color.YELLOW);
                hit++;
            }
            if(i%10000 == 0)
            {
                gc.drawImage(writableImage, 0, 0,gc.getCanvas().getWidth(),gc.getCanvas().getWidth());
            }

            updateProgress(i,count);
        }
        updateProgress(1,1);

        gc.drawImage(writableImage, 0, 0,gc.getCanvas().getWidth(),gc.getCanvas().getWidth());
        System.out.println(256 * hit/ count);

        return String.valueOf(256 * hit/ count);
    }

}
