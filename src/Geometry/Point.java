//208060855 Evyatar Altman
package Geometry;

/**
 * This Class represent a line with start and end Points. The Class support in culculate the distanse from
 * other point and if they are equal.
 */

public class Point {
    //coordinations of the point
    private double x;
    private double y;

    /**
     * constructor.
     * @param x x coordination
     * @param y y coordination
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculate the distance from other point and return the distance.
     * @param other point
     * @return double, the distance.
     */

    public double distance(Point other) {
        //calculate distance between points with the formula: root of ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * method to fix the mistakes that can take place in working with comparing double type until
     * 10^-10.
     * @param x double
     * @param y double
     * @return double, the distance.
     */
    public boolean isInRangeOfEpsilon(double x, double y) {
        double epsilon = 0.000000000000001;
        return x >= y - epsilon && x <= y + epsilon;
    }

    /**
     * This method check if this point and another point are equals.
     * Return true if yes, false if not.
     * @param other point
     * @return boolean
     */

    public boolean equals(Point other) {
        //the points are equal if the both x and y are equal
        return isInRangeOfEpsilon(this.x, other.x) && isInRangeOfEpsilon(this.y, other.y);
    }

    /**
     * check if the point in the range of a,b.
     * @param a point
     * @param b point
     * @return boolean
     */
    public boolean isInRangeOf(Point a, Point b) {
        //take the borders between the points
        double bigX = Math.max(a.getX(), b.getX());
        double lowX = Math.min(a.getX(), b.getX());
        double bigY = Math.max(a.getY(), b.getY());
        double lowY = Math.min(a.getY(), b.getY());
        //check if the point inside
        if (lowX <= this.getX() && this.getX() <= bigX && lowY <= this.getY() && this.getY() <= bigY) {
            return true;
        }
        return false;
    }

    /**
     * This method return the x value of this point.
     * @return double
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method change the x value of the point.
     * @param  newX double
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * This method return the y value of this point.
     * @return double
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method change the y value of the point.
     * @param  newY double
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
