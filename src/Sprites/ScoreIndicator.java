//208060855 Evyatar Altman
package Sprites;

import Collidable.Block;
import Game.Counter;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class that show the score during the game and the name of the level.
 */
public class ScoreIndicator extends Block {
    private Counter score;
    private String levelName;
    private static final int FONT_SIZE = 16;

    /**
     * constructor with rectangle, color and Counter.
     * @param rect Rectangle
     * @param color Color
     * @param score Counter
     * @param levelName the name of the current level
     */
    public ScoreIndicator(Rectangle rect, Color color, Counter score, String levelName) {
        super(rect, color);
        this.score = score;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        //convert every double to int
        int x = (int) this.getBlock().getUpperLeft().getX();
        int y = (int) this.getBlock().getUpperLeft().getY();
        int width = (int) this.getBlock().getWidth();
        int hight = (int) this.getBlock().getHeight() / 2;
        surface.fillRectangle(x, y, width, hight);
        //draw his frame
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, hight);
        String score = "Score:" + String.valueOf(this.score.getValue());
        //draw the score
        surface.drawText((int) width / 2,  (int) hight / 2 + 5, score, FONT_SIZE);
        //draw the name of the level
        surface.drawText((int) width - 200,  (int) hight / 2 + 5, "Level Name: " + levelName, FONT_SIZE);
    }
}
