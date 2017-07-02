package spriteimplemts;

import biuoop.DrawSurface;

/**
 * the sprite interface.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d - the DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
