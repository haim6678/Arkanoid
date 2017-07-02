package listeners;

import collidables.Block;
import spriteimplemts.Ball;

/**
 * Created by haim on 09-May-16.
 */
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A collidables.Block with x =  " + beingHit.getColl().getX() + ","
                + " And with y = " + beingHit.getColl().getY() + ","  + " was hit.");

    }
}
