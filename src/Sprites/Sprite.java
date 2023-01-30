//208060855 Evyatar Altman
package Sprites;
import biuoop.DrawSurface;

/**
 * this class represent game objects that can be drawn to the screen and do another action by the time that pass.
 */
public interface Sprite {
    /**
     * game object that can be drawn to the screen.
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
