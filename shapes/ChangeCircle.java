package shapes;

import biuoop.DrawSurface;
import shapes.Circle;
import spriteimplemts.Sprite;

import java.awt.*;
import java.util.Random;

/**
 * Created by haim on 18-May-16.
 */
public class ChangeCircle implements Sprite {

    private Circle circle;

    public ChangeCircle(Circle circle) {
        this.circle = circle;
    }

    public boolean getFill() {
        return this.circle.getFill();
    }

    public void drawOn(DrawSurface canvas) {
        Random rand = new Random();
        if (this.circle.getFill() == false) {
            canvas.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            canvas.drawCircle((int) this.circle.getCenter().getX(), (int) this.circle.getCenter().getY(), this.circle.getRadius());
        } else {
            canvas.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            canvas.fillCircle((int) this.circle.getCenter().getX(), (int) this.circle.getCenter().getY(), this.circle.getRadius());
        }
    }

    public void timePassed() {

    }

}
