//208060855 Evyatar Altman
package Game;

/**
 * simple class that used to count things.
 */
public class Counter {
    private int number;

    /**
     * constructor that initialize the number to 0.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * add number to current count.
     * @param number given number
     */
    void increase(int number) {
        this.number += number;
    }

    /**
     * subtract number from current count.
     * @param number given number
     */
    void decrease(int number) {
        this.number -= number;
    }

    /**
     * get current count.
     * @return the amount of counts
     */
    public int getValue() {
        return this.number;
    }
}
