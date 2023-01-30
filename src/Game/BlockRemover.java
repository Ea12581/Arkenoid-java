//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Collidable.HitListener;
import Sprites.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor with given Game and Counter.
     * @param game
     * @param removedBlocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove from the game the block that got hit.
     * @param beingHit the block who hava been hit
     * @param hitter the ball which hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
