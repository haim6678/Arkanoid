package indicators;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import spriteimplemts.Sprite;

import java.awt.Color;

/**
 * Created by haim on 09-May-16.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * the constructor.
     *
     * @param count - the score counter
     */
    public ScoreIndicator(Counter count) {
        this.scoreCounter = count;
    }

    /**
     * access method.
     *
     * @return - the counter.
     */
    private Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * draw the score to the screen.
     *
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(360, 15, "Score:" + Integer.toString(this.getScoreCounter().getValue()), 15);
        d.setColor(Color.red);
        d.drawText(361, 16, "Score:" + Integer.toString(this.getScoreCounter().getValue()), 15);
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
        gameLevel.addSprite(this);
    }
}
