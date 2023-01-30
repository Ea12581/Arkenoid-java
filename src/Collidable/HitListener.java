//208060855 Evyatar Altman
/**
 * notify the listeners that have been hit
 */
package Collidable;

import Sprites.Ball;

/**
 * class to know to respond when the ball hit a block.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block who hava been hit
     * @param hitter the ball which hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}
