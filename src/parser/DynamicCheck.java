package parser;

import ast.*;
import enums.TriggerFlavour;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashSet;
import java.util.Set;

import static constants.Constant.GAME_UNIT;

public class DynamicCheck {
    private Program program;

    public final Pair<Integer, Integer> gameWidth = new Pair<>(100, 2000);

    public final Pair<Integer, Integer> rewardValue = new Pair<>(5, 100);
    public final Pair<Integer, Integer> rewardDistance = new Pair<>(1, 10);
    public final Pair<Integer, Integer> speed = new Pair<>(1, 10);
    public Pair<Integer, Integer> coordinateX;
    public final Pair<Integer, Integer> coordinateY = new Pair<>(0, 450);
    public final Pair<Integer, Integer> score = new Pair<>(1, 1000);
    public final Pair<Integer, Integer> wallHeight = new Pair<>(1 * GAME_UNIT, 6 * GAME_UNIT);
    public final Pair<Integer, Integer> wallWidth = new Pair<>(1 * GAME_UNIT, 6 * GAME_UNIT);
    public final Pair<Integer, Integer> triggerDistance = new Pair<>(10, 100);

    public DynamicCheck(Program program) {
        this.program = program;
        coordinateX = new Pair<>(0, program.getGame().getWidth());
    }

    public void integerOverflow(Integer target, Pair<Integer, Integer> range, String name) {
        if (!(range.a <= target && target <= range.b)) {
            throw new RuntimeException(String.format("Cannot assign the value, %d, (%s: %d - %d)", target, name,
                    range.a,
                    range.b));
        }
    }

    public void duplicateCoordinate(Set<Coordinate> coordinateSet, Coordinate c) {
        if (coordinateSet.contains(c)) {
            throw new RuntimeException("Duplicate Coordinates with the same wall");
        }
    }

    public void check() {
        // game object
        Game game = program.getGame();
        integerOverflow(game.getWidth(), gameWidth, "Game Width");
        integerOverflow(game.getReward().getValue(), rewardValue, "Reward Value");
        integerOverflow(game.getReward().getDistance(), rewardDistance, "Reward Distance");
        // level object
        for (Level l: program.getLevels().values()) {
            integerOverflow(l.getSpeed().getValue(), speed, "Speed");
            for (Coordinate c: l.getSubstageCoordinates()) {
                integerOverflow(c.getX(), coordinateX, "X Coordinate");
                integerOverflow(c.getY(), coordinateY, "Y Coordinate");
            }
        }
        // substage object
        for (Substage sb: program.getSubStages().values()) {
            integerOverflow(sb.getSpeed().getValue(), speed, "Speed");
            integerOverflow(sb.getScore().getValue(), score, "Score");
        }
        // wall object
        for (Wall w: program.getWalls().values()) {
            integerOverflow(w.getWidth(), wallWidth, "Wall Width");
            integerOverflow(w.getHeight(), wallHeight, "Wall Height");
            Set<Coordinate> coordinateSet = new HashSet<>();
            for (Coordinate c: w.getCoordinates()) {
                integerOverflow(c.getX(), coordinateX, "X Coordinate");
                integerOverflow(c.getY(), coordinateY, "Y Coordinate");
                // check the duplicate coordinate with the same wall
                duplicateCoordinate(coordinateSet, c);
                coordinateSet.add(c);
            }
        }
        // fireball object
        for (Fireball fb: program.getFireballs().values()) {
            integerOverflow(fb.getSpeed().getValue(), speed, "Speed");
            integerOverflow(fb.getY_coordinate(), coordinateY, "Y Coordinate");
            if (fb.getTrigger().getTriggerFlavour() == TriggerFlavour.Static) {
                integerOverflow(fb.getCounter(), coordinateX, "X Coordinate");
            } else {
                integerOverflow(fb.getCounter(), triggerDistance, "Trigger Distance");
            }
        }

    }
}
