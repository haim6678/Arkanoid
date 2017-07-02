package levels;

import collidables.Block;
import game.BackgroundCreator;
import game.Surface;
import game.Velocity;
import shapes.*;
import spriteimplemts.MultipleCircleSprite;
import spriteimplemts.MultipleLinesSprite;
import spriteimplemts.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by haim on 18-May-16.
 */
public class SunnyDayLevel implements LevelInformation {

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

    public SunnyDayLevel() {
        this.levelName = "Some Sunny Day";
        this.numberOfBlocksToRemove = 36;
        this.numOfBalls = 4;
        this.ballVelocities = this.initialBallVelocities();
        this.background = this.getBackground();
        this.backColor = Color.WHITE;
        this.background = SunnyDayBackground();
        this.blockInfoList = this.styleBlocks();
        this.paddleSpeed = 20;
        this.paddleWidth = 100;
        this.colorList = new ArrayList<>();
        this.colorList.add(Color.RED);
        this.colorList.add(Color.BLUE);
        this.colorList.add(Color.ORANGE);
        this.colorList.add(Color.MAGENTA);
    }

    public List getColorList() {
        return this.colorList;
    }
    public List<Velocity> initialBallVelocities() {
        //double vel = 5;
       /* for (int i = 0; i < 3; i++) {
            game.Velocity tempV = new game.Velocity(vel, vel += 5);
            this.ballVelocities.add(tempV);
            vel += 5;
        }
        for (int i = 0; i < 3; i++) {
            game.Velocity tempV = new game.Velocity(vel, vel -= 5);
            this.ballVelocities.add(tempV);
            vel -= 5;
        }*/
        double dx = 200;
        double dy = 3;
        for( int i = 0; i < this.numOfBalls; i++) {
            Velocity v = new Velocity(dx, dy);
            ballVelocities.add(v);
            dx += 5;
            dy += 1;
        }
        return this.ballVelocities;
    }

    public List<Block> styleBlocks() {
        //List of colors for rainbow
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.blue);
        colors.add(Color.blue);
        colors.add(Color.magenta);
        colors.add(Color.magenta);
        this.blockInfoList = new ArrayList<Block>();
        Surface s = new Surface(0, 0, 600, 600, 60);
        double x = 50;
        double y = 400;
        int blocks = this.numberOfBlocksToRemove / 2;
        int counter;
        for (counter = 0; counter < blocks / 3; counter++) {
            for(int i = 0; i < 5; i++) {
                shapes.Point corner = new shapes.Point(x, y);
                Block block = new Block(corner, 75, 30, colors.get(counter), 1, s);
                this.blockInfoList.add(block);
                x += 10;
                y -= 5;
            }
        }
        //decreasing section of rainbow
        for (int i = counter; i < (blocks - blocks / 3); i++) {
            for(int k = 0; k < 5; k++) {
                x += 10;
                y += 5;
                shapes.Point corner = new shapes.Point(x, y);
                Block block = new Block(corner, 75, 30, colors.get(i), 1, s);
                this.blockInfoList.add(block);
            }
        }
        return this.blockInfoList;
    }


    public Sprite SunnyDayBackground() {

        //Creates a unique list of sprites for this level
        List<Sprite> listOfSprites = new ArrayList<Sprite>();

        /* adding sprites to be in background */
        Sprite sun = createSun();
        Block grass = new Block(new shapes.Point(0,400),800, 200, Color.green,0,null);
        List<Sprite> cloud = CreateCloud();
        Sprite rays = rays();

        listOfSprites.add(grass);
        listOfSprites.addAll(cloud);
        listOfSprites.add(rays);
        listOfSprites.add(sun);

        //creating an 'official' full background image
        this.background = new BackgroundCreator(listOfSprites);
        return this.background;
    }

    public Sprite createSun() {
        MultipleCircleSprite sun = new MultipleCircleSprite();
        List radius = new ArrayList();
        radius.add(80);
        radius.add(60);
        radius.add(40);
        radius.add(20);
        List centers = new ArrayList<>();
        shapes.Point p = new shapes.Point(200, 150);
        centers.add(p);
        centers.add(p);
        centers.add(p);
        centers.add(p);
        List<Color> c = new ArrayList<>();
        c.add(Color.YELLOW);
        c.add(Color.ORANGE);
        c.add(Color.ORANGE);
        c.add(Color.YELLOW);
        sun.setVariousDetails(radius, centers, c, false);

        return sun;
    }

    public List<Sprite> CreateCloud() {
        List<Sprite> cloud = new ArrayList<>();
        MultipleCircleSprite circles = new MultipleCircleSprite();
        List<shapes.Point> centers = new ArrayList<>();
        centers.add(new shapes.Point(70, 300));
        centers.add(new shapes.Point(80, 320));
        centers.add(new shapes.Point(90, 320));
        centers.add(new shapes.Point(100, 350));
        centers.add(new shapes.Point(120, 340));
        centers.add(new shapes.Point(550, 280));
        centers.add(new shapes.Point(570, 290));
        centers.add(new shapes.Point(590, 270));
        centers.add(new shapes.Point(500, 280));
        centers.add(new shapes.Point(480, 290));
        List radius = new ArrayList();
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        radius.add(45);
        List<Color> c = new ArrayList<>();
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        c.add(Color.lightGray);
        circles.setVariousDetails(radius, centers, c, true);
        cloud.add(circles);
        return cloud;
    }
    public Sprite rays() {

        Random rand = new Random();
        List<Line> lines = new ArrayList<>();
        double x = 500;
        double y = 400;
        for (int i = 0; i < 100; i++) {
            Line line = new Line(new shapes.Point(200, 150), new shapes.Point(x, y));
            x = rand.nextInt();
            y = rand.nextInt();
            lines.add(line);
        }
        for (int i = 0; i < 40; i++) {
            Line line = new Line(new shapes.Point(200, 150), new shapes.Point(x, y));
            x -= 20;
            y -= 20;
            lines.add(line);
        }
        List<Color> colors = new ArrayList<>();
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        MultipleLinesSprite rays = new MultipleLinesSprite(lines, colors);
        //spriteimplemts.MultipleLinesSprite rays2 = new spriteimplemts.MultipleLinesSprite(lines, Color.ORANGE);
        return rays;
    }

    public int numberOfBalls() {
        return this.numOfBalls;
    }

    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    public void setPaddleWidth(int speed) {
        this.paddleWidth = speed;
    }

    public int paddleWidth() {
        return this.paddleWidth;
    }

    public Color getColor() {
        return this.backColor;
    }

    public String levelName() {
        return this.levelName;
    }

    public Sprite getBackground() {
        return this.background;
    }

    public List<Block> blocks() {
        return this.blockInfoList;
    }

    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
