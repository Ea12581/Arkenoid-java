//208060855 Evyatar Altman
/**
 *  objects that implement it can send notifications when they are being hit.
 */
package Collidable;

/**
 * class to notify when the ball hit a block.
 */

public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener obj
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener obj
     */
    void removeHitListener(HitListener hl);

}
