package spriteimplemts;

import biuoop.DrawSurface;
import shapes.Line;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by haim on 18-May-16.
 */
public class MultipleLinesSprite implements Sprite {

    private List<Line> lines;
    private Color color;
    private List<Color> colors = null;

    public MultipleLinesSprite(List lines, Color color) {
        this.lines = lines;
        this.color = color;
        this.colors = null;

    }

    public MultipleLinesSprite(List lines, List<Color> colors) {
        this.lines = lines;
        this.colors = colors;
        this.color = null;
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public List<Color> getColors(){
        return this.colors;
    }
    public void drawOn(DrawSurface canvas) {
        Random random = new Random();
        if (this.colors != null) {
            int color = random.nextInt(this.colors.size());
            for (int i = 0; i < lines.size(); i++) {
                int x = random.nextInt(600);
                int y = random.nextInt(600);
                canvas.setColor(this.colors.get(color));
                Line tempLine = lines.get(i);
                canvas.drawLine((int) tempLine.getStart().getX(),
                        (int) tempLine.getStart().getY(), x, y);
            }
        } else {
            for (int i = 0; i < lines.size(); i++) {
                canvas.setColor(this.color);
                Line tempLine = lines.get(i);
                canvas.drawLine((int) tempLine.getStart().getX(),
                        (int) tempLine.getStart().getY(), (int) tempLine.getEnd().getX(), (int) tempLine.getEnd().getY());
            }
        }
    }

    public void timePassed() {

    }
}
