package game;

import java.awt.Color;
import java.util.Random;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import collidables.Block;
import collidables.Collidable;
import collidables.GameEnvironment;
import collidables.Paddle;
import indicators.LevelIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import levels.GreenDay;
import levels.LevelInformation;
import levels.SunnyDayLevel;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.PrintingHitListener;
import listeners.ScoreTrackingListener;
import shapes.Point;
import spriteimplemts.Ball;
import spriteimplemts.Sprite;
import spriteimplemts.SpriteCollection;

/**
 * Game class creates an entire game environment, including.
 * all necessary elements.
 */
public class GameLevel implements Animation {

    //Class Fields
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private Surface surface;
    private int borderWidth;
    private Paddle controller;
    private KeyboardSensor keyboard;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter liveCounter;
    private BlockRemover remover;
    private BallRemover ballRemover;
    private LevelInformation level;


    //private Color guiColor;


    private Counter score;

    /**
     * Constructor for Game class.
     */
    public GameLevel(LevelInformation level) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.remover = new BlockRemover(this, this.getBlockCounter()); //TODO here or in initialize?
        this.ballRemover = new BallRemover(this, this.getBallCounter());
        this.score = new Counter();
        this.liveCounter = new Counter();
        this.runner = new AnimationRunner();
        this.keyboard = runner.getGui().getKeyboardSensor(); //TODO like this?????
        // this.running = true;
    }


    public boolean shouldStop() {
        return !this.running;
    }

    public Counter getScore() {
        return score;
    }

    public Counter getLiveCounter() {
        return liveCounter;
    }


    public BlockRemover getRemover() {
        return remover;
    }

    public Counter getBallCounter() {
        return this.ballCounter;
    }

    public SpriteCollection getsprites() {
        return this.sprites;
    }

    public BallRemover getBallRemover() {
        return this.ballRemover;
    }


    /**
     * add collidables.Collidable method adds a given.
     * collidable object to the game environment.
     *
     * @param c - a sent collidable.
     */
    public void addCollidable(Collidable c) {

        this.environment.addCollidable(c);
    }

    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    private SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * addSprite adds a sprite to the list of sprites.
     * belonging to the Game.
     *
     * @param s a sent sprite to add.
     */
    public void addSprite(Sprite s) {

        this.sprites.addSprite(s);
    }

    /**
     * createBall creates a ball, setting its parameters.
     * environment, velocity, and then adding it to the current game.
     */
    public void createBall(int x, int y, int angle, int speed) {
        //Adds appropriate amount of balls for level

        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(x, y, 8, this.level.getColorList().get(i));
            ball.setSurface(0, 0, 800, 600);
            ball.setEnvironment(this.environment);
            List<Velocity> vList = this.level.initialBallVelocities();
            Velocity v = Velocity.fromAngleAndSpeed(vList.get(i).getDx(), vList.get(i).getDy()); //TODO check constructer
            ball.setVelocity(v);
            ball.addToGame(this);
            this.getBallCounter().increase(1);
        }
    }

    /**
     * returns the draw surface of the game.
     *
     * @return the game surface
     */
    public Surface getSurface() {
        return this.surface;
    }

    /**
     * returns the border width of the Game.
     *
     * @return borderWidth
     */
    public int getBorderWidth() {
        return this.borderWidth;
    }

    /**
     * this method initializes a new Game.
     * it creates all necessary elements, such as keyBoard sensor.
     * gui, and the game balls.
     */
    public void initialize() {
        PrintingHitListener print = new PrintingHitListener();
        this.surface = new Surface(0, 0, 800, 600, 30);
        this.borderWidth = 30;
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.getScore());
        this.liveCounter.increase(4); //todo 7 to all?
        /* creating elements for the paddle */
        //this.keyboardSensor = gui.getKeyboardSensor(); //TODO!!??!?!
        this.sleeper = new Sleeper();
        LivesIndicator livesIndicator = new LivesIndicator(this.getLiveCounter());
        this.setBorders();
        LevelIndicator levelIndicator = new LevelIndicator(this.level.levelName());
        levelIndicator.addToGame(this);
        /* spriteimplemts.Ball Elements */
        //this.createBallsOnTopOfPaddle();

        /* collidables.Block Elements */
        List<Block> blocks = this.level.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            Block tempBlock = blocks.get(i);
            Block block = new Block(tempBlock.getCollisionRectangle().getUpperLeft(),
                    tempBlock.getCollisionRectangle().getWidth(), tempBlock.getCollisionRectangle().getHeight(),
                    tempBlock.getColor(), tempBlock.getHit(), this.surface);
            //block.addHitListener(print);
            block.addHitListener(new ScoreTrackingListener(this.getScore())); //TODO it's okay?
            this.blockCounter.increase(1);
            block.addHitListener(this.getRemover());
            block.addToGame(this);
            scoreIndicator.addToGame(this);
            livesIndicator.addToGame(this);
        }
    }

    public void removeCollidable(Collidable c) {
        this.getEnvironment().getListOfCollidables().remove(c);
    }

    public void removeSprite(Sprite s) {
        this.getsprites().getSpriteList().remove(s);
    }

    /**
     * sets the borders of the outer blocks surrounding the screen.
     */
    public void setBorders() {
        int width = this.getBorderWidth();

        Point upperLeft = new Point(0, 0);
        Block block = new Block(upperLeft, this.getSurface().getEndBorder().getX(), 20, Color.WHITE, 0, this.surface);
        block.addToGame(this);
        //left collidables.Block
        upperLeft = new Point(0, (width * 2) - 10);
        block = new Block(upperLeft, width, this.getSurface().getEndBorder().getY(),
                Color.gray, 0, this.surface);
        block.addToGame(this);

        //right block
        upperLeft = new Point(this.getSurface().getEndBorder().getX() - width, (width * 2) - 10);
        Block block2 = new Block(upperLeft, width, this.getSurface().getEndBorder().getY(),
                Color.gray, 0, this.surface);
        block2.addToGame(this);

        //upper block
        upperLeft = new Point(0, 20);
        block = new Block(upperLeft, this.getSurface().getEndBorder().getX(), width,
                Color.gray, 0, this.surface);
        block.addToGame(this);

        //Death collidables.Block
        Point p = new Point(width, this.getSurface().getEndBorder().getY() + width);
        Block deathBlock = new Block(p, this.getSurface().getEndBorder().getX() + 2 * width + 10, width,
                Color.WHITE, 0, this.surface);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(this.getBallRemover());

    }

    /**
     * chooses a random color for the blocks.
     *
     * @return a random color to use.
     */
    public Color chooseColor() {
        Random rand = new Random();
        return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    private Paddle getController() {
        return this.controller;
    }

    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.controller.drawOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if ((this.getBlockCounter().getValue() == 0) || (this.getBallCounter().getValue() == 0)) {
            if (this.getBlockCounter().getValue() == 0) {
                this.getScore().increase(100); //TODO this means level?
            }
            this.running = false;
        }
    }

    private void createBallsOnTopOfPaddle() {
        Point c = new Point(360, this.getSurface().getEndBorder().getY() - this.getBorderWidth() / 2 - 10);
        Block paddle = new Block(c, this.level.paddleWidth(), 20, Color.YELLOW, 0, this.surface);
        Paddle p = new Paddle(this.keyboard, paddle, this.level.paddleSpeed());//todo fixx
        this.controller = p;
        controller.addToGame(this);
        this.createBall(360 + this.level.paddleWidth() / 2,
                (int) (this.getSurface().getEndBorder().getY() - this.getBorderWidth() / 2 - 20), 135, 6);
    }

    /**
     * Runs the actual game.
     */
    public void playOneTurn() {
        this.running = true;
        this.runner.setFramesPerSecond(600);
        this.runner.run(this);
    }

    public void run() {
        while ((this.getBlockCounter().getValue() > 0) && (this.getLiveCounter().getValue() > 0)) {
            this.createBallsOnTopOfPaddle();
            this.runner.setFramesPerSecond(12);
            this.runner.run(new CountdownAnimation(1, 3, this.getsprites()));
            this.playOneTurn();
            this.controller.removeFromGame(this);
            this.getLiveCounter().decrease(1);

        }

    }

    /**
     * main function, to run the entire game.
     *
     * @param args unused user input.
     */
    public static void main(String[] args) {
        LevelInformation newLevel = new GreenDay();
        GameLevel game = new GameLevel(newLevel);
        game.addSprite(newLevel.getBackground());
        game.initialize();
        game.run();
        game.runner.getGui().close(); //TODO here or in run method? fixxx
    }
}
