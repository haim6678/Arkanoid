package listeners;

import collidables.Block;
import game.Counter;
import listeners.HitListener;
import spriteimplemts.Ball;

/**
 * Created by haim on 09-May-16.
 */
public class LivesTrackingListener implements HitListener {

    private Counter currentLives;

    public LivesTrackingListener(Counter liveCounter) {
        this.currentLives= liveCounter;
    }

    public Counter getCurrentScore() {
        return currentLives;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        //TODO remove !!

    }
}
