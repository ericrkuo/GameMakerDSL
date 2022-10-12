package parser;

import ast.*;
import enums.TriggerFlavour;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashSet;
import java.util.Set;

import static constants.Constant.GAME_UNIT;

public class DynamicCheck {
    private Program program;

    public final Pair<Integer, Integer> gameWidthRange = new Pair<>(100, Integer.MAX_VALUE);

    public final Pair<Integer, Integer> rewardValueRange = new Pair<>(5, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> rewardDistanceRange = new Pair<>(1, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> speedRange = new Pair<>(1, 10);
    public Pair<Integer, Integer> coordinateXRange;
    public final Pair<Integer, Integer> coordinateYRange = new Pair<>(0, 450);
    public final Pair<Integer, Integer> scoreRange = new Pair<>(1, 1000);
    public final Pair<Integer, Integer> wallHeightRange = new Pair<>(1 * GAME_UNIT, 6 * GAME_UNIT);
    public final Pair<Integer, Integer> wallWidthRange = new Pair<>(1 * GAME_UNIT, 6 * GAME_UNIT);
    public final Pair<Integer, Integer> triggerDistanceRange = new Pair<>(10, 100);

    public DynamicCheck(Program program) {
        this.program = program;
        coordinateXRange = new Pair<>(0, program.getGame().getWidth());
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
        integerOverflow(game.getWidth(), gameWidthRange, "Game Width");
        integerOverflow(game.getReward().getValue(), rewardValueRange, "Reward Value");
        integerOverflow(game.getReward().getDistance(), rewardDistanceRange, "Reward Distance");
        // level object
        for (Level l: program.getLevels().values()) {
            integerOverflow(l.getSpeed().getValue(), speedRange, "Speed");
            for (Coordinate c: l.getSubstageCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, "X Coordinate");
                integerOverflow(c.getY(), coordinateYRange, "Y Coordinate");
            }
        }
        // substage object
        for (Substage sb: program.getSubStages().values()) {
            integerOverflow(sb.getSpeed().getValue(), speedRange, "Speed");
            integerOverflow(sb.getScore().getValue(), scoreRange, "Score");
        }
        // wall object
        for (Wall w: program.getWalls().values()) {
            integerOverflow(w.getWidth(), wallWidthRange, "Wall Width");
            integerOverflow(w.getHeight(), wallHeightRange, "Wall Height");
            Set<Coordinate> coordinateSet = new HashSet<>();
            for (Coordinate c: w.getCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, "X Coordinate");
                integerOverflow(c.getY(), coordinateYRange, "Y Coordinate");
                // check the duplicate coordinate with the same wall
                duplicateCoordinate(coordinateSet, c);
                coordinateSet.add(c);
            }
        }
        // fireball object
        for (Fireball fb: program.getFireballs().values()) {
            integerOverflow(fb.getSpeed().getValue(), speedRange, "Speed");
            integerOverflow(fb.getY_coordinate(), coordinateYRange, "Y Coordinate");
            if (fb.getTrigger().getTriggerFlavour() == TriggerFlavour.Static) {
                integerOverflow(fb.getCounter(), coordinateXRange, "X Coordinate");
            } else {
                integerOverflow(fb.getCounter(), triggerDistanceRange, "Trigger Distance");
            }
        }

    }
}
