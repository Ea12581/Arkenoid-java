//208060855 Evyatar Altman
package Game;

import Collidable.Block;
import Geometry.Point;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * create background of sun.
 */
public class Bomb implements Sprite {
    private SpriteCollection sprites;

    /**
     * constructor that build background of a sun.
     */
    public Bomb() {
        this.sprites = new SpriteCollection();
        //create the background
        Color color = new Color(0, 120, 0);
        int width = 800;
        int height = 600;
        Block background = new Block(new Geometry.Rectangle(new Geometry.Point(0, 0), width, height), color);
        sprites.addSprite(background);
        //create the blocks of the bomb
        color = new Color(50, 50, 50);
        width = 10;
        height = 220;
        Block block = new Block(new Geometry.Rectangle(new Geometry.Point(100, 180), width, height), color);
        sprites.addSprite(block);
        color = new Color(40, 20, 50);
        width = 30;
        height = 70;
        block = new Block(new Geometry.Rectangle(new Geometry.Point(90, 400), width, height), color);
        sprites.addSprite(block);
        color = new Color(30, 30, 30);
        width = 90;
        height = 200;
        block = new Block(new Geometry.Rectangle(new Geometry.Point(60, 470), width, height), color);
        sprites.addSprite(block);
        //add the white blocks
        color = Color.WHITE;
        width = 11;
        height = 40;
        int y = 475;
        for (int i = 0; i < 3; i++) {
            int x = 65;
            for (int j = 0; j < 6; j++) {
                block = new Block(new Geometry.Rectangle(new Geometry.Point(x, y), width, height), color);
                sprites.addSprite(block);
                x += (width + 3);
            }
            y += (height + 3);
        }
        //add the balls in the edge
        int x = 106;
        y = 190;
        int r = 15;
        Ball ball = new Ball(new Point(x, y), r, Color.orange);
        sprites.addSprite(ball);
        //the red ball
        r = 10;
        ball = new Ball(new Point(x, y), r, Color.red);
        sprites.addSprite(ball);
        //the white ball
        r = 5;
        ball = new Ball(new Point(x, y), r, Color.white);
        sprites.addSprite(ball);
    }

    @Override
    public void drawOn(DrawSurface d) {
        sprites.drawAllOn(d);
    }

    @Override
    public void timePassed() {
    }
}
