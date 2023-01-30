package Game;

import Collidable.Block;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;

import java.util.List;


/**
 * specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * get the numbers of balls of the level.
     * @return num of balls
     */
    int numberOfBalls();

    /**
     *  The initial velocity of each ball. size of numberOfBalls().
     * @return list of velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * get the speed of the paddle.
     * @return speed of the paddle
     */
    int paddleSpeed();

    /**
     * get the width of the paddle.
     * @return width of the paddle
     */
    int paddleWidth();

    /**
     * the level name, it would be displayed at the top of the screen.
     * @return level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return number of blocks to remove
     */
    int numberOfBlocksToRemove();

    /**
     * create the balls of the level.
     * @param ballNum number of the balls
     * @param vList their velocity
     * @param g Game Environment
     * @return list of balls
     */
    List<Ball> ballsGenerator(int ballNum, List<Velocity> vList, GameEnvironment g);
}
