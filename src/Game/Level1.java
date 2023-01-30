package Game;

import Collidable.Block;
import Geometry.Point;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The first level.
 */
public class Level1 implements LevelInformation {
    /**
     * constructor.
     */
    public Level1() {
    }

    @Override
    public int numberOfBalls() {
        //there is 3 balls in this level
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5;
        //create list of velocity as the size of the number of the balls
        List<Velocity> vList = new ArrayList<>();
        int numBalls = numberOfBalls();
        Velocity v;
        for (int i = 0; i < numBalls; i++) {
            //give velocity for going up
            v = new Velocity(0, -speed);
            vList.add(v);
        }
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        //create Target background
        return new Target();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<Block>();
        Block newBlock;
        Color rowColor = Color.red;
        //create a square above the ball
        newBlock = new Block(new Geometry.Rectangle(new Point(380, 120), 30, 30), rowColor);
        blocks.add(newBlock);

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Ball> ballsGenerator(int ballNum, List<Velocity> vList, GameEnvironment g) {
        //create list of balls
        List<Ball> balls = new LinkedList<>();
        Ball ball;
        int r = 5;
        int x = 400;
        int y = 400;
        int i = 0;
        ball = new Ball(new Point(x, y), r, Color.WHITE, g);
        ball.setVelocity(vList.get(i));
        balls.add(ball);
        return  balls;
    }
}

