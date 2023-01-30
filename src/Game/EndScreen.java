package Game;

import Animation.Animation;
import Collidable.Block;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to display the screen of the end of the game.
 */
public class EndScreen implements Animation {
    private Counter score;
    private int numBalls;
    private static final int NO_BALLS = 0;

    /**
     * constructor with keySensor.
     * @param numBalls the num of balls that have reminds
     * @param score the score
     */
    public EndScreen(Counter score, int numBalls) {
        this.score = score;
        this.numBalls = numBalls;
    }

    /**
     * create loop inside the loop that print the pause screen. get out if "space" was pressed.
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        //create the background
        Color color = Color.black;
        int width = 800;
        int height = 600;
        //draw black background
        Block background = new Block(new Geometry.Rectangle(new Geometry.Point(0, 0), width, height), color);
        background.drawOn(d);
        d.setColor(Color.white);
        String massage;
        //if the player won, there are still balls
        if (numBalls != NO_BALLS) {
           massage = "You Win! Your score is " + String.valueOf(score.getValue());
        } else {
            massage = "Game Over.Your score is " + String.valueOf(score.getValue());
        }
        //draw the end screen and the score
        d.drawText(10, d.getHeight() / 2, massage, 32);
    }

    /**
     * return if the pause screen should be stopped.
     * @return true if yes, false if not
     */
    public boolean shouldStop() {
        return false;
    }
}
