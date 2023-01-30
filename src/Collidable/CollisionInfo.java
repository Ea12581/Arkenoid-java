//208060855 Evyatar Altman
package Collidable;

import Geometry.Point;

/**
 * The class gives information about a taking place collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable obj;

    /**
     * constructor.
     * @param collisionPoint point
     * @param obj Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable obj) {
        this.collisionPoint = collisionPoint;
        this.obj = obj;
    }

    /**
     * getter to take the point at which the collision occurs.
     * @return collisionPoint
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return Collidable obj
     */
    public Collidable collisionObject() {
        return this.obj;
    };

    /**
     * setter to change the collisionPoint.
     * @param collisionPoint Point
     */
    public void setPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * setter to change the Collidable obj.
     * @param obj Collidable
     */
    public void setObj(Collidable obj) {
        this.obj = obj;
    }
}
