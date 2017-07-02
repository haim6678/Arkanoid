package listeners;

import collidables.Block;
import game.Counter;
import game.GameLevel;
import listeners.HitListener;
import spriteimplemts.Ball;

/**
 * Created by haim on 09-May-16.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter removedBall;

    public BallRemover(GameLevel gameLevel, Counter removedBall) {
        this.gameLevel = gameLevel;
        this.removedBall = removedBall;
    }

    public Counter getRemovedBall() {
        return this.removedBall;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    public void hitEvent(Block beingHit, Ball hitter){
        hitter.removeFromGame(gameLevel);
        this.getRemovedBall().decrease(1);
        //beingHit.removeHitListener();// TODO what to do?
    }
}
