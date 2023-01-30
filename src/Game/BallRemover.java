//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Collidable.HitListener;
import Sprites.Ball;

/**
 * Remove balls that hit a death region.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;

    /**
     * constructor with given Game and Counter.
     * @param game
     * @param remainingBall
     */
    public BallRemover(GameLevel game, Counter remainingBall) {
        this.game = game;
        this.remainingBall = remainingBall;
    }

    /**
     * remove from the game the block that got hit.
     * @param beingHit the block who hava been hit
     * @param hitter the ball which hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBall.decrease(1);
    }

}

