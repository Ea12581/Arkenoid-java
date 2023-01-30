//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Collidable.HitListener;
import Sprites.Ball;

/**
 * Track's after the score of the game. 5 for one block.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int SCORE_FOR_HIT = 5;

    /**
     * constructor with provided Counter.
     * @param scoreCounter the Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * apdate the score after the hit.
     * @param beingHit the block who hava been hit
     * @param hitter the ball which hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
       currentScore.increase(SCORE_FOR_HIT);
    }
}
