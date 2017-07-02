package spriteimplemts;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;

/**
 * Created by haim on 18-May-16.
 */
public class MultipleCircleSprite implements Sprite {

    private List radius;
    private List<shapes.Point> centers;
    private List<Color> colors;
    private boolean fill = false;

    public void setVariousDetails(List radii, List centers, List<Color> colors, boolean fill) {
        this.radius = radii;
        this.centers = centers;
        this.colors = colors;
        this.fill = fill;
    }
    public void setSameDetails(int radius, Color color, List<shapes.Point> centers) {

    }

    public void drawOn(DrawSurface canvas) {
        if(this.fill = false) {
            for (int i = 0; i < centers.size(); i++) {
                canvas.setColor(this.colors.get(i));
                canvas.drawCircle((int) centers.get(i).getX(), (int) centers.get(i).getY(), (int) radius.get(i));
            }
        } else {
            //Fills the circles
            for (int i = 0; i < centers.size(); i++) {
                canvas.setColor(this.colors.get(i));
                canvas.fillCircle((int) centers.get(i).getX(), (int) centers.get(i).getY(), (int) radius.get(i));
            }
        }
    }


    public void timePassed() {

    }
}
