package parser;

import ast.*;
import enums.TriggerFlavour;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashSet;
import java.util.Set;

import static constants.Constant.GAME_UNIT;

public class DynamicCheck {
    private final Program program;

    public final Pair<Integer, Integer> gameWidthRange = new Pair<>(100, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> rewardValueRange = new Pair<>(5, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> rewardDistanceRange = new Pair<>(1, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> speedRange = new Pair<>(1, 10);
    public final Pair<Integer, Integer> coordinateYRange = new Pair<>(0, 10);
    public final Pair<Integer, Integer> scoreRange = new Pair<>(1, 1000);
    public final Pair<Integer, Integer> wallHeightRange = new Pair<>(1, 10 );
    public final Pair<Integer, Integer> wallWidthRange = new Pair<>(1, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> triggerDistanceRange = new Pair<>(10, 100);
    public Pair<Integer, Integer> coordinateXRange;

    public DynamicCheck(Program program) {
        this.program = program;
        coordinateXRange = new Pair<>(0, program.getGame().getWidth() / GAME_UNIT);
    }

    public void integerOverflow(Integer target, Pair<Integer, Integer> range, String name, Boolean isGameUnit) {
        target = (isGameUnit) ? target / GAME_UNIT : target;
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
        integerOverflow(game.getWidth(), gameWidthRange, "Game Width", false);
        integerOverflow(game.getReward().getValue(), rewardValueRange, "Reward Value", false);
        integerOverflow(game.getReward().getDistance(), rewardDistanceRange, "Reward Distance", false);
        // level object
        for (Level l: program.getLevels().values()) {
            integerOverflow(l.getSpeed().getValue(), speedRange, "Speed", false);
            for (Coordinate c: l.getSubstageCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, "X Coordinate", true);
                integerOverflow(c.getY(), coordinateYRange, "Y Coordinate", true);
            }
        }
        // substage object
        for (Substage sb: program.getSubStages().values()) {
            integerOverflow(sb.getSpeed().getValue(), speedRange, "Speed", false);
            integerOverflow(sb.getScore().getValue(), scoreRange, "Score", false);
        }
        // wall object
        for (Wall w: program.getWalls().values()) {
            integerOverflow(w.getWidth(), wallWidthRange, "Wall Width", true);
            integerOverflow(w.getHeight(), wallHeightRange, "Wall Height", true);
            Set<Coordinate> coordinateSet = new HashSet<>();
            for (Coordinate c: w.getCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, "X Coordinate", true);
                integerOverflow(c.getY(), coordinateYRange, "Y Coordinate", true);
                // check the duplicate coordinate with the same wall
                duplicateCoordinate(coordinateSet, c);
                coordinateSet.add(c);
            }
        }
        // fireball object
        for (Fireball fb: program.getFireballs().values()) {
            integerOverflow(fb.getSpeed().getValue(), speedRange, "Speed", false);
            integerOverflow(fb.getY_coordinate(), coordinateYRange, "Y Coordinate", true);
            if (fb.getTrigger().getTriggerFlavour() == TriggerFlavour.Static) {
                integerOverflow(fb.getCounter(), coordinateXRange, "X Coordinate", true);
            } else {
                integerOverflow(fb.getCounter(), triggerDistanceRange, "Trigger Distance", false);
            }
        }

    }
}
