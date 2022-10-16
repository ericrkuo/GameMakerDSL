package parser;

import ast.*;
import enums.TriggerFlavour;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashSet;
import java.util.Set;

import static constants.Constant.GAME_UNIT;

public class DynamicCheck {
    private final Program program;

    public final Pair<Integer, Integer> gameWidthRange = new Pair<>(5, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> rewardValueRange = new Pair<>(50, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> rewardDistanceRange = new Pair<>(1, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> speedRange = new Pair<>(1, 10);
    public final Pair<Integer, Integer> coordinateYRange = new Pair<>(0, 9); // 0-based indexing => 10 walls
    public final Pair<Integer, Integer> scoreRange = new Pair<>(0, 1000);
    public final Pair<Integer, Integer> wallHeightRange = new Pair<>(1, 10 ); // 1-based indexing -> 10 walls
    public final Pair<Integer, Integer> wallWidthRange = new Pair<>(1, Integer.MAX_VALUE);
    public final Pair<Integer, Integer> triggerDistanceRange = new Pair<>(0, 10);
    public Pair<Integer, Integer> coordinateXRange;

    public DynamicCheck(Program program) {
        this.program = program;
        coordinateXRange = new Pair<>(0, (program.getGame().getWidth() / GAME_UNIT)-1);
    }

    public void integerOverflow(Integer target, Pair<Integer, Integer> range, String name, Boolean isGameUnit) {
        target = (isGameUnit) ? target / GAME_UNIT : target;
        if (!(range.a <= target && target <= range.b)) {
            throw new RuntimeException(String.format("Cannot assign the value %d to %s, accepted values are within range %d - %d",
                    target,
                    name,
                    range.a,
                    range.b));
        }
    }

    public void duplicateCoordinate(Set<Coordinate> coordinateSet, Coordinate c, Integer id) {
        if (coordinateSet.contains(c)) {
            throw new RuntimeException(
                    String.format("Found duplicate coordinates (%d, %d) for wall %d",
                            c.getX()/GAME_UNIT,
                            c.getY()/GAME_UNIT,
                            id)
            );
        }
    }

    public void check() {
        // game object
        Game game = program.getGame();
        integerOverflow(game.getWidth(), gameWidthRange, "the width of the game", true);
        integerOverflow(game.getReward().getValue(), rewardValueRange, "the base reward rate", false);
        integerOverflow(game.getReward().getDistance(), rewardDistanceRange, "the reward distance", true);
        // level object
        for (Level l: program.getLevels().values()) {
            integerOverflow(l.getSpeed().getValue(), speedRange, "speed for level " + l.getId(), false);
            for (Coordinate c: l.getSubstageCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, String.format("x-coordinate of substage location within level %d", l.getId()), true);
                integerOverflow(c.getY(), coordinateYRange, String.format("y-coordinate of substage location within level %d", l.getId()), true);
            }
        }
        // substage object
        for (Substage sb: program.getSubStages().values()) {
            integerOverflow(sb.getSpeed().getValue(), speedRange, "speed for substage " + sb.getId(), false);
            integerOverflow(sb.getScore().getValue(), scoreRange, "score for substage " + sb.getId(), false);
        }
        // wall object
        for (Wall w: program.getWalls().values()) {
            integerOverflow(w.getWidth(), wallWidthRange, "the width of wall " + w.getId(), true);
            integerOverflow(w.getHeight(), wallHeightRange, "the height of wall " + w.getId(), true);
            Set<Coordinate> coordinateSet = new HashSet<>();
            for (Coordinate c: w.getCoordinates()) {
                integerOverflow(c.getX(), coordinateXRange, String.format("x-coordinate of wall %d", w.getId()), true);
                integerOverflow(c.getY(), coordinateYRange, String.format("x-coordinate of wall %d", w.getId()), true);
                // check the duplicate coordinate with the same wall
                duplicateCoordinate(coordinateSet, c, w.getId());
                coordinateSet.add(c);
            }
        }
        // fireball object
        for (Fireball fb: program.getFireballs().values()) {
            integerOverflow(fb.getSpeed().getValue(), speedRange, "speed for fireball " + fb.getId(), false);
            integerOverflow(fb.getY_coordinate(), coordinateYRange, String.format("y-coordinate fireball %d", fb.getId()), true);
            if (fb.getTrigger().getTriggerFlavour() == TriggerFlavour.Static) {
                integerOverflow(fb.getCounter(), coordinateXRange, String.format("x-coordinate fireball %d", fb.getId()), true);
            } else {
                integerOverflow(fb.getCounter(), triggerDistanceRange, String.format("Trigger distance of fireball %d", fb.getId()), true);
            }
        }

    }
}
