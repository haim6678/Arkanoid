package collidables;

import biuoop.DrawSurface;
import game.GameLevel;
import listeners.HitNotifier;
import game.Surface;
import game.Velocity;
import listeners.HitListener;
import shapes.*;
import spriteimplemts.Ball;
import spriteimplemts.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * collidables.Block - a class which implements collidables.Collidable and spriteimplemts.Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //declaring the class fields
    private Rectangle block;
    private int hit;
    private Color color;
    private Surface surface;
    private Point coll;
    private List<HitListener> hitListeners;


    /**
     * the constructor - setting the members.
     *
     * @param upperLeft      - the block upperLeft point.
     * @param width          - the block width.
     * @param height         - the block height.
     * @param color          - the block's color.
     * @param hitInitializer - the block numbers of hit until it will be removed.
     * @param surface        - the block surface.
     */
    public Block(Point upperLeft, double width, double height, Color color, int hitInitializer, Surface surface) {
        Rectangle rect = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.block = rect;
        this.hit = hitInitializer;
        this.surface = surface;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * access method.
     * access to the hit listener field.
     *
     * @return - the hit listener
     */
    public List getHitListeners() { //TODO private ?? and other funcs?
        return this.hitListeners;
    }

    /**
     * access method.
     * access to the surface field method.
     *
     * @return - the surface
     */
    public Surface getSurface() {
        return this.surface;
    }

    /**
     * access method.
     * access to the coll field.
     *
     * @return - the collision point
     */
    public Point getColl() {
        return this.coll;
    }

    /**
     * set method.
     * add a hit listener field.
     */
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    /**
     * getting the hitter ball.
     * then notify to the block listeners about the hit.
     *
     * @param hitter - the hiiting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * removing a listener from the block listeners list.
     *
     * @param hl - the listener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }

    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * access method.
     *
     * @return - the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * access method.
     * access to the hit field method.
     *
     * @return - the number of hit's for the block.
     */
    public int getHit() {
        return this.hit;
    }

    /**
     * when the ball hit the block it will change directions.
     * the change will be according to the collision point.
     * this function is in charge of canghing the velocity.
     *
     * @param collisionPoint  - the ball's collision point with the block.
     * @param currentVelocity - the ball's speed.
     * @return the new speed.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) { //TODO where do i send hitter?

        // changing the number of hits left to the block.
        if (this.hit > 0) {
            this.hit--;
        }

        this.coll = collisionPoint;

        // creating the block line's
        Line topLine = new Line(this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getUpperRight());
        Line bottomLine = new Line(this.getCollisionRectangle().getBottomLeft(),
                this.getCollisionRectangle().getBottomRight());
        Line leftLine = new Line(this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getBottomLeft());
        Line rightLine = new Line(this.getCollisionRectangle().getUpperRight(),
                this.getCollisionRectangle().getBottomRight());

        Velocity newVel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        // checking if the collision is on the corner
        if (cornerCheck(collisionPoint, topLine.getStart())
                || cornerCheck(collisionPoint, topLine.getEnd())
                || cornerCheck(collisionPoint, bottomLine.getStart())
                || cornerCheck(collisionPoint, bottomLine.getEnd())) {
            newVel.setDy(-1 * currentVelocity.getDy());
            newVel.setDx(-1 * currentVelocity.getDx());
            return newVel;
        }

        //If ball is hitting from top
        if (rangeX(collisionPoint, topLine.getStart(), topLine.getEnd())) {
            newVel.setDy(-1 * currentVelocity.getDy());

            //If ball is hitting from bottom
        } else if ((rangeX(collisionPoint, bottomLine.getStart(), bottomLine.getEnd()))) {
            newVel.setDy(-1 * currentVelocity.getDy());
        }

        //if ball is hitting from left
        if (rangeY(collisionPoint, leftLine.getStart(), leftLine.getEnd())) {
            newVel.setDx(-1 * currentVelocity.getDx());

            //If ball is hitting from right
        } else if (rangeY(collisionPoint, rightLine.getStart(), rightLine.getEnd())) {
            newVel.setDx(-1 * currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return newVel;
    }

    /**
     * the spriteimplemts.Sprite interface implementation.
     * drawing the block to the screen.
     *
     * @param d - the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        /**d.setColor(Color.red);

         if (this.coll != null) {
         d.drawCircle((int) coll.getX(), (int) coll.getY(), 2);
         }**/
        // setting the color and drawing the block
        d.setColor(this.getColor());
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.black);

        if (this.getHit() > 0) { //TODO kike this or make new class for borders?
            d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
        }

    }

    /**
     * the spriteimplemts.Sprite interface implementation.
     * currently doing nothing as instructed.
     */
    public void timePassed() {

    }

    /**
     * adding the block to the game.- to the sprite,and collidabe lists.
     *
     * @param g - the game class(object)
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * access method.
     *
     * @return the block's color
     */
    public Color getColor() {
        return this.color;
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

    /**
     * checks if a number is in range of two other numbers.
     *
     * @param col   collision point
     * @param start first line limit
     * @param end   second line limit
     * @return boolean
     */
    public boolean rangeX(Point col, Point start, Point end) {
        if (col.getX() < Math.max(start.getX(), end.getX()) && (col.getX() > Math.min(start.getX(), end.getX()))) {
            return true;
        }
        return false;
    }

    /**
     * checks if a number is in range of two other numbers.
     *
     * @param col   collision point
     * @param start first line limit
     * @param end   second line limit
     * @return boolean
     */
    public boolean rangeY(Point col, Point start, Point end) {
        if (col.getY() < Math.max(start.getY(), end.getY()) && (col.getY() > Math.min(start.getY(), end.getY()))) {
            return true;
        }
        return false;
    }

    /**
     * checking if the ball is hiting the given corner.
     *
     * @param collision - the collision point.
     * @param corner    - the given corner.
     * @return - true if hiting, false otherwise.
     */
    public boolean cornerCheck(Point collision, Point corner) {
        if ((this.checkDouble(collision.getX(), corner.getX()) == 0)
                && (this.checkDouble(collision.getY(), corner.getY()) == 0)) {
            return true;
        }
        return false;
    }
}
