package listeners;

import collidables.Block;
import game.Counter;
import listeners.HitListener;
import spriteimplemts.Ball;

/**
 * Created by haim on 09-May-16.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public Counter getCurrentScore() {
        return currentScore;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        //TODO ADD 100, AND LEVEL
        if (beingHit.getHit() == 0) {
            this.getCurrentScore().increase(10);
        }
        this.getCurrentScore().increase(5);
    }
}
