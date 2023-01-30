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
public class Sky implements Sprite {
    private SpriteCollection sprites;

    /**
     * constructor that build background of a sun.
     */
    public Sky() {
        this.sprites = new SpriteCollection();
        //add the balls of the clouds. The most brights
        int x = 150;
        int y = 400;
        int r = 30;
        Color color = new Color(240, 240, 240);
        Ball ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        x = 620;
        y = 500;
        r = 30;
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);

        //more balls
        x = 195;
        y = 380;
        r = 35;
        color = new Color(200, 200, 200);
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        x = 675;
        y = 480;
        r = 35;
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        //more balls
        x = 165;
        y = 410;
        r = 25;
        color = new Color(180, 180, 180);
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        x = 630;
        y = 510;
        r = 25;
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);

        //more balls
        x = 155;
        y = 430;
        r = 20;
        color = new Color(160, 160, 160);
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        x = 645;
        y = 505;
        r = 20;
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);

        //last balls
        x = 180;
        y = 400;
        r = 20;
        color = new Color(190, 190, 190);
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
        x = 660;
        y = 480;
        r = 20;
        ball = new Ball(new Point(x, y), r, color);
        sprites.addSprite(ball);
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw the background
        //create the background
        Color color = new Color(0, 140, 255);
        int width = 800;
        int height = 600;
        Block background = new Block(new Geometry.Rectangle(new Geometry.Point(0, 0), width, height), color);
        background.drawOn(d);
        //draw the rain
        int x1 = 130;
        int y1 = 400;
        int x2 = 100;
        int y2 = 600;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1 += 10;
            x2 += 10;
        }

        x1 = 600;
        y1 = 510;
        x2 = 590;
        y2 = 600;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1 += 10;
            x2 += 10;
        }
        sprites.drawAllOn(d);
    }

    @Override
    public void timePassed() {
    }
}
