package game;

import biuoop.DrawSurface;
import game.Animation;
import spriteimplemts.SpriteCollection;

import java.awt.Color;

/**
 * Created by haim on 10-May-16.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprits;
    private boolean stop;

    /**
     * the constructor.
     *
     * @param numOfSeconds - the num of seconds.
     * @param countFrom    - the num of seconds to display.
     * @param gameScreen   - the other sprite to draw.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprits = gameScreen;
        this.stop = false;
    }

    /**
     * access method.
     *
     * @return - the Sprite
     */
    public SpriteCollection getSprits() {
        return sprits;
    }

    /**
     * access method.
     *
     * @return - the num of seconds
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * access method.
     *
     * @return - the num of seconds to count.
     */
    public int getCountFrom() {

        return countFrom;
    }

    /**
     * the func that is in charge of drawing one frame.
     *
     * @param d - the drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        this.getSprits().drawAllOn(d);
        if (this.countFrom > 0) {
            d.drawText(300, 360, Integer.toString(this.countFrom), 20);
            this.countFrom--;
        } else if (this.countFrom == 0) {
            d.setColor(Color.red);
            d.drawText(300, 380, "GO", 25);
            this.countFrom--;
        } else if (this.countFrom == -1) {
            this.countFrom = 3;
            this.stop = true;
        }
    }

    /**
     * the condition to stop the animation runner.
     *
     * @return - the boolean variable
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
