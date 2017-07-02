package collidables;

import game.Velocity;
import shapes.Point;
import shapes.Rectangle;
import spriteimplemts.Ball;

/**
 * Interface of collidables.Collidable.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return - his rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies an object that we collided with it at collisionPoint with a given velocity.
     * The return is a new velocity expected after the hit, based on the force
     * the an object inflicted on it
     *
     * @param collisionPoint  - the point
     * @param currentVelocity - the given velocity
     * @return - the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}