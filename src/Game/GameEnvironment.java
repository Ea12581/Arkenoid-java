//208060855 Evyatar Altman
package Game;

import Collidable.Collidable;
import Collidable.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;

/**
 * This class is the collection of all the objects that can be colladible with
 * and checks if a collision is taking place.
 */
public class GameEnvironment {
    //distance suppose to be positive;
    private static final int NO_DISTANCE = -1;
    private ArrayList<Collidable> arrayColl;

    /**
     * constructor that create arry list in the game environment.
     */
   public GameEnvironment() {
       this.arrayColl = new ArrayList<>();
   }

    /**
     * add the given collidable to the environment.
     * @param c Collidable obj
     */
    public void addCollidable(Collidable c) {
        arrayColl.add(c);
    }

    /**
     * Remove the given collidable fromethe environment.
     * @param c Collidable obj
     */
    public void removeCollidable(Collidable c) {
        arrayColl.remove(c);
    }

    /**
     *  Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory line
     * @return cInfo CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //search if there is any collision point
        CollisionInfo cInfo = null;
        Point collisionPoint;
        double minDistance = NO_DISTANCE;
        double tempD;
        //create copy so that changes won't disturb the current iteration
        ArrayList<Collidable> arrayColl = new ArrayList<Collidable>(this.arrayColl);
        //loop to go though all the collidable objects
        for (Collidable i : arrayColl) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle());
            //if there is intersect point
            if (collisionPoint != null) {
                //calculate the distance
                tempD = trajectory.start().distance(collisionPoint);
                //if this is the first collision point
                if (minDistance == NO_DISTANCE) {
                    minDistance = tempD;
                    cInfo = new CollisionInfo(collisionPoint, i);
                    //if that point is closer
                } else if (tempD < minDistance) {
                    minDistance = tempD;
                    cInfo.setPoint(collisionPoint);
                    cInfo.setObj(i);
                }
            }
        }
        return cInfo;
    }

}
