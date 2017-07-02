package listeners;
import collidables.Block;
import game.Counter;
import game.GameLevel;
import listeners.HitListener;
import spriteimplemts.Ball;

/**
 * Created by haim on 09-May-16.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBlocks;

    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.removedBlocks = removedBlocks;
    }

    public Counter getRemovedBlocks() {
        return this.removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHit() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this); //TODO this??
            this.getRemovedBlocks().decrease(1);
        }
    }
}
