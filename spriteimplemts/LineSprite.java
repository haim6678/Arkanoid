package spriteimplemts;

import biuoop.DrawSurface;
import shapes.Line;
import spriteimplemts.MultipleLinesSprite;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by haim on 18-May-16.
 */
public class LineSprite extends MultipleLinesSprite implements Sprite {

    public LineSprite(List lines, List<Color> colors) {
        super(lines, colors);
    }

    public List<Line> getLines() {
        return super.getLines();
    }

    public void drawOn(DrawSurface canvas) {
        Random random = new Random();
        //if (super.getColors() != null) {
        int color = random.nextInt(super.getColors().size());
        for (int i = 0; i < super.getLines().size(); i++) {
            canvas.setColor(super.getColors().get(color));
            Line tempLine = this.getLines().get(i);
            canvas.drawLine((int) tempLine.getStart().getX(),
                    (int) tempLine.getStart().getY(), (int) tempLine.getEnd().getX(), (int) tempLine.getEnd().getY());
        }
        /**} else {
         for (int i = 0; i < lines.size(); i++) {
         canvas.setColor(this.color);
         shapes.Line tempLine = lines.get(i);
         canvas.drawLine((int) tempLine.getStart().getX(),
         (int) tempLine.getStart().getY(), (int) tempLine.getEnd().getX(), (int) tempLine.getEnd().getY());
         }**/


    }

    public void timePassed() {
        super.timePassed();
    }


}
