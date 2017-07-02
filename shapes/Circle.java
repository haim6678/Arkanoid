package shapes;

import biuoop.DrawSurface;
import spriteimplemts.Sprite;

import java.awt.*;

/**
 * Created by haim on 18-May-16.
 */
public class Circle implements Sprite {
    private int radius;
    private Point center;
    private Color color;
    private boolean fill;

    public Circle(int radius, Point center, Color color, boolean fill) {
        this.radius = radius;
        this.center = center;
        this.color = color;
        this.fill = fill;
    }

    public Point getCenter() {
        return this.center;
    }

    public int getRadius() {
        return this.radius;
    }

    public boolean getFill() {
        return this.fill;
    }

    public void drawOn(DrawSurface canvas) {
        if (this.getFill() == false) {
            canvas.setColor(this.color);
            canvas.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        } else {
            canvas.setColor(this.color);
            canvas.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
    }

    public void timePassed() {

    }
}
