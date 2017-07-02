package game;

import shapes.Point;

/**
 * the surface class.
 * contains an object surface.
 */
public class Surface {

    // declaring the fields
    private Point startBorder;
    private Point endBorder;
    private double borderWidth;

    /**
     * the constructor. setting the values. getting the x and y limits.
     *
     * @param xStart      - the start x point of border limit
     * @param yStart      - the start y point of border limit
     * @param xEnd        - the end x point of border limit
     * @param yEnd        - the end y point of border limit
     * @param borderWidth -the border's width.
     */
    public Surface(double xStart, double yStart, double xEnd, double yEnd, double borderWidth) {
        this.startBorder = new Point(xStart, yStart);
        this.endBorder = new Point(xEnd, yEnd);
        this.borderWidth = borderWidth;
    }

    /**
     * access method.
     * accessing the borderWidth field.
     *
     * @return - the BorderWidth.
     */
    public double getBorderWidth() {
        return this.borderWidth;
    }

    /**
     * access method to the start limit.
     *
     * @return - the start limit point
     */
    public Point getStartBorder() {

        return this.startBorder;
    }

    /**
     * access method to the end limit.
     *
     * @return - the end limit point
     */
    public Point getEndBorder() {
        return this.endBorder;
    }
}
