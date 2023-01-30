//208060855 Evyatar Altman
package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class take care on all the sprites in the game and operates them when needed.
 */
public class SpriteCollection {
    private List<Sprite> arrSprite;

    /**
     * constructor to create array to store all the sprites.
     */
    public SpriteCollection() {
        this.arrSprite = new ArrayList<>();
    }
    /**
     * constructor with List of sprites.
     * @param lis of sprites
     */
    public SpriteCollection(List<Sprite> lis) {
        this.arrSprite = lis;
    }


    /**
     * method that add a sprite to the array.
     * @param s  a given Sprite
     */
    public void addSprite(Sprite s) {
        this.arrSprite.add(s);
    }
    /**
     * get sprite by index.
     * @param i the index
     * @return the i sprite in the collection
     */
    public Sprite getSprite(int i) {
        return this.arrSprite.get(i);
    }
    /**
     * Remove given Sprite.
     * @param s  the removed Sprite
     */
    public void removeSprite(Sprite s) {
        this.arrSprite.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite i : this.arrSprite) {
            i.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites on d, the given surface.
     * @param d DrawSurface, the given surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : this.arrSprite) {
            i.drawOn(d);
        }
    }

    /**
     * Return copy of the list of the sprites.
     * @return List of the sprites
     */
    public List<Sprite> createCopy() {
        return new ArrayList<>(this.arrSprite);
    }
}

