//208060855 Evyatar Altman
package Collidable;


import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * class to create blocks that kill balls.
 */
public class DeathRegion extends Block {
    /**
     * constructor with rectangle.
     * @param rect rectangle
     */
    public DeathRegion(Rectangle rect) {
        super(rect);
    }

    /**
     * constructor with rectangle and color.
     * @param rect rectangle
     * @param color of the rectangle
     */
    public DeathRegion(Rectangle rect, Color color) {
        super(rect, color);
    }
    @Override
    public void drawOn(DrawSurface surface) {
        //draw nothing
    }
}
