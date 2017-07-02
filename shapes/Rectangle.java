package shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * shapes.Rectangle - a shape class.
 */
public class Rectangle {

    //Declaring class fields
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Point middle;


    /**
     * Constructer creates a new rectangle with specified dimensions.
     *
     * @param upperLeft - point of upper left corner
     * @param width     - rectangle width
     * @param height    - rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.setMiddle();
    }

    /**
     * Return a (possibly empty) List of intersection points with a specified line.
     *
     * @param line - a specified line
     * @return a list of intersections
     */

    public List intersectionPoints(Line line) {
        List intersectionList = new ArrayList();
        checkRight(line, intersectionList);
        checkLeft(line, intersectionList);
        checkBottom(line, intersectionList);
        checkUpper(line, intersectionList);
        return intersectionList;

    }

    /**
     * checks a specified line with the right side of the rectangle
     * to see if the lines intersect. if yes, adds to the list.
     *
     * @param line             the specified line
     * @param intersectionList list of intersections
     */
    public void checkRight(Line line, List intersectionList) {
        Line rightLine = new Line(this.upperRight, this.bottomRight);
        if (line.isIntersecting(rightLine)) {
            Point temp = line.intersectionWith(rightLine);
            intersectionList.add(temp);

        }
    }

    /**
     * checks a specified line with the left side of the rectangle
     * to see if the lines intersect. if yes, adds to the list.
     *
     * @param line             the specified line
     * @param intersectionList list of intersections
     */
    public void checkLeft(Line line, List intersectionList) {
        Line leftLine = new Line(this.upperLeft, this.bottomLeft);
        if (line.isIntersecting(leftLine)) {
            Point temp = line.intersectionWith(leftLine);
            intersectionList.add(temp);
        }
    }

    /**
     * checks a specified line with the bottom side of the rectangle
     * to see if the lines intersect. if yes, adds to the list.
     *
     * @param line             the specified line
     * @param intersectionList list of intersections
     */
    public void checkBottom(Line line, List intersectionList) {
        Line bottomLine = new Line(this.bottomLeft, this.bottomRight);
        if (line.isIntersecting(bottomLine)) {
            Point temp = line.intersectionWith(bottomLine);
            intersectionList.add(temp);
        }
    }

    /**
     * checks a specified line with the top side of the rectangle
     * to see if the lines intersect. if yes, adds to the list.
     *
     * @param line             the specified line
     * @param intersectionList list of intersections
     */
    public void checkUpper(Line line, List intersectionList) {
        Line upperLine = new Line(this.upperLeft, this.upperRight);
        if (line.isIntersecting(upperLine)) {
            Point temp = line.intersectionWith(upperLine);
            intersectionList.add(temp);
        }
    }

    /**
     * returns the upper right corner of rectangle.
     *
     * @return point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * returns the bottom left corner.
     *
     * @return point
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * returns bottom right point.
     *
     * @return point
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    /**
     * returns the width of the rectangle.
     *
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the rectangle height.
     *
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * sets middle point of a rectangle.
     */
    public void setMiddle() {
        double middleX = (this.upperLeft.getX() + this.upperRight.getX()) / 2;
        double middleY = (this.upperRight.getY() + this.bottomRight.getY()) / 2;
        this.middle = new Point(middleX, middleY);
    }

    /**
     * returns the middle of the rectangle.
     *
     * @return point
     */
    public Point getMiddle() {
        return this.middle;
    }
}
