package game;

import biuoop.DrawSurface;

/**
 * Created by haim on 10-May-16.
 */
public interface Animation {

    /**
     * in charge of drawing one frame
     *
     * @param d - the drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * in charge of stopping the run of the animation.
     *
     * @return - true to continue false to stop
     */
    boolean shouldStop();
}
