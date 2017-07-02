package listeners;

import collidables.Block;
import spriteimplemts.Ball;

/**
 * Created by haim on 08-May-16.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the spriteimplemts.Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}
