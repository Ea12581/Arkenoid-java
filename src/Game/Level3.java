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
 * the third level.
 */
public class Level3 implements LevelInformation {
    /**
     * constructor that calls to the superClass.
     */
    public Level3() {
    }

    @Override
    public int numberOfBalls() {
        //there is 2 balls in this level
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5;
        //the angle of the start ball
        double angle = 320;
        //create list of velocity in the size of the number of the balls
        List<Velocity> vList = new ArrayList<>();
        int numBalls = numberOfBalls();
        Velocity v;
        for (int i = 0; i < numBalls; i++) {
            //give random velocity
            v = Velocity.fromAngleAndSpeed(angle, speed);
            vList.add(v);
            //take the angle right for the next ball
            angle += 90;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Bomb();
    }

    @Override
    public List<Block> blocks() {
        Color[] arryC = {Color.gray, Color.red, Color.yellow, Color.blue, Color.white};
        List<Block> blocks = new LinkedList<Block>();
        Block newBlock;
        //num of blocks in the first row
        int blockN = 10;
        int blockW = 50;
        int blockH = 25;
        double y = 150;
        //add five rows of blocks, start from the right boundary
        for (double i = 0; i < arryC.length; i++) {
            Color rowColor = arryC[(int) i];
            double x = 700;
            for (double j = 0; j < blockN - i; j++) {
                newBlock = new Block(new Geometry.Rectangle(new Point(x, y), blockW, blockH), rowColor);
                blocks.add(newBlock);
                x -= blockW;
            }
            y += blockH;
        }
        return blocks;
    }
    @Override
    public List<Ball> ballsGenerator(int ballNum, List<Velocity> vList, GameEnvironment g) {
        //create list of balls
        List<Ball> balls = new LinkedList<>();
        Ball ball;
        int x = 320;
        int y = 400;
        int r = 5;
        int i;
        for (i = 0; i < ballNum; i++) {
            ball = new Ball(new Point(x, y), r, Color.WHITE, g);
            ball.setVelocity(vList.get(i));
            balls.add(ball);
            x += 200;
        }
        return  balls;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
