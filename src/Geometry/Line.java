package Geometry;//208060855 Evyatar Altman

import java.util.List;

/**
 * This Class represent a line with start and end Points. The Class support in geting the start,end and middle
 * poit of a line and also can tell if two lines have intersection.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     *constructor to build from two points and another from two coordination.
     * @param start point
     * @param end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     *constructor to build from two coordination.
     * @param x1 of the first coordination
     * @param y1 of the first
     * @param x2 of the second
     * @param y2 of the second
     */
    public Line(double x1, double y1, double x2, double y2) {
        //create new start point and new end point from the coordination
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
    }

    /**
     * This method cuculate the length of the line (which is the distance between
     * the start point and the end point).
     * @return double, the length of the line
     */

    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This method caculate the middle point of the line (which is the midlle point between the
     * start and the end points).
     * @return Point, the middle point of the line.
     */

    public Point middle() {
        //calculate the middle point with the formula ((x1 + x2)/2 , (y1 + y2)/2)
        Point middle = new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
        return middle;
    }

    /**
     * This method returns the start point.
     * @return Point, the start point of the line.
     */

    public Point start() {
        return this.start;
    }


    /**
     * This method returns the end point.
     * @return Point, the end point of the line.
     */

    public Point end() {
        return this.end;
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

    //finding the slope of the line by the formula y2-y1/x2-x1
    private double findingSlope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    //finding the y-intercept of the line (b)
    private double findingYIntercept() {
        //using the formula y = xa + b -> b = y - xa. x,y are any (x,y) on the line
        return this.start.getY() - this.start.getX() * this.findingSlope();
    }

    //finding y by given x
    private double findingYByX(double x) {
        //using the formula y = xa + b
        return x * this.findingSlope() + this.findingYIntercept();
    }

    //checking if x,y is in the range of the two lines
    private boolean isXYInRange(Line other, double xIntersect, double yIntersect) {
        //Taking the edges of the lines to determine if the intersect point is in those limits
        Point inter = new Point(xIntersect, yIntersect);
        return inter.isInRangeOf(this.start, this.end) && inter.isInRangeOf(other.start, other.end);
    }

    //checking if this line is in the range of the other line
    private boolean isInRangeOf(Line other) {
        //check if start point inside other line
        if (this.start.isInRangeOf(other.start, other.end)) {
            //if it's not equal to the edges of line, it inside
            if (!this.start.equals(other.start) && !this.start.equals(other.end)) {
                return true;
            }
            //check if this.end point is equal to one of the other
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return true;
            }
            //check if end point is in other line
            if (this.end.isInRangeOf(other.start, other.end)) {
                return true;
            }
            //check if the other line is inside this line and not at the edge or other line is a point
            if (other.start.isInRangeOf(this.start, this.end) && !this.start.equals(other.start)
                    || other.end.isInRangeOf(this.start, this.end) && !this.start.equals(other.end)
                    || this.start.equals(other.start) && this.start.equals(other.end)) {
                return true;
            }
            //they just touch
            return false;
            //check the end point
        } else if (this.end.isInRangeOf(other.start, other.end)) {
            //if it's inside and not on the edges
            if (!this.end.equals(other.start) && !this.end.equals(other.end)) {
                return true;
            }
            //check if the other line is inside this line and not at the edge or other line is a point
            if (other.start.isInRangeOf(this.start, this.end) && !this.end.equals(other.start)
                    || other.end.isInRangeOf(this.start, this.end) && !this.end.equals(other.end)
                    || this.end.equals(other.start) && this.end.equals(other.end)) {
                return true;
            }
            //they just touch
            return false;
        }
        return false;
    }

    private Point sharedStartOrEndPoint(Line other) {
        //if the lines have the same points, they are in each other
        if (this.start.equals(other.end) && this.end.equals(other.start)
                || this.start.equals(other.start) && this.end.equals(other.end)) {
            return null;
        }
        //edge case, if the start point of one line is the end of the other
        if (this.start.equals(other.end) || this.start.equals(other.start)) {
            return new Point(this.start.getX(), this.start.getY());
        }

        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;
    }

    /**
     * this method checks if this line is inside the other line.
     * @param other line
     * @return true if yes, false if not
     */
    public boolean isInside(Line other) {
        // edge case if they are both in the form of x = n
        if (isInRangeOfEpsilon(this.start.getX(), this.end.getX())
                && isInRangeOfEpsilon(other.start.getX(), other.end.getX())) {
            //if one is inside the other
                return this.isInRangeOf(other);
            }
        //check if they have the same slop and they in the range of each other
        double aThis = this.findingSlope();
        double aOther = other.findingSlope();
        return (isInRangeOfEpsilon(aThis, aOther) && this.isInRangeOf(other));

    }
    /**
     * This method Returns the intersection point if the lines intersect,
     * and null otherwise.
     * @param other line
     * @return Point, the intersection point of the line or null if there is'nt.
     */
    public Point intersectionWith(Line other) {
        double xIntersect = 0;
        double yIntersect = 0;

        //edge case, if one of the lines is in the form of x = n
        if (isInRangeOfEpsilon(this.start.getX(), this.end.getX())
                || isInRangeOfEpsilon(other.start.getX(), other.end.getX())) {
            //if the two in the form of x = n
            if (isInRangeOfEpsilon(this.start.getX(), this.end.getX())
                    && isInRangeOfEpsilon(other.start.getX(), other.end.getX())) {
                //if one is inside the other
                if (this.isInRangeOf(other)) {
                    return null;
                }
                //the last option, they have shared edges points
                return this.sharedStartOrEndPoint(other);
            }

            //if just one is in that form, the x would be his and the y of the other line
            if (isInRangeOfEpsilon(this.start.getX(), this.end.getX())) {
                xIntersect = this.start.getX();
                yIntersect = other.findingYByX(this.start.getX());
            } else {
                xIntersect = other.start.getX();
                yIntersect = this.findingYByX(other.start.getX());
            }
            //if the both are in form of y = xa + b
        } else {
         /* The solution of two linear functions y = xa1 + b1 and y = xa2 + b2 is
          xa1 + b1 = xa2 + b2 -> xa1 - xa2 = b2 - b1 -> x(a1 - a2) = b2 - b1 -> x = (b2 - b1)/(a1 - a2)
          y = (b2 - b1)/(a1 - a2) * a1 + b1
         */
            double a1 = this.findingSlope();
            double a2 = other.findingSlope();
            //if the slopes are equals (a1 == a2), they are parallel or on the same line.
            if (isInRangeOfEpsilon(a1, a2)) {
                if (this.isInRangeOf(other)) {
                    //if they inside each other
                    return null;
                }
                //the last option, they have shared edges points
                return this.sharedStartOrEndPoint(other);
            }
            double b1 = this.findingYIntercept();
            double b2 = other.findingYIntercept();
            xIntersect = (b2 - b1) / (a1 - a2);
            yIntersect = xIntersect * a1 + b1;
        }

        if (this.isXYInRange(other, xIntersect, yIntersect)) {
            return new Point(xIntersect, yIntersect);
        } else {
            return null;
        }
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other line
     * @return Point, the intersection point of the line or null if there is'nt.
     */
    public boolean isIntersecting(Line other) {
        //if the lines have the same points, they are in each other
        if (this.start.equals(other.end) && this.end.equals(other.start)
                || this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        //if there is intersection point
        if (this.intersectionWith(other) != null) {
            return true;
            //There is another option that one line is in the other
        } else if (this.isInRangeOf(other)) {
            //if both are in form of x = n and in the same range
            if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
                return true;
                //if they both on the same line
            } else {
                //check if they on the same line, that the slopes and the y-intercect are equals
                if (this.findingSlope() == other.findingSlope()
                        && this.findingYIntercept() == other.findingYIntercept()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other line
     * @return Point, the intersection point of the line or null if there is'nt.
     */
    public boolean equals(Line other) {
        //the lines are equals if they have the same slope and the same y intersect
        if (isInRangeOfEpsilon(this.findingSlope(), other.findingSlope())
                && isInRangeOfEpsilon(this.findingYIntercept(), other.findingYIntercept())) {
            return true;
        }
        return false;
    }

    // .

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect we want to ccheck if intersect
     * @return the most close inersect point to the start point or null if where is'nt
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        //if there is'nt intersect point return null
        if (intersectionPoints.size() == 0) {
            return null;
        }
        //calculate the distance from the first point
        double distance = this.start().distance(intersectionPoints.get(0));
        Point nearestPoint = intersectionPoints.get(0);
        //check which is the nearest point
        for (Point i : intersectionPoints) {
            if (this.start().distance(i) < distance) {
                nearestPoint = i;
                distance = this.start().distance(i);
            }
        }
        return nearestPoint;
    }


}
