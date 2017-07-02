package levels;

import collidables.Block;
import game.BackgroundCreator;
import game.Surface;
import game.Velocity;
import shapes.*;
import spriteimplemts.MultipleLinesSprite;
import spriteimplemts.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {

    //TODO make setters
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInfoList;
    private int numberOfBlocksToRemove;
    private int numOfBalls;
    private Sprite background;
    private Color backColor = Color.BLACK;
    private List<Velocity> ballVelocities = new ArrayList<Velocity>();
    private List<Color> colorList;

    /**
     * the constructor.
     * setting the fields.
     */
    public DirectHit() {
        this.background = DirectHitBackground();
        this.blockInfoList = styleBlocks();
        this.levelName = "Direct Hit";
        this.numberOfBlocksToRemove = 1;
        this.numOfBalls = 1;
        this.ballVelocities = initialBallVelocities();
        this.paddleSpeed = 20;
        this.paddleWidth = 100;
        this.colorList = new ArrayList<>();
        colorList.add(Color.RED);
    }

    /**
     * access method.
     *
     * @return the backColor.
     */
    public Color getColor() {
        return this.backColor;
    }

    /**
     * access method.
     *
     * @return the color list.
     */
    public List getColorList() {
        return this.colorList;
    }

    /**
     * creating the block that it's going to be on the game.
     *
     * @return - the block list.
     */
    private List<Block> styleBlocks() {
        this.blockInfoList = new ArrayList<>();
        Surface s = new Surface(0, 0, 600, 600, 60);
        Block b = new Block(new shapes.Point(383, 183), 35, 35, Color.RED, 1, s);
        this.blockInfoList.add(b);
        return this.blockInfoList;
    }

    /**
     * creating list that old velocity for the game balls.
     *
     * @return - the velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(350, 5);
        ballVelocities.add(v);
        return this.ballVelocities;
    }

    /**
     * access method.
     *
     * @return - the number of ball that should be on the game.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * setting method.
     *
     * @param speed - setting the paddle speed.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * access method.
     *
     * @return - the paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * setting method.
     *
     * @param speed - setting the paddle width.
     */
    public void setPaddleWidth(int speed) {
        this.paddleWidth = speed;
    }

    /**
     * access method.
     *
     * @return - the paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * creating the graphics in the background.
     *
     * @return - the Sprite that is going to draw them
     */
    public Sprite DirectHitBackground() {
        //Creates a unique list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();
        //Creates background color
        Block b = new Block(new shapes.Point(0, 0), 800, 600, Color.BLACK, 1, null);
        listOfSprites.add(b);
        // adding sprites to be in background
        List<Sprite> target = MakeTargetImage();
        listOfSprites.addAll(target);
        //creating an 'official' full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    /**
     * adding the target sprites to be in background
     *
     * @return - list of sprite that assembles the target.
     */
    public List<Sprite> MakeTargetImage() {

        List<Sprite> returnList = new ArrayList<>();
        List<Circle> circle = new ArrayList<>();
        List c = new ArrayList<>();
        c.add(Color.RED);
        c.add(Color.RED);
        c.add(Color.RED);
        c.add(Color.RED);
        c.add(Color.RED);
        int radius = 40;
        //shapes.Circle center
        shapes.Point p = new shapes.Point(400, 200);
        //Creates target image
        for (int i = 0; i < 4; i++) {
            circle.add(new Circle(radius, p, Color.RED, false));
            radius += 20;
        }
        /* Target lines */
        Line line = new Line(365, 200, 100, 200);
        Line line3 = new Line(400, 235, 400, 520);
        Line line2 = new Line(400, 165, 400, 10);
        Line line1 = new Line(435, 200, 660, 200);
        List<Line> vSprite = new ArrayList();
        vSprite.add(line);
        vSprite.add(line1);
        vSprite.add(line2);
        vSprite.add(line3);
        MultipleLinesSprite linesSprite = new MultipleLinesSprite(vSprite, Color.RED);
        //Adds to sprite list
        returnList.addAll(circle);
        returnList.add(linesSprite);
        return returnList;

    }

    /**
     * access method.
     *
     * @return - the block list.
     */
    public List<Block> blocks() {
        return this.blockInfoList;
    }

    /**
     * access method.
     *
     * @return - the level's name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * access method.
     *
     * @return - the sprite that is drawing the background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * access method.
     *
     * @return - the numberOfBlocksToRemove before the level ends.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}