package collidables;

import shapes.Line;
import shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * collidables.GameEnvironment - a class to be able to access.
 * all elements included in the game, such as collidable objects, with ability.
 * to check for closest collisions.
 */
public class GameEnvironment {

    //declaring the class fields
    private List listOfCollidables;

    /**
     * the constructor - setting the members.
     */
    public GameEnvironment() {
        this.listOfCollidables = new ArrayList();
    }

    /**
     * setting method.
     * adding to the listOfCollidables.
     *
     * @param c - the collidable to add
     */
    public void addCollidable(Collidable c) {
        listOfCollidables.add(c);
    }

    public List getListOfCollidables(){
        return this.listOfCollidables;
    }

    /**
     * Assume an object (ball) moving from line.start() to line.end().
     * If this object will not collide with any of the collidables.
     * in this collection, return null. Else, return the information.
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the given object trejectory
     * @return - the collision info (if there is) null otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // there aren't any collidables to collide with
        if (this.listOfCollidables.isEmpty()) {
            return null;
        }

        /*
         * Begins with setting the closest point to be the first one
         * from the listOfCollidables which it will collide with
         */
        Collidable temp;
        Point closest;
        int j = 0;

        //getting the first collision in the list
        do {
            temp = (Collidable) listOfCollidables.get(j);
            closest = trajectory.closestIntersectionToStartOfLine(temp.getCollisionRectangle());
            j++;
        } while ((closest == null) && (j < listOfCollidables.size()));

        // no collision found
        if ((closest == null)) {
            return null;
        }

        // finding the next collision in list (if there is any other)
        Collidable object = (Collidable) listOfCollidables.get(j - 1);
        for (int i = j; i < this.listOfCollidables.size(); i++) {

            //Makes a temp point of next collision point
            temp = (Collidable) listOfCollidables.get(i);
            Point tempPoint = trajectory.closestIntersectionToStartOfLine(temp.getCollisionRectangle());

            // compares which is closer to the trajectory start point

            if ((tempPoint != null) && (this.checkDouble(trajectory.getStart().distanceTo(closest)
                    , trajectory.getStart().distanceTo(tempPoint)) == 1)) {

                closest = tempPoint;
                object = temp;
            }
        }

        // return the closest collisionInfo from the list
        return new CollisionInfo(closest, object);
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
