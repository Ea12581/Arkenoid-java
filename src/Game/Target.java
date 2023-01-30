//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to create background that drawn by multiple sprites.
 */
public class Target implements Sprite {
    private SpriteCollection sprites;

    /**
     * constructor that build background of a target.
     */
    public Target() {
        this.sprites = new SpriteCollection();
        //create black background
        Color color = Color.black;
        int width = 800;
        int height = 600;
        Block background = new Block(new Geometry.Rectangle(new Geometry.Point(0, 0), width, height), color);
        sprites.addSprite(background);
        //create 2 blue lines
        color = Color.blue;
        width = 200;
        height = 2;
        Block rightToLeft = new Block(new Geometry.Rectangle(new Geometry.Point(295, 135), width, height), color);
        sprites.addSprite(rightToLeft);
        width = 2;
        height = 200;
        Block upToDown = new Block(new Geometry.Rectangle(new Geometry.Point(395, 30), width, height), color);
        sprites.addSprite(upToDown);
    }
    @Override
    public void drawOn(DrawSurface d) {
        sprites.drawAllOn(d);
        //create 3 blue circles
        d.setColor(Color.blue);
        int r = 40;
        int x = 395;
        int y = 135;
        d.drawCircle(x, y, r);
        for (int i = 0; i < 3; i++) {
            d.drawCircle(x, y, r);
            r += 30;
        }
    }

    @Override
    public void timePassed() {
    }

    /**
     * constructor to create list of sprites
     */
}
