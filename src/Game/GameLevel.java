//208060855 Evyatar Altman
package Game;

import Animation.Animation;
import Animation.AnimationRunner;
import Animation.PauseScreen;
import Animation.CountdownAnimation;
import Animation.KeyPressStoppableAnimation;
import Collidable.Block;
import Collidable.Collidable;
import Collidable.Paddle;
import Collidable.HitListener;
import Collidable.DeathRegion;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.ScoreIndicator;
import Sprites.SpriteCollection;
import Sprites.Ball;
import Sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * This class operates the whole game, include his Sprites and collidable objects, by the game environment.
 */
public class GameLevel implements HitListener, Animation {
    private static final int GAME_OVER = 0;
    private static final int SCORE_FOR_ENDING_LEVEL = 100;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    //the boundaries of the game
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    //the height of the boundaries up and down
    private static final int BON_H = 50;
    //the width of the boundaries left and right
    public static final int BON_W = 50;
    //the width of regular block
    private static final int BLOCK_W = 50;
    //the height of regular block
    private static final int BLOCK_H = 25;
    //the radius of the ball
    private static final int R = 5;
    //max speed
    private static final int SPEED = 5;
    //max radians
    private static final double MAX_RAD = 2 * 3.14;
    //information on the level
    private LevelInformation level;
    /**
     * constructor for new Game.Game obj.
     * @param level the level that we want to play
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     * @param score counter for score
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.environment  = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.runner = ar;
        this.keyboard = ks;
        this.remainingBlocks = new Counter();
        this.level = level;
        this.remainingBalls = new Counter();
        this.score = score;
    }

    /**
     * add new collidble object to the game environment.
     * @param c Collidable, the given object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add new Sprite object to the sprite collection.
     * @param s new Sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * generate random double number between min and max.
     * @param min double
     * @param max double
     * @return random double
     */
    public static double nextDoubleBetween(double min, double max) {
        return (new Random().nextDouble() * (max - min)) + min;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //add the backGround
        this.addSprite(level.getBackground());
        //create ball remover
        BallRemover ballR = new BallRemover(this, remainingBalls);
        //create the four boundaries as blocks on the sides
        Block upBoundary = new Block(new Rectangle(new Point(0, 0), WIDTH, BON_H), Color.GRAY);
        //the left is between the up and down boundaries, so his height decreased by their heights
        //and his up point start after the up.
        Block leftBoundary = new Block(new Rectangle(new Point(0, BON_H), BON_W,
                HEIGHT - BON_H), Color.GRAY);
        //the y point of the down was calculates by the end of the screen - his height
        Block downBoundary = new DeathRegion(new Rectangle(new Point(0, HEIGHT), WIDTH, BON_H),
                Color.GRAY);
        downBoundary.addHitListener(ballR);
        //the right is between the up and down boundaries, so his height decreased by their width
        //his x point was calculates by the end of the screen to the right- his width, his y start after the up boundary
        Block rightBoundary = new Block(new Rectangle(new Point(WIDTH - BON_W, BON_H),
                BON_W, HEIGHT - BON_H),
                Color.GRAY);
        //create the score indicator
        ScoreIndicator sI = new ScoreIndicator(new Rectangle(new Point(0, 0), WIDTH, BON_H),
                Color.white, this.score, level.levelName());
        Block[] boundaries = {upBoundary, leftBoundary, downBoundary, rightBoundary, sI};
        //add the boundaries to the game
        for (Block i : boundaries) {
            i.addToGame(this);
        }
        //add the balls, create their start point at the bottom of the screen
        List<Ball> balls = this.level.ballsGenerator(level.numberOfBalls(), level.initialBallVelocities(),
                this.environment);
        for (Ball ball : balls) {
            ball.addToGame(this);
            remainingBalls.increase(1);
        }
        BlockRemover blockR = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreT = new ScoreTrackingListener(score);
        List<Block> blocks = level.blocks();
        //add all the blocks to the game
        for (Block newBlock : blocks) {
                newBlock.addHitListener(blockR);
                newBlock.addHitListener(scoreT);
                this.remainingBlocks.increase(1);
                newBlock.addToGame(this);
            }

        //add the paddle
        this.setPaddle(new Paddle(this.keyboard, level.paddleSpeed(), level.paddleWidth()));
        this.paddle.addToGame(this);
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //activate countdown with countdown from 3 and delay of 2/3 seconds between digits, 3 seconds total
        this.runner.run(new CountdownAnimation(3, 2000 / 3,
                new SpriteCollection(this.sprites.createCopy())));
        //activate the loop
        this.runner.run(this);
    }

    /**
     * getter to get the width of the boundaries.
     * @return int width of the boundaries
     */
    public int getBoundaryWidth() {
        return BON_W;
    }

    /**
     * getter to get the width of the board.
     * @return int width of the board
     */
    public int getBoardWidth() {
        return WIDTH;
    }

    /**
     * getter to get the height of the board.
     * @return int height of the board
     */
    public int getBoardHeight() {
        return HEIGHT;
    }

    /**
     * set new gameEnvironment.
     * @param gameEnvironment new Game.Game.GameEnvironment
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * set new sprites collection.
     * @param spriteCollection new SpriteCollection
     */
    public void setSprites(SpriteCollection spriteCollection) {
        this.sprites = spriteCollection;
    }

    /**
     * set new paddle.
     * @param paddle new Paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * Remove Collidable obj.
     * @param c Collidable obj that should be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove Sprite obj.
     * @param s the Sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //create copy so that changes won't disturb the current iteration
        SpriteCollection sprites = new SpriteCollection(this.sprites.createCopy());
        //draw all the sprites
        sprites.drawAllOn(d);
        //move the sprites
        sprites.notifyAllTimePassed();
        //check if the player want a pause screen and pressed "p"
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, new PauseScreen(), KeyboardSensor.SPACE_KEY));
        }
    }

    @Override
    public boolean shouldStop() {
        //check if there are no more blocks, so we can end the game
        if (this.remainingBlocks.getValue() == GAME_OVER) {
            score.increase(SCORE_FOR_ENDING_LEVEL);
            return true;
        }
        //check if there are no more balls
        if (this.remainingBalls.getValue() == GAME_OVER) {
            return true;

        }
        return false;
    }
    /**
     * get the current number of balls.
     * @return current number of balls
     */
    int getCurrentNumOfBalls() {
        return this.remainingBalls.getValue();
    }
}