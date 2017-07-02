package indicators;

import biuoop.DrawSurface;
import game.GameLevel;
import spriteimplemts.Sprite;

import java.awt.*;

/**
 * Created by haim on 19-May-16.
 */
public class LevelIndicator implements Sprite {
    private String name;

    /**
     * the constructor.
     * setting the fields.
     *
     * @param s - the level name
     */
    public LevelIndicator(String s) {
        this.name = s;
    }

    /**
     * access method.
     *
     * @return - the level name
     */
    public String getName() {
        return name;
    }

    /**
     * drawing the level's name to the screen.
     *
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(500, 15, "LevelName:" + this.getName(), 15);
        d.setColor(Color.black);
        d.drawText(501, 15, "LevelName:" + this.getName(), 15);

    }

    /**
     * implement of Sprite
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
