//208060855 Evyatar Altman
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
 * The second Level.
 */
public class Level2 implements LevelInformation {
    /**
     * constructor that calls to the superClass.
     */
    public Level2() {
        super();
    }

    @Override
    public int numberOfBalls() {
        //there is 10 balls in this level
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5;
        //the angle of the start ball
        double angle = 300;
        //create list of velocity in the size of the number of the balls
        List<Velocity> vList = new ArrayList<>();
        int numBalls = numberOfBalls();
        Velocity v;
        //create v for the left balls
        for (int i = 0; i < numBalls / 2; i++) {
            //give random velocity
            v = Velocity.fromAngleAndSpeed(angle, speed);
            vList.add(v);
            //take the angle right for the next ball
            angle += 18;
        }
        angle -= 18;
        v = Velocity.fromAngleAndSpeed(angle, speed);
        vList.add(v);
        //for the right
        for (int i = 0; i < numBalls / 2; i++) {
            //give random velocity
            v = Velocity.fromAngleAndSpeed(angle, speed);
            vList.add(v);
            //take the angle right for the next ball
            angle += 18;
        }
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        //create the background
        return new Sun();
    }

    @Override
    public List<Block> blocks() {
        Color[] arryC = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        int colorNum = arryC.length;
        List<Block> blocks = new LinkedList<Block>();
        //add one row of blocks with colors in pairs
        Block newBlock;
        //height of the row
        double y = 250;
        //x start of the first block
        double x = 50;
        double blockW = 46.8;
        double blockH = 25;
        for (double j = 0; j < colorNum; j++) {
            newBlock = new Block(new Geometry.Rectangle(new Point(x, y), blockW, blockH), arryC[(int) j]);
            blocks.add(newBlock);
            x += blockW;
            newBlock = new Block(new Geometry.Rectangle(new Point(x, y), blockW, blockH), arryC[(int) j]);
            blocks.add(newBlock);
            x += blockW;
            //create triple for the green color
            if (arryC[(int) j] == Color.green) {
                newBlock = new Block(new Geometry.Rectangle(new Point(x, y), blockW, blockH), arryC[(int) j]);
                blocks.add(newBlock);
                x += blockW;
            }
        }
        return blocks;
    }
    @Override
    public List<Ball> ballsGenerator(int ballNum, List<Velocity> vList, GameEnvironment g) {
        //create list of balls
        List<Ball> balls = new LinkedList<>();
        Ball ball;
        int x = 260;
        int y = 400;
        int r = 5;
        //create the left balls
        int i;
        for (i = 0; i < ballNum / 2; i++) {
            ball = new Ball(new Point(x, y), r, Color.WHITE, g);
            ball.setVelocity(vList.get(i));
            balls.add(ball);
            x += 35 + i;
            if (i < ballNum / 2 - 1) {
                y -= (30 - 5 * i);
            }
        }
        x += 15;
        //keep the old i to get the rest v
        int j = i;
        i--;
        //create the right balls
        for (; i >= 0; i--, j++) {
            ball = new Ball(new Point(x, y), 5, Color.WHITE, g);
            ball.setVelocity(vList.get(j));
            balls.add(ball);
            x += 35 + i;
            y += (35 - 5 * i);
        }
        return  balls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
