package Animation;

import biuoop.DrawSurface;

/**
 * Interface for objects that use an animation loop can create frames for animations.
 */
public interface Animation {
    /**
     * Draw one frame of the animation.
     * @param d the serface from a GIU
     */
    void doOneFrame(DrawSurface d);

    /**
     * check if the animation should stop.
     * @return true for stop, false for continue
     */
    boolean shouldStop();
}
