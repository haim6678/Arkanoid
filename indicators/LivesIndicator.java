package indicators;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import spriteimplemts.Sprite;

import java.awt.*;

/**
 * Created by haim on 09-May-16.
 */
public class LivesIndicator implements Sprite {
    private Counter live;

    /**
     * the constructor.
     * setting the fields.
     *
     * @param liveCounter - theLive tracking counter
     */
    public LivesIndicator(Counter liveCounter) {
        this.live = liveCounter;
    }

    /**
     * access method.
     *
     * @return - the line remaining value
     */
    private Counter getLive() {
        return live;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {

        for (int i = 0; i < this.getLive().getValue(); i++) {
            d.setColor(Color.BLACK);
            d.drawText(150, 15, "lives:", 15);
            d.setColor(Color.RED);
            d.drawText(151, 16, "lives:", 15);
            d.setColor(Color.black);
            d.fillCircle(200 + (15 * i), 10, 7);
            d.setColor(Color.red);
            d.fillCircle(200 + (15 * i), 10, 3);

        }

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * adding this class to a given game.
     *
     * @param gameLevel - the given game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getsprites().addSprite(this);
    }
}
