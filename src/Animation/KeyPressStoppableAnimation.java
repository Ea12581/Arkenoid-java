//Evyatar Altman 208060855
package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class for obj that stop the animation after press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor the sensor to the keyboard
     * @param animation the obj the decorator holds
     * @param key which stop the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, Animation animation, String key) {
        this.sensor = sensor;
        this.animation = animation;
        this.stop = false;
        this.key = key;
        //if it's been pressed before the animation, it's from another animation
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        //check if "space" was pressed but specifies for this animation
        if (this.sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
            //change back if the key was not pressed
        } else if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
       return this.stop;
    }

    /**
     * get the sensor.
     * @return KeyboardSensor sensor for the keyboard
     */
    protected KeyboardSensor getSensor() {
        return sensor;
    }

    /**
     * get the string key.
     * @return KeyboardSensor sensor for the keyboard
     */
    protected String getKey() {
        return key;
    }
}
