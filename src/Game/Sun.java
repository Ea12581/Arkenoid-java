//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * create background of sun.
 */
public class Sun implements Sprite {
    private SpriteCollection sprites;

    /**
     * constructor that build background of a sun.
     */
    public Sun() {
        this.sprites = new SpriteCollection();
        //create white background
        Color color = Color.white;
        int width = 800;
        int height = 600;
        Block background = new Block(new Geometry.Rectangle(new Geometry.Point(0, 0), width, height), color);
        sprites.addSprite(background);
    }
    @Override
    public void drawOn(DrawSurface d) {
        sprites.drawAllOn(d);
        //draw the vectors of the sun
        Color brightYellow = new Color(250, 230, 110);
        int x1 = 150;
        int y1 = 150;
        int x2 = 50;
        int y2 = 250;
        d.setColor(brightYellow);
        for (; x2 < 750; x2 += 7) {
            d.drawLine(x1, y1, x2, y2);
        }
        //draw inner circle
        int r = 50;
        d.fillCircle(x1, y1, r);
        //the middle
        brightYellow = new Color(250, 200, 110);
        d.setColor(brightYellow);
        r = 45;
        d.fillCircle(x1, y1, r);
        //the top
        brightYellow = new Color(250, 240, 110);
        d.setColor(brightYellow);
        r = 40;
        d.fillCircle(x1, y1, r);
    }

    @Override
    public void timePassed() {

    }
}
