package collidables;

import shapes.Point;

/**
 * collidables.CollisionInfo - a class to contain.
 * collision info about a object that something.
 * collided into.
 */
public class CollisionInfo {

    // declaring the fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * the constructor- setting the members.
     *
     * @param point  - the collision point
     * @param object - the collision object
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collisionPoint = point;
        this.collisionObject = object;
    }

    /**
     * access method.
     * access to the collisionPoint field method.
     *
     * @return - the collisionPoint
     */

    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * access method.
     * access to the collisionObject field method.
     *
     * @return - the collisionObject
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
