//208060855 Evyatar Altman
package Animation;

import biuoop.DrawSurface;

/**
 * create a pause in the game when press on "p" and unlock when "space" is being pressed.
 */
public class PauseScreen implements Animation {
    /**
     * constructor.
     */
    public PauseScreen() {
    }

    /**
     * create loop inside the loop that print the pause screen.
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        //draw  "paused -- press space to continue"
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
