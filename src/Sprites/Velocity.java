//208060855 Evyatar Altman
package Sprites;

import Geometry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * // constructor with dx and dy.
     * @param dx double, the change of left and right
     * @param dy double, the change of up and down
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor with angle and speed.
     * @param angle of the velocity
     * @param speed how fast
     * @return velocity obj
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //take the direction for dx with sin and get raised by speed. the same about dy.
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * get dx.
     * @return dx double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get dy.
     * @return dy double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p point, there the obj is
     * @return p point there the obj had moved
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * calculate the speed vector with the formula sqrt(x^2 + y^2).
     * @return double the speed value
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
