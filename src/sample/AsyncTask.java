package sample;

import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by slovi on 03.04.2017.
 */
public class AsyncTask extends Task {

    private Canvas canvas;
    private GraphicsContext gc;
    private PixelWriter pixelWriter;
    private int count;
    private ProgressBar progressBar;

    public AsyncTask(GraphicsContext gcc, Canvas canvass, int c, ProgressBar pB) {

        progressBar = pB;
        count = c;
        canvas = canvass;
        gc = gcc;
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        pixelWriter = gc.getPixelWriter();
    }

    @Override
    protected Object call() throws Exception
    {
        Equation equation = new Equation();
        Random random = new Random();
        double x;
        double y;
        for(int i = 0; i < count; i++) {
            x = -8 + 16*random.nextDouble();
            y = -8 + 16*random.nextDouble();
            if(equation.calc(x,y))
             pixelWriter.setColor((int)((x + 8) * canvas.getWidth()/16), (int) (-(y + 8) *canvas.getHeight()/16 + canvas.getHeight()), Color.YELLOW);
            updateProgress(i,count);
        }
        updateProgress(1,1);


      //  for(double i = -8; i <= 8;i+=0.01)
     //       for(double j = -8; j <= 8; j+=0.01)
    //            if(equation.calc(i,j)) {
   //                 pixelWriter.setColor((int)((i + 8) * canvas.getWidth()/16), (int) (-(j + 8) *canvas.getHeight()/16 + canvas.getHeight()), Color.YELLOW);
  //              }

     //   while (true)
    //    {
   //         System.out.println(".");
  //          if (isCancelled()) break;
 //       }

        return "Sukces";
    }

}
