package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

import java.awt.*;

/**
 * Created by haim on 10-May-16.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * the constructor.
     * setting the fields.
     *
     * @param k - the Keyboard of the game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * the function that is in charge of drawing a frame.
     *
     * @param d - the drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        d.setColor(Color.blue);
        d.drawText(10 + 2, (d.getHeight() / 2) + 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
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
