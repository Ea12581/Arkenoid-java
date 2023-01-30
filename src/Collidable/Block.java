//208060855 Evyatar Altman
package Collidable;

import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * this class creates the blocks that the ball would hit in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners;

    protected void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * constructor with a given rectangle.
     * @param rect Rectangle
     */
    public Block(Rectangle rect) {
        this.block = rect;
    }

    /**
     * constructor with a given rectangle and color.
     * @param rect Rectangle
     * @param color Color
     */
    public Block(Rectangle rect, Color color) {
        this.block = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getter to get the block.
     * @return Rectangle block
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * get the color.
     * @return color of the Block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * chamge the x value of the uppLeft point of the block.
     * @param newX
     */
    public void setX(double newX) {
        this.block.setX(newX);
    }

    /**
     * chamge the y value of the uppLeft point of the block.
     * @param newY
     */
    public void setY(double newY) {
        this.block.setY(newY);
    }

    /**
     * method to get the block which going to get hit.
     * @return Rectangle block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.getBlock();
    }

    /**
     * method to change the velocity of the hitting ball after the hit.
     * @param collisionPoint Point
     * @param currentVelocity Velocity
     * @return new velocity for the hitting ball
     */

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        //notify about the hit
        notifyHit(hitter);
        //take the current dx and dy
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //build the four point of the rectangle
        double x = this.block.getUpperLeft().getX();
        double y = this.block.getUpperLeft().getY();
        Point uppRight = new Point(x + this.block.getWidth(), y);
        Point downLeft = new Point(x, y + this.block.getHeight());
        Point downRight = new Point(x + this.block.getWidth(), y + this.block.getHeight());
        //change the velocity according to the position of the coll point:
        //if it hit the corner (one of the point) we change the whole direction
        if (collisionPoint.equals(this.block.getUpperLeft()) || collisionPoint.equals(uppRight)
        || collisionPoint.equals(downLeft) || collisionPoint.equals(downRight)) {
            return new Velocity(-dx, -dy);
        }
        //if it's hit in the up or down line, change only dy
        //we check if the coll point is between the upper points or the down points
        if (collisionPoint.isInRangeOf(this.block.getUpperLeft(), uppRight)
                || collisionPoint.isInRangeOf(downLeft, downRight)) {
            return new Velocity(dx, -dy);
        }
        // The only remain option is that the coll point is in the left\right lines. change only dx
        return new Velocity(-dx, dy);
    }

    /**
     * draw the block on a given surface.
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        //convert every double to int
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int hight = (int) this.block.getHeight();
        surface.fillRectangle(x, y, width, hight);
        //draw his frame
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, hight);
    }

    /**
     * notify the block that time has passed.
     */
    public void timePassed() {
    }

    /**
     * method to add the ball by himself to the game.
     * @param g Game.Game obj
     */
    public void addToGame(GameLevel g) {
        //add as collidable object
        g.addCollidable(this);
        //add as a sprite
        g.addSprite(this);
    }

    /**
     * Remove the block from the game.
     * @param g game obj
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
