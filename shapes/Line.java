package shapes;

import java.util.List;

/**
 * shapes.Line - a class which can perform operations on lines such
 * as finding slope, distance, and intersection points.
 */
public class Line {

    // declaring the fields
    private Point start;
    private Point end;
    private double slope;
    private double cuttingWhitY;
    private boolean slopeCheck;

    /**
     * the constructor. setting the values. getting 2 point objects.
     *
     * @param start - the start point of the line
     * @param end   - the end point of the line
     */
    public Line(Point start, Point end) {

        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        this.setSlop();
        this.cuttingWhitY = this.setCuttingWhitY();
    }

    /**
     * the constructor. setting the values.getting the start and end Coordinates.
     *
     * @param x1 - the start x Coordinate.
     * @param y1 - the start y Coordinate.
     * @param x2 - the end x Coordinate.
     * @param y2 - the end y Coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.setSlop();
        this.cuttingWhitY = this.setCuttingWhitY();
    }

    /**
     * checking the point that the line is cutting the y axis, needed for the equation.
     *
     * @return - the cut point with the y axis.
     */
    public double setCuttingWhitY() {

        //  calculating  the cutting point according to the formula
        return this.start.getY() - this.getSlope() * this.start.getX();
    }

    /**
     * access to the line cutting point field method.
     *
     * @return - the cutting point
     */
    public double getCuttingWhitY() {

        return this.cuttingWhitY;
    }

    /**
     * checking if the line has a slope or it's vertical.
     *
     * @return - boolean,false if vertical,true otherwise
     */
    public boolean checkSlope() {

        // checking if it's vertical
        return (this.start.getX() - this.end.getX() == 0);
    }

    /**
     * access to the line slope field method.
     *
     * @return - the slope
     */
    public double getSlope() {

        return this.slope;

    }

    /**
     * calculating the line slope by the formula.
     * needed for the equation.
     */
    public void setSlop() {

        // calculating the slope by the formula
        double yDistance = this.start.getY() - this.end.getY();
        double xDistance = this.start.getX() - this.end.getX();
        if (!this.checkSlope()) {
            this.slope = (yDistance / xDistance);
            this.slopeCheck = true;
        } else {
            this.slopeCheck = false;
        }
    }

    /**
     * calculating the line length using the point distance method.
     *
     * @return - the length
     */
    public double length() {

        return this.start.distanceTo(end);
    }

    /**
     * calculating the middle of the line point.
     *
     * @return - the middle point
     */
    public Point middle() {

        //calculating the x and y points by the formula
        double xMiddle = (this.start.getX() + this.end.getX()) / 2;
        double yMiddle = (this.start.getY() + this.end.getY()) / 2;

        // creating a new point object
        return new Point(xMiddle, yMiddle);
    }

    /**
     * access to the line start point field method.
     *
     * @return - the start point
     */
    public Point getStart() {

        return this.start;
    }

    /**
     * access to the line end point field method.
     *
     * @return - the end point
     */
    public Point getEnd() {

        return this.end;
    }

    /**
     * If this line does not intersect with the given rectangle, return null.
     * Otherwise, return the closest intersection point  of the rectangle.
     * to the start of the line.
     *
     * @param rect - the shapes.Rectangle to check withe
     * @return - null if there is'nt,collision point othewise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line ballTrajectory = new Line(this.start, this.end);

        // getting the collision list
        List intersections = rect.intersectionPoints(ballTrajectory);
        Point closestIntersection;

        //there is no collisions
        if (intersections.isEmpty()) {
            return null;
            //there is a collision
        } else {
            //calling a function to check who is closer
            closestIntersection = checkClosest(ballTrajectory, intersections);
        }

        //return the closest collision point.
        return closestIntersection;
    }

    /**
     * checking with a given line and intersection list.
     * who is the closest point to the line start.
     *
     * @param ballTrajectory - the line
     * @param intersections  - the intersection list
     * @return - the closest point
     */
    public Point checkClosest(Line ballTrajectory, List intersections) {

        Point closest = (Point) intersections.get(0);
        for (int i = 1; i < intersections.size(); i++) {
            Point secondPoint = (Point) intersections.get(i);

            //checking with the point method distanceTo wich one will give a smaller value

            if (this.checkDouble(ballTrajectory.getStart().distanceTo(secondPoint),
                    ballTrajectory.getStart().distanceTo(closest)) == -1) {
                closest = secondPoint;
            }
        }

        // the point
        return closest;
    }

    /**
     * checking if to lines are intersecting.
     *
     * @param other - the point to compare to
     * @return if there is an intersection.
     */
    public boolean isIntersecting(Line other) {

        // checking if both are vertical,or if they are parallel so they will never intersect
        if (((this.checkDouble(other.getSlope(), this.getSlope()) == 0)
                && ((!other.checkSlope()) && (!this.checkSlope())))
                || (other.checkSlope() && this.checkSlope())
                || ((this.getStart().getY() - this.getEnd().getY() == 0)
                && (other.getStart().getY() - other.getEnd().getY() == 0))) {
            return false;

            //checking if the intersect point is on both lines
        } else if (this.intersectionWith(other) == null) {
            return false;
        }

        // they are intersecting
        return true;
    }

    /**
     * finding the intersecting point and return it(return null if there is'nt).
     *
     * @param other - the other point to compare to.
     * @return - the intersecting point.
     */
    public Point intersectionWith(Line other) {

        // initialing the intersection variables
        double xIntersection = 0;
        double yIntersection = 0;

        // both line have Slope and maybe cross so check for the point
        if ((!this.checkSlope()) && (!other.checkSlope())) {

            // the x point intersection according to the line equation
            xIntersection = (other.getCuttingWhitY() - this.getCuttingWhitY())
                    / (this.getSlope() - other.getSlope());

            // the y point intersection according to the line equation
            yIntersection = (other.getSlope() * xIntersection) + other.getCuttingWhitY();

            // checking that the point is actually on both line
            if (!(checkIfPointExist(xIntersection, yIntersection))
                    || (!other.checkIfPointExist(xIntersection, yIntersection))) {
                return null;
            }

            //one line is vertical vertical - then you can set the x in the equation
        } else if (other.checkSlope() || this.checkSlope()) {

            // the second line is vertical
            if (other.checkSlope()) {
                yIntersection = (this.getSlope() * other.start.getX()) + this.getCuttingWhitY();
                xIntersection = other.start.getX();
                if (!(checkIfPointExist(xIntersection, yIntersection))
                        || (!other.checkIfPointExist(xIntersection, yIntersection))) {
                    return null;
                }

                // the first line is vertical
            } else {
                yIntersection = (other.getSlope() * this.start.getX()) + other.getCuttingWhitY();
                xIntersection = this.start.getX();
                if (!(checkIfPointExist(xIntersection, yIntersection))
                        || (!other.checkIfPointExist(xIntersection, yIntersection))) {
                    return null;
                }
            }
        }

        // initializing and returning the intersection point and return it
        return new Point(xIntersection, yIntersection);
    }

    /**
     * checking if a point is on a given line.
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     * @return - true if the point is on the line false otherwise
     */
    public boolean checkIfPointExist(double x, double y) {

        //creating the intersection point
        Point checkPoint = new Point(x, y);
        // checking that the point is in the line range (if it's actually on the line)
        double d1 = this.start.distanceTo(checkPoint);
        double d2 = this.end.distanceTo(checkPoint);
        double combineDistance = d1 + d2;
        if (this.checkDouble(combineDistance, this.length()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * checking if a given point is in the range of 2 limits.
     *
     * @param toCheck     the point to check.
     * @param firstLimit  - the first range limit
     * @param secondLimit - the second range limit
     * @return boolean if the number is in range
     */
    public boolean inRange(double toCheck, double firstLimit, double secondLimit) {
        double checkMax = Math.max(firstLimit, secondLimit);
        double checkMin = Math.min(firstLimit, secondLimit);
        if ((this.checkDouble(toCheck, checkMax) == 0) || (this.checkDouble(toCheck, checkMax) == -1)
                && (this.checkDouble(toCheck, checkMin) == 0) || (this.checkDouble(toCheck, checkMin) == 1)) {
            return true;
        }
        return false;
    }

    /**
     * checking the ratio between 2 double numbers .
     * return 0 if equals (with 0.001 difference).
     * return 1 if the first is bigger the thw second.
     * return -1 if the second is bigger.
     *
     * @param first  - the first number.
     * @param second - the second.
     * @return - the ratio
     */
    public int checkDouble(double first, double second) {
        if (Math.abs(first - second) < 0.001) {
            return 0;
        } else if (first > second) {
            return 1;
        }
        return -1;
    }
}
