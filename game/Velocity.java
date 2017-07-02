package game;

import shapes.Point;

/**
 * the velocity class.
 * holds a velocity of an object.
 * <p/>
 */
public class Velocity {

    // declaring the fields
    private double dx;
    private double dy;

    /**
     * the constructor setting the object velocity.
     *
     * @param dx - the x progress
     * @param dy - the y progress
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * access method to the x field.
     *
     * @return - the x progress field
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * access method to the y field.
     *
     * @return - the y progress field
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * seting method.
     * deting the y distance.
     *
     * @param yDistance -the given distance.
     */
    public void setDy(double yDistance) {
        this.dy = yDistance;
    }

    /**
     * seting method.
     * deting the y distance.
     *
     * @param xDistance -the given distance.
     */
    public void setDx(double xDistance) {
        this.dx = xDistance;
    }

    /**
     * getting x and y progress in terms of angel and speed.
     * converting it to progress in x axis and y.
     *
     * @param angle - the moving angle
     * @param speed - the moving speed
     * @return - a new velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // converting it
        double dy = speed * Math.sin(Math.toRadians(270 + angle));
        double dx = speed * Math.cos(Math.toRadians(270 + angle));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p - the current ball location point
     * @return - the new point after the moving
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }
}
