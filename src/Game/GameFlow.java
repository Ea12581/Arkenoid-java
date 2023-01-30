//208060855 Evyatar Altman
package Game;

import Animation.AnimationRunner;
import Animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * This class is being in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private static final int NO_MORE_BALLS = 0;

    /**
     * constructor with animation runner and keyboard sensor.
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * Run all the levels one by one.
     * @param levels list with the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        //create new score counter
        Counter score = new Counter();
        int ballsNum = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, score);
            //initialization the game
            level.initialize();
            //run
            level.run();
            ballsNum = level.getCurrentNumOfBalls();
            //stop if there are no more balls
            if (ballsNum == NO_MORE_BALLS) {
                break;
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, new EndScreen(score, ballsNum), KeyboardSensor.SPACE_KEY));
        //close the surface
        this.ar.getGui().close();
    }
}
