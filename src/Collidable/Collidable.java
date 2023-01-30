//208060855 Evyatar Altman
package Collidable;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;

/**
 * This interface supposes to give us information and how to react in a real
 * time collision.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return Rectangle obj
     */
    Rectangle getCollisionRectangle();



    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint Point
     * @param currentVelocity Velocity
     * @param hitter Ball
     * @return new Velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
