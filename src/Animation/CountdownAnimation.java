//208060855 Evyatar Altman
package Animation;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private SpriteCollection gameScreen;
    private long miliSecondsPerCount;
    private Sleeper sleeper;
    private boolean stop;
    private static final int NUM_OF_COUNT = 3;

    /**
     * constructor with given count down, seconds between the count and the sprites we need to draw.
     * @param countFrom the start of the count
     * @param gameScreen all the sprites we need to draw on the screen
     * @param miliSecondsPerCount
     */
    public CountdownAnimation(int countFrom, long miliSecondsPerCount, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.miliSecondsPerCount = miliSecondsPerCount;
        this.sleeper = new Sleeper();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //check if a count was started
        if (countFrom < NUM_OF_COUNT) {
            //wait some seconds
            this.sleeper.sleepFor(miliSecondsPerCount);
        }
        //check if the count was over
        if (countFrom <= 0) {
            this.stop = true;
            return;
        }
        this.gameScreen.drawAllOn(d);
        //draw circle behind the digit
        d.setColor(Color.black);
        d.fillCircle(410, 285, 21);
        String currentDigit = String.valueOf(countFrom);
        d.setColor(Color.white);
        //draw the digit
        d.drawText(400, 300, currentDigit, 40);
        //decrease the count
        countFrom -= 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
