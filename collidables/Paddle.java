package collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import game.GameLevel;
import game.Surface;
import game.Velocity;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import spriteimplemts.Ball;
import spriteimplemts.Sprite;

import java.awt.Color;

/**
 * collidables.Paddle - class which implements spriteimplemts.Sprite and collidables.Collidable -
 * allows users to control a game piece during the game.
 */
public class Paddle implements Sprite, Collidable {

    //declaring the class fields
    private biuoop.KeyboardSensor keyboard;
    private Block paddleBlock;

    private Point region0;
    private Point region1;
    private Point region2;
    private Point region3;
    private Point region4;
    private Point region5;

    private Line firstRegion;
    private Line secondRegion;
    private Line thirdRegion;
    private Line fourthRegion;
    private Line fifthRegion;
    private int moveStep;
    private double width;
    private double height;

    /**
     * the constractur - setting the memmbers.
     *
     * @param k - the KeyboardSensor.
     * @param b - the paddle's block
     */
    public Paddle(KeyboardSensor k, Block b, int speed) { //todo add speed !!!!!!
        this.keyboard = k;
        this.paddleBlock = b;
        this.setRegions(); //Initializes paddle
        this.setRegionLines();
        this.moveStep = 5;
        this.setWidth();
        this.setHeight();
    }

    /**
     * setting method.
     * in charge of setting the paddle's width.
     */
    public void setWidth() {
        this.width = this.getPaddleBlock().getCollisionRectangle().getWidth();
    }

    /**
     * setting method.
     * in charge of setting the paddle's height.
     */
    public void setHeight() {
        this.height = this.getPaddleBlock().getCollisionRectangle().getHeight();
    }

    /**
     * access mothod.
     * access to the width field method.
     *
     * @return - the paddle width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * access mothod.
     * access to the width field method.
     *
     * @return - the paddle width.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * access method.
     * access to the MoveStep field method.
     *
     * @return - how much the paddle moves in one step.
     */
    public int getMoveStep() {
        return this.moveStep;
    }

    /**
     * if the player presses the left key.
     * this function is in charge of moving the paddle to the left.
     */
    public void moveLeft() {

        // getting the surface and how much to move in one step the paddle
        int stepToMove = this.getMoveStep();
        Surface surface = this.getPaddleBlock().getSurface();

        //if there is still place to move the paddle
        //without hiting the borders - then move it

        if (this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX() - stepToMove,
                surface.getStartBorder().getX() + this.getPaddleBlock().getSurface().getBorderWidth()) == 1) {

            Point newUpperLeft = new Point(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX()
                    - stepToMove, this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getY());

            this.paddleBlock = new Block(newUpperLeft, this.getWidth(), this.getHeight(), Color.YELLOW, 0, surface);

            // there is no more place to move left then keep it near the left border
        } else if ((this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX(),
                surface.getStartBorder().getX() + this.getPaddleBlock().getSurface().getBorderWidth()) == 0)
                || (this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX(),
                surface.getStartBorder().getX() + this.getPaddleBlock().getSurface().getBorderWidth()) == 1)) {

            Point newUpperLeft = new Point(surface.getStartBorder().getX()
                    + this.getPaddleBlock().getSurface().getBorderWidth(),
                    this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getY());

            this.paddleBlock = new Block(newUpperLeft, this.getWidth(), this.getHeight(), Color.YELLOW, 0, surface);
        }

        //resets regions after moving paddle
        this.setRegions();
        this.setRegionLines();
    }

    /**
     * if the player presses the right key.
     * this function is in charge of moving the paddle to the right.
     */
    public void moveRight() {

        // getting the surface and how much to move in one step the paddle
        int stepToMove = this.getMoveStep();
        Surface surface = this.getPaddleBlock().getSurface();

        //if there is still place to move the paddle
        //without hiting the borders - then move it
        if (this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperRight().getX() + stepToMove
                , surface.getEndBorder().getX() - this.getPaddleBlock().getSurface().getBorderWidth()) == -1) {
            Point newUpperLeft = new Point(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX()
                    + stepToMove, this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getY());

            this.paddleBlock = new Block(newUpperLeft, this.getWidth(), this.getHeight(), Color.YELLOW, 0, surface);

            // there is no more place to move left then keep it near the left border
        } else if ((this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX()
                        + this.getWidth() + this.moveStep,
                surface.getEndBorder().getX() - this.getPaddleBlock().getSurface().getBorderWidth()) == 0)
                || ((this.checkDouble(this.getPaddleBlock().getCollisionRectangle().getUpperLeft().getX()
                        + this.getWidth() + this.getMoveStep(),
                surface.getEndBorder().getX() - this.getPaddleBlock().getSurface().getBorderWidth()) == 1))) {

            Point newUpperLeft = new Point(surface.getEndBorder().getX() - this.getWidth()
                    - this.getPaddleBlock().getSurface().getBorderWidth(),
                    this.getPaddleBlock().getCollisionRectangle().getUpperRight().getY());

            this.paddleBlock = new Block(newUpperLeft, this.getWidth(), this.getHeight(), Color.YELLOW, 0, surface);

        }


        //resets regions after moving paddle
        this.setRegions();
        this.setRegionLines();
    }

    /**
     * setting method.
     * setting the paddles regions points.
     */
    public void setRegions() {

        double regionWidth = (this.getCollisionRectangle().getWidth() / 5);
        this.region0 = new Point(this.paddleBlock.getCollisionRectangle().getUpperLeft().getX(),
                this.paddleBlock.getCollisionRectangle().getUpperLeft().getY());
        this.region1 = new Point(this.paddleBlock.getCollisionRectangle().getUpperLeft().getX()
                + regionWidth, this.paddleBlock.getCollisionRectangle().getUpperLeft().getY());
        this.region2 = new Point(this.region1.getX() + regionWidth,
                this.paddleBlock.getCollisionRectangle().getUpperLeft().getY());
        this.region3 = new Point(this.region2.getX() + regionWidth,
                this.paddleBlock.getCollisionRectangle().getUpperLeft().getY());
        this.region4 = new Point(this.region3.getX() + regionWidth,
                this.paddleBlock.getCollisionRectangle().getUpperLeft().getY());
        this.region5 = new Point(this.paddleBlock.getCollisionRectangle().getUpperRight().getX(),
                this.paddleBlock.getCollisionRectangle().getUpperRight().getY());
    }

    /**
     * setting method.
     * setting the paddles regions lines.
     */
    public void setRegionLines() {
        this.firstRegion = new Line(region0, region1);
        this.secondRegion = new Line(region1, region2);
        this.thirdRegion = new Line(region2, region3);
        this.fourthRegion = new Line(region3, region4);
        this.fifthRegion = new Line(region4, region5);
    }

    /**
     * the spriteimplemts.Sprite interface implementetion.
     * Checks which keys are pressed.
     * moves accordingly.
     */
    public void timePassed() {

        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * the spriteimplemts.Sprite interface implementetion.
     * drawing the paddle.
     *
     * @param d - the drawSurfsce
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        Rectangle rect = this.getPaddleBlock().getCollisionRectangle();
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

    }

    /**
     * access mothod.
     * getting the rectangle.
     *
     * @return getCollisionRectangle - the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }

    /**
     * access mothod.
     * getting the block.
     *
     * @return paddleBlock - the paddleBlock.
     */
    public Block getPaddleBlock() {
        return this.paddleBlock;
    }

    /**
     * when the ball hit the paddle it will change directions.
     * the change will be according to the collision point and the region.
     * this function is in charge of canghing the velocity.
     *
     * @param collisionPoint  - the ball's collision point with the paddle
     * @param currentVelocity - the ball's speed.
     * @return - the new speed
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) { //todo add notify!

        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
        //Region One -- 300 degrees (-60)
        if (rangeX(collisionPoint, region0, region1)) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
            //Region Two -- 330 degrees (a little to the left)
        } else if (rangeX(collisionPoint, region1, region2)) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
            //region three
        } else if (rangeX(collisionPoint, region2, region3)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            //Region Four -- 30 degrees, and for region 5 in 60 degrees.
        } else if (rangeX(collisionPoint, region3, region4)) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
            //Region Five
        } else if (rangeX(collisionPoint, region4, region5)) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
        }
        //TODO REMOVE THIS ??
        //If the ball hits the side of the paddle/corners
        else if (collisionPoint.getX() - region5.getX() < .3 || collisionPoint.getY() == region5.getY()) {
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if (collisionPoint.getX() - region0.getX() < .3 || collisionPoint.getY() == region0.getY()) {
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        //the new speed
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g -the game to add to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * checking if a given point is in the range of 2 limits.
     *
     * @param toCheck the point to check.
     * @param limit1  - the first rangr limit
     * @param limit2  - the second range limit
     * @return - true if in rang,false otherwise.
     */
    public boolean inRange(double toCheck, double limit1, double limit2) {
        double checkMax = Math.max(limit1, limit2);
        double checkMin = Math.min(limit1, limit2);
        if ((this.checkDouble(toCheck, checkMax) == 0) || (this.checkDouble(toCheck, checkMax) == -1)
                && (this.checkDouble(toCheck, checkMin) == 0)
                || (this.checkDouble(toCheck, checkMin) == 1)) {
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
        if (Math.abs(first - second) < 0.01) {
            return 0;
        } else if (first > second) {
            return 1;
        }
        return -1;
    }

    /**
     * checking if a given point is in rang of a line.
     *
     * @param col   -the collision point
     * @param start - the line start.
     * @param end   - the line end.
     * @return - true if in rang false otherwise
     */
    public boolean rangeX(Point col, Point start, Point end) {
        double max = Math.max(start.getX(), end.getX());
        double min = Math.min(start.getX(), end.getX());
        if ((this.checkDouble(col.getX(), max) == -1) || (this.checkDouble(col.getX(), max) == 0)
                && ((this.checkDouble(col.getX(), min) == 1) || (this.checkDouble(col.getX(), min) == 0))) {
            return true;
        }
        return false;
    }
}
