package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

/**
 * Created by haim on 10-May-16.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * the constructor.
     * setting the fields.
     */
    public AnimationRunner() {
        this.framesPerSecond = 70;
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
    }

    /**
     * access method.
     *
     * @return - the gui that being drawing on.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * access method.
     *
     * @return - the number of framesPerSecond
     */
    private int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * set method.
     * setting the FramesPerSecond
     *
     * @param framesPerSecond - the given value for FramesPerSecond
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * the function that running the actual animation on screen.
     * getting a animation interface and running it according to it's,
     * stop condition and do one frame.
     *
     * @param animation - the given animation interface
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 10000 / this.getFramesPerSecond();
        double count = 0;

        // running until the condition stop is false
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.getGui().getDrawSurface();
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 800, 600);
            count++;
            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
