//208060855 Evyatar Altman
package Sprites;

/**
 * Ball represent 2D ball (actually, a circle). Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a given DrawSurface.
 */
import Collidable.CollisionInfo;
import Game.GameLevel;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * all the members of the ball. include radius, center point,color, velocity and his limits (borders)
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    //default v is zero
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment gameEnvi;

    //check if the r is in the frame of the screen
   /* private int rLegal() {
        //deference of the lowest borders divided by 2.

    }*/
    //check if the center is not out of the borders of the screen
    private Point centerLegal() {
        int r = this.r;
        Point center = this.center;
        double fixedX = this.center.getX();
        double fixedY = this.center.getY();

        return new Point(fixedX, fixedY);
    }

    /**
     * constructor with point.
     * @param center point
     * @param r radius
     * @param color of the ball
     * @param gameEnvi Game.Game.GameEnvironment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvi) {
        this.r = r;
        //after we have r, we can check him
        //this.r = this.rLegal();
        this.center = center;
        //after we have center, we can check it
        //this.center = this.centerLegal();
        this.color = color;
        this.gameEnvi = gameEnvi;
    }

    /**
     * constrctor with x and y.
     * @param x of the center
     * @param y of the center
     * @param r radius
     * @param color of the ball
     * @param gameEnvi Game.Game.GameEnvironment
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnvi) {
        this.center = new Point(x, y);
        //after we have center, we can check it
        //this.center = this.centerLegal();
        this.r = r;
        //after we have r, we can check him
        //this.r = this.rLegal();
        this.color = color;
        this.gameEnvi = gameEnvi;
    }

    /**
     * constructor with point and without game environment.
     * @param center point
     * @param r radius
     * @param color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.r = r;
        //after we have r, we can check him
        //this.r = this.rLegal();
        this.center = center;
        //after we have center, we can check it
        //this.center = this.centerLegal();
        this.color = color;
    }
    /**
     * access to the x of the center.
     * @return int, the x of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * access to the y of the center.
     * @return int the y of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get the center of the ball.
     * @return Point center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * set a given gameEnvironment.
     * @param gameEnvironment new Game.Game.GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvi = gameEnvironment;
    }

    /**
     * access to the r of the ball.
     * @return int, the r
     */
    //get the radius of the ball
    public int getSize() {
        return r;
    }

    /**
     * access to the color of the ball.
     * @return Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface which we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        //draw the ball
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        //draw his circle
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * setter to give the ball velocity.
     * @param v the velocity we want to give the ball.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * setter to give the ball velocity with dx and dy.
     * @param dx double, change in x
     * @param dy double, change in y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getter to get the ball's velocity.
     * @return v, velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }


    /**
     * check if the ball would hit any object in the line between his center and the given point.
     * return new velocity to change the ball direction, otherwise return null.
     * @param direction Point
     * @return v Velocity
     */
    private Velocity isGetHit(Point direction) {
        double r = this.r;
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = direction.getX();
        double dy = direction.getY();
        Velocity newV = null;
        CollisionInfo c = null;
        //create 3 lines, for vertical direction (up and down)  ,for horizontal direction (right and left),
        //and for the slats
        //slats
        Line trajectorySlats = new Line(center, new Point(x + dx, y + dy));
        //up/down
        Line trajectoryUpDown = new Line(center, new Point(x, y + dy));
        //right/left
        Line trajectoryRightLeft = new Line(center, new Point(x + dx, y));
        //check if he hits something from above/below
        c = this.gameEnvi.getClosestCollision(trajectoryUpDown);
        //if so
        if (c != null) {
            //check if the hit was from above
            if (c.collisionPoint().getY() < this.center.getY()) {
                this.center = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() + r);
                //it was from below
            } else {
                this.center = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() - r);
            }
            newV = c.collisionObject().hit(c.collisionPoint(), this.v, this);
        } else {
            //check if he hits something from left\right
            c = this.gameEnvi.getClosestCollision(trajectoryRightLeft);
            if (c != null) {
                //check if it hits something from left
                if (c.collisionPoint().getX() < center.getX()) {
                    this.center = new Point(c.collisionPoint().getX() + r, c.collisionPoint().getY());
                } else {
                    this.center = new Point(c.collisionPoint().getX() - r, c.collisionPoint().getY());
                }
                newV = c.collisionObject().hit(c.collisionPoint(), this.v, this);
            } else {
                //check if it hits one of the corners
                c = this.gameEnvi.getClosestCollision(trajectorySlats);
                if (c != null) {
                    //check if it hits the uppLeft corner
                    if (c.collisionPoint().getX() >= center.getX() && c.collisionPoint().getY() >= center.getY()) {
                        this.center = new Point(c.collisionPoint().getX() - r, c.collisionPoint().getY() - r);
                        //the upRight
                    } else if (c.collisionPoint().getX() <= center.getX()
                            && c.collisionPoint().getY() >= center.getY()) {
                        this.center = new Point(c.collisionPoint().getX() + r, c.collisionPoint().getY() - r);
                        //the downLeft
                    } else if (c.collisionPoint().getX() >= center.getX()
                            && c.collisionPoint().getY() <= center.getY()) {
                        this.center = new Point(c.collisionPoint().getX() - r, c.collisionPoint().getY() + r);
                        //downRight
                    } else {
                        this.center = new Point(c.collisionPoint().getX() + r, c.collisionPoint().getY() + r);
                    }
                    newV = c.collisionObject().hit(c.collisionPoint(), this.v, this);
                }
                }
        }
        return newV;
    }
    /**
     * give the ball "speed" by changing his center point with his velocity.
     */
    public void moveOneStep() {
        Point direction = new Point(this.v.getDx(), this.v.getDy());
        //check if the ball hit something
        Velocity newV = this.isGetHit(direction);
        if (newV != null) {
            this.v = newV;
        //keep the regular v
        } else {
                        this.center = this.getVelocity().applyToPoint(this.center);
                    }
                }

    /**
     * notify the ball that time has passed, and it needs to move.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * method to add the ball by himself to the game.
     * @param g Game.Game obj
     */
    public void addToGame(GameLevel g) {
        //add as a sprite
        g.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     * @param g game obj
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}



