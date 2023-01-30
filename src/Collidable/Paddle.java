// 208060855 Evyatar Altman
package Collidable;
import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * class to create the paddle in the game, which the player uses to bounce the ball.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    //the sizes of the paddle
    private int width;
    private static final int HEIGHT = 5;
    //his upLeft point
    private static final double START_X = 360;
    private static final double START_Y = 550;
    private static final Color COLOR = Color.orange;
    private static final int BOARD_WIDTH = 800;
    //his progress left\right per press
    private int progress;
    //the block that represent the paddle
    private Block paddle;
    private static final int BOUNDARY_WIDTH = 50;

    /**
     * constructor to build the paddle with KeyboardSensor.
     * @param keyboard the keyboard sensor
     * @param progress progress left\right per press
     * @param width of the paddle
     */
    public Paddle(KeyboardSensor keyboard, int progress, int width) {
        //set the paddle in the middle of the bottom
        this.paddle = new Block(new Rectangle(new Point(400 - width / 2, START_Y), width, HEIGHT), COLOR);
        this.keyboard = keyboard;
        this.progress = progress;
        this.width = width;
    }

    /**
     * constructor to build the paddle with KeyboardSensor and gui.
     * @param keyboard the keyboard sensor
     * @param gui new GUI
     * @param width of the paddle
     * @param progress left\right
     */
    public Paddle(GUI gui, KeyboardSensor keyboard,  int progress, int width) {
        this.paddle = new Block(new Rectangle(new Point(START_X, START_Y), width, HEIGHT), COLOR);
        this.keyboard = keyboard;
        this.width = width;
        this.progress = progress;
    }

    /**
     * move the paddle left if the player presses on the left arrow.
     */
    public void moveLeft() {
        //prepare the x
        double x = this.paddle.getBlock().getUpperLeft().getX();
        //check if it got to the left boundary
        if (x <= BOUNDARY_WIDTH) {
            return;
        }
        //add value in left direction
        if (this.keyboard.isPressed(keyboard.LEFT_KEY)) {
            //check if going left would pass the boundary, change to the edge of the boundary
            if ((x - progress) <= BOUNDARY_WIDTH) {
                this.paddle.setX(BOUNDARY_WIDTH);
            } else {
                //progress left
                this.paddle.setX(x - progress);
            }
        }
    }

    /**
     * move the paddle right if the player presses on the right arrow.
     */
    public void moveRight() {
        //prepare the x
        double x = this.paddle.getBlock().getUpperLeft().getX();
        //check if the paddle got to the right boundary
        if (x + width >= BOARD_WIDTH - BOUNDARY_WIDTH) {
            return;
        }
        //check if the progress would get the paddle into the boundary
        if (this.keyboard.isPressed(keyboard.RIGHT_KEY)) {
            //check if going right would pass the boundary, change to the edge of the boundary
            if ((x + width + progress) >= BOARD_WIDTH - BOUNDARY_WIDTH) {
                this.paddle.setX(BOARD_WIDTH - (BOUNDARY_WIDTH + width));
            } else {
                //progress right
                this.paddle.setX(x + progress);
            }
        }
    }

    /**
     * method to notify the paddle to move.
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * method to draw the paddle on a given surface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * Return the block of the paddle in case of collision with the ball.
     * @return Rectangle obj
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getBlock();
    }

    /**
     * changing the ball's directory if it had hit the paddle.
     * @param collisionPoint Point
     * @param currentVelocity Velocity
     * @param hitter Ball that hit
     * @return new velocity for the ball
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        //dividing the paddle to 5 region so the ball bounce in different angle from each region on the paddle
        //the y value in every region is the same as the paddle
        double paddleX = this.paddle.getBlock().getUpperLeft().getX();
        double paddleY = this.paddle.getBlock().getUpperLeft().getY();
        double xColl = collisionPoint.getX();
        double yColl = collisionPoint.getY();
        double regionWidth = this.width / 5;
        double region1 = paddleX + regionWidth;
        double region2 = paddleX + 2 * regionWidth;
        double region3 = paddleX + 3 * regionWidth;
        double region4 = paddleX + 4 * regionWidth;
        double speedBall = currentVelocity.getSpeed();
        //if the hit was above
        if (yColl <= paddleY) {
            //if the hit was in the most left side of the paddle, return in 300 angle (-60)
            if (xColl <= region1) {
                return Velocity.fromAngleAndSpeed(300, speedBall);
            }
            //in the second left but not most, 330 angle (-30)
            if (xColl <= region2) {
                return Velocity.fromAngleAndSpeed(330, speedBall);
            }
            //if hit the middle, angle 0
            if (xColl <= region3) {
                return this.paddle.hit(collisionPoint, currentVelocity, hitter);
            }
            //in the second right but not most, angle 30
            if (xColl <= region4) {
                return Velocity.fromAngleAndSpeed(30, speedBall);
            }
            //most right, angle 60
            return Velocity.fromAngleAndSpeed(60, speedBall);
        }
        return this.paddle.hit(collisionPoint, currentVelocity, hitter);
    }

    /**
     * Add this paddle to the game.
     * @param g  our Game.Game obj
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
