package spriteimplemts;

import biuoop.DrawSurface;
import collidables.CollisionInfo;
import collidables.GameEnvironment;
import game.GameLevel;
import game.Surface;
import game.Velocity;
import shapes.Line;
import shapes.Point;

/**
 * the ball class.
 * holds a ball with speed ,surface,size and color.
 * <p/>
 */

public class Ball implements Sprite {

    // declaring the fields
    private Point point;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private Surface surface;
    private GameEnvironment environment;


    /**
     * the constructor. setting the values. getting the ball components.
     *
     * @param center - the ball center point
     * @param radius - the ball's radius
     * @param color  - the ball's color
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        point = new Point(center.getX(), center.getY());
        this.size = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * the constructor. setting the values. getting the ball components .
     *
     * @param x     - the ball center x point
     * @param y     - the ball center y point
     * @param r     - the ball's radius
     * @param color - thr ball's color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {

        point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * access to the x field method.
     *
     * @return - the x point
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * access to the y field method.
     *
     * @return - the y point
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * access to the size field method.
     *
     * @return - the size value
     */
    public int getSize() {
        return this.size;
    }

    /**
     * access to the color field method.
     *
     * @return - the color value
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * access to the velocity field method.
     *
     * @return - the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * setting method.
     * setting the ball's environment.
     *
     * @param env - the given environment.
     */
    public void setEnvironment(GameEnvironment env) {
        this.environment = env;
    }

    /**
     * access method.
     * access to the environment field.
     *
     * @return - the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * adding the ball to the sprite list.
     *
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * setting the surface for the ball to move on.
     *
     * @param xStart - the start x point of border limit
     * @param yStart - the start y point of border limit
     * @param xEnd   - the end x point of border limit
     * @param yEnd   - the end y point of border limit
     */
    public void setSurface(double xStart, double yStart, double xEnd, double yEnd) {
        this.surface = new Surface(xStart, yStart, xEnd, yEnd, 4);
    }

    /**
     * setting the ball's velocity.
     *
     * @param v - a velocity object
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * setting the ball's velocity.
     *
     * @param dx - change in x-axis
     * @param dy - change in y-axis
     */
    public void setVelocity(double dx, double dy) {

        this.velocity = new Velocity(dx, dy);
    }

    /**
     * sprite implement.
     * moving the ball.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * calculating tha ball's trajectory.
     * his current location and his next location.
     *
     * @return - the ball's course.
     */
    public Line trajectory() {
        Point end = new Point(this.point.getX() + this.getVelocity().getDx(),
                this.point.getY() + this.getVelocity().getDy());

        Line trajectory = new Line(this.point, end);
        return trajectory;
    }

    /**
     * the function that move's the ball on the screen.
     * according to the ball's surface and velocity.
     */
    public void moveOneStep() {

        // getting the ball's trajectory and speed
        Line trajectory = this.trajectory();
        Velocity v = this.getVelocity();

        // checking if there is a collision in his trajectory
        CollisionInfo collision = this.getEnvironment().getClosestCollision(trajectory);

        // if there is then get a new speed
        if (collision != null) {

            if ((this.checkDouble(this.point.distanceTo(collision.getCollisionPoint()), this.getSize()) == 0)
                    || (this.checkDouble(this.point.distanceTo(collision.getCollisionPoint()), this.getSize()) == -1)) {

                v = collision.collisionObject().hit(this, collision.getCollisionPoint(), this.getVelocity());
                this.setVelocity(v.getDx(), v.getDy());
            }

            // if there is'nt then move according to his speed
        } else {

            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param canvas - where to draw on
     */
    public void drawOn(DrawSurface canvas) {

        canvas.setColor(this.color);
        canvas.fillCircle(this.getX(), this.getY(), this.getSize());

    }

    /**
     * checking if a given number is in range with 2 given limit's.
     *
     * @param toCheck - the number.
     * @param limit1  - the first limit.
     * @param limit2  -the second limit.
     * @return - boolean answer if in range.
     */
    public boolean inRange(double toCheck, double limit1, double limit2) {
        double checkMax = Math.max(limit1, limit2);
        double checkMin = Math.min(limit1, limit2);
        if ((this.checkDouble(toCheck, checkMax) == 0) || (this.checkDouble(toCheck, checkMax) == -1)
                && (this.checkDouble(toCheck, checkMin) == 0) || (this.checkDouble(toCheck, checkMin) == 1)) {
            return true;
        }
        return false;
    }

    public void removeFromGame(GameLevel g) {
        g.getsprites().getSpriteList().remove(this);
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
