package spriteimplemts;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * spriteimplemts.SpriteCollection - a class which preserves
 * all the sprites in the current game.
 */
public class SpriteCollection {

    //Member
    private List spriteList;

    /**
     * spriteimplemts.SpriteCollection initializes a new array List
     * to hold the sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList();
    }

    /**
     * adds a spriteimplemts.Sprite to the list of Sprites.
     *
     * @param s sprite to add to list
     */
    public void addSprite(Sprite s) {

        this.spriteList.add(s);
    }


    /**
     * notifies all sprites in array that time
     * has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite call = (Sprite) this.spriteList.get(i);
            call.timePassed();
        }
    }


    /**
     * draws on all sprites to drawSurface, by
     * calling each sprite and their respective drawOn function.
     *
     * @param canvas drawSurface
     */
    public void drawAllOn(DrawSurface canvas) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite call = (Sprite) this.spriteList.get(i);
            call.drawOn(canvas);
        }
    }

    /**
     * returns list of sprites.
     *
     * @return spriteList the list of sprites
     */
    public List getSpriteList() {

        return this.spriteList;
    }

}