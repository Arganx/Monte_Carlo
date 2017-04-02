package model;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by qwerty on 02-Apr-17.
 */
public class MyTask extends Task {

    public MyTask(GraphicsContext grc,int rep) {
        gc=grc;
        repeats =rep;
    }
    private  GraphicsContext gc;
    private int repeats;
    @Override
    protected Object call() throws Exception {

        double x;
        double y;
        double min=-8;
        double max=8;
        Random random;
        int printer=0;
        long shot=0;
        BufferedImage bi = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<repeats;i++)
        {
            if(isCancelled()==true)
            {
                break;
            }
            printer++;
            random = new Random();
            x=min+(max-min)*random.nextDouble();
            random = new Random();
            y=min+(max-min)*random.nextDouble();
            if(Equation.calc(x,y)==true)
            {
                x = ((600-0) * (x-min) / (max-min) + 0);
                y = ((400-0) * (y-min) / (max-min) + 0);
                y-=400;
                y*=-1;
                shot++;
                bi.setRGB((int)x,(int)y,java.awt.Color.yellow.getRGB());
            }
            else
            {
                x = ((600-0) * (x-min) / (max-min) + 0);
                y = ((400-0) * (y-min) / (max-min) + 0);
                y-=400;
                y*=-1;
                bi.setRGB((int)x,(int)y,java.awt.Color.blue.getRGB());
            }
            if(printer==100000)
            {
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
                printer=0;
            }
            updateProgress(i,repeats);
        }
        updateProgress(1,1);
        gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
        return shot;
    }

}
