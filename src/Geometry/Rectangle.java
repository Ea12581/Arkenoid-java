//208060855 Evyatar Altman
package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent rectangle by point,height and wight. The rectangle can also find
 * List of intersection points with the specified line (type Line).
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft point
     * @param width double
     * @param height double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the line we check if intersect
     * @return list of the (possibly empty)intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        //build the four point of the rectangle, assuming that the lines are in the
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        Point uppRight = new Point(x + this.width, y);
        Point downLeft = new Point(x, y + this.height);
        Point downRight = new Point(x + this.width, y + this.height);
        //build the four lines, up,down,right and left.
        Line up = new Line(this.upperLeft, uppRight);
        Line down = new Line(downLeft, downRight);
        Line right = new Line(uppRight, downRight);
        Line left = new Line(this.upperLeft, downLeft);
        //create arry of the lines
        Line[] arrOfRecLines = {up, down, right, left};
        //create list of points
        List<Point> intersectionPoints = new ArrayList<>();
        //flag to know if there is intersection point
        //boolean nullFlag = true;
        /*check if the line intersect with any of the lines of the rectangle.
         if yes, ad this intersection point to the list */
        for (Line i : arrOfRecLines) {
            //check if the line is inside i
            /*if (line.isInside(i)) {
                return null;
            }*/
            Point intersect = line.intersectionWith(i);
            if (intersect != null) {
                intersectionPoints.add(intersect);
            }
        }
        /*if (!nullFlag) {
        }*/
        return intersectionPoints;
    }

    /**
     * Return the width of the rectangle.
     * @return double width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return double height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Point upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * chamge the x value of the uppLeft point.
     * @param newX
     */
    public void setX(double newX) {
        this.upperLeft.setX(newX);
    }

    /**
     * chamge the y value of the uppLeft point.
     * @param newY
     */
    public void setY(double newY) {
        this.upperLeft.setY(newY);
    }
}
