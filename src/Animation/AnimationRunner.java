//208060855 Evyatar Altman
package Animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Run an animation loop.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Initialize an animation runner with gui, how many frame for seconds and a sleeper.
     * @param gui
     * @param framesPerSecond
     * @param sleeper
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * get the gui.
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Run the animation loop for a given animation.
     * @param animation the given animation
     */
    public void run(Animation animation) {
        //there for
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //draw one frame of the animation
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
