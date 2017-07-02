package levels;

import collidables.Block;
import game.BackgroundCreator;
import game.Surface;
import game.Velocity;
import shapes.*;
import shapes.Point;
import spriteimplemts.LineSprite;
import spriteimplemts.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by haim on 18-May-16.
 */
public class GreenDay implements LevelInformation {

    private String levelName;
    private List<Color> colorList;
    private List<Block> blockInfo;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private int numOfBalls;
    private Color backColor;
    private List<Velocity> ballVelocities;

    public GreenDay() {
        this.levelName = "GreenDay";
        this.paddleSpeed = 5;
        this.paddleWidth = 100;
        this.numOfBalls = 2;
        this.numberOfBlocksToRemove = 0;
        this.backColor = Color.green;
        this.ballVelocities = this.initialBallVelocities();
        this.background = this.getBackground();
        this.blockInfo = this.styleBlock();
        this.colorList = new ArrayList<>();
        this.colorList.add(Color.magenta);
        this.colorList.add(Color.red);
    }

    public String levelName() {
        return this.levelName;
    }

    public List<Velocity> getBallVelocities() {
        return ballVelocities;
    }

    public Color getBackColor() {
        return this.backColor;
    }

    public int getNumOfBalls() {
        return numOfBalls;
    }

    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    public int paddleWidth() {
        return this.paddleWidth;
    }

    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> list = new ArrayList<>();
        List<Sprite> building = createBuilding();
        List<Sprite> tree = creatTree();
        List<Sprite> man = createMan();
        list.addAll(building);
        list.addAll(tree);
        list.addAll(man);

        BackgroundCreator back = new BackgroundCreator(list);
        return back;

    }

    private List<Sprite> creatTree() {
        List<Sprite> list = new ArrayList<>();
        shapes.Point upperLeft = new shapes.Point(700, 538);
        Block block = new Block(upperLeft, 20, 60, new Color(122, 76, 76), -1, null);
        list.add(block);
        shapes.Point center = new shapes.Point(710, 480);
        Circle circle = new Circle(58, center, new Color(122, 76, 76), false);
        list.add(circle);
        return list;
    }

    private List<Sprite> createMan() {
        List<Sprite> list = new ArrayList<>();
        Circle circle = new Circle(18, new shapes.Point(600, 460), Color.BLACK, false);
        list.add(circle);
        circle = new Circle(2, new shapes.Point(593, 458), Color.BLUE, true);
        list.add(circle);
        circle = new Circle(2, new shapes.Point(607,458 ), Color.BLUE, true);
        list.add(circle);
        circle = new Circle(3,new Point(600,469),Color.black,false);
        list.add(circle);
        Block block = new Block(new Point(595,460),10,10,Color.green,-1,null);
        list.add(block);
        List<Color> colors = new ArrayList<>();
        colors.add(Color.black);
        List<Line> lines = new ArrayList<>();
        Line line = new Line(597, 466, 603, 466);
        //lines.add(line);
        line = new Line(600, 480, 600, 570);
        lines.add(line);
        line = new Line(600, 505, 615, 535);
        lines.add(line);
        line = new Line(600, 505, 585, 535);
        lines.add(line);
        line = new Line(600, 570, 615, 600);
        lines.add(line);
        line = new Line(600, 570, 585, 600);
        lines.add(line);

        LineSprite lineSprite = new LineSprite(lines, colors);
        list.add(lineSprite);
        return list;


    }


    private List<Sprite> createBuilding() {

        List<Sprite> list = new ArrayList<>();
        shapes.Point upperLeft = new shapes.Point(0, 0);
        Block color = new Block(upperLeft, 800, 600, Color.green, -1, null);
        list.add(color);
        upperLeft = new shapes.Point(80, 450);
        Block backGround = new Block(upperLeft, 80, 150, Color.yellow, -1, null);
        list.add(backGround);

        for (int i = 450; i < 600; i += 25) {
            shapes.Point point = new shapes.Point(80, i);
            Block block = new Block(point, 80, 10, Color.blue, -1, null);
            list.add(block);
        }

        for (int i = 80; i <= 160; i += 20) {
            shapes.Point point = new shapes.Point(i, 450);
            Block block = new Block(point, 10, 150, Color.blue, -1, null);
            list.add(block);
        }
        upperLeft = new shapes.Point(115, 400);
        Block block = new Block(upperLeft, 20, 50, Color.blue, -1, null);
        list.add(block);

        upperLeft = new shapes.Point(122, 200);
        block = new Block(upperLeft, 5, 200, Color.blue, -1, null);
        list.add(block);

        shapes.Point center = new shapes.Point(124, 198);
        Circle circle = new Circle(20, center, Color.yellow, true);
        list.add(circle);
        circle = new Circle(10, center, Color.red, true);
        list.add(circle);
        Random rand = new Random();
        ChangeCircle changeCircle = new ChangeCircle(new Circle(6, center, Color.BLUE, true));
        list.add(changeCircle);
        return list;
    }

    public List<Block> styleBlock() {
        List<Block> list = new ArrayList<>();
        int width = 50;
        int hight = 20;
        int blockEnd = 300;
        Surface s = new Surface(0, 0, 600, 600, 60);
        for (int i = 150; i < 250; i += 20) {
            Color color = this.randomColor();
            for (int j = 720; j > blockEnd; j -= 50) {
                shapes.Point upperLeft = new shapes.Point(j, i);
                Block block = new Block(upperLeft, width, hight, color, 1, s);
                list.add(block);
                this.numberOfBlocksToRemove++;
            }

            blockEnd += 50;
        }


        return list;
    }

    private Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    @Override
    public List<Color> getColorList() {
        return colorList;
    }

    public String getLevelName() {
        return levelName;
    }

    public List<Velocity> initialBallVelocities() {

        List<Velocity> ballsVelocity = new ArrayList<>();
        int startSpeed = 5;
        for (int i = 0; i < this.getNumOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(35, startSpeed);
            ballsVelocity.add(v); //todo check if work with angel
            startSpeed += 1;
            if (startSpeed > 10) {
                startSpeed = 5;
            }
        }
        return ballsVelocity;
    }

    // its size, color and location.
    public List<Block> blocks() {
        return this.blockInfo;
    }

    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBalls() {
        return this.numOfBalls;
    }


}
