//208060855 Evyatar Altman
import Animation.AnimationRunner;
import Game.GameFlow;
import Game.Level1;
import Game.Level2;
import Game.Level3;
import Game.Level4;
import Game.LevelInformation;


import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * the class that binds every things together.
 */
public class Ass6Game {
    /**
     * the main that operates everything.
     * @param args from the cmd
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(new GUI("Arkenoid", 800, 600),
                60, new Sleeper());
        GameFlow game = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>(Arrays.asList(new Level1(), new Level2(), new Level3(),
                new Level4()));
        List<LevelInformation> runLevels = levels;
        //if there are args, create new list of levels;
        if (args.length != 0) {
            runLevels = new ArrayList<>();
            for (String s : args) {
                //check if this a number
                if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                    runLevels.add(levels.get(Integer.parseInt(s) - 1));
                }
            }
            //if there is no match number in the args
            if (runLevels.size() == 0) {
                runLevels = levels;
            }
        }
        game.runLevels(runLevels);
    }
}
