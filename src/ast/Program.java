package ast;

import java.util.HashMap;
import java.util.Map;

/**
 * The main representation of our game. The program holds information about the game such as
 * all the mappings of ids to levels, substages, walls, and fireballs.
 */
public class Program extends Node {
    private final Game game;
    private final Map<Integer, Level> levels = new HashMap<>();
    private final Map<Integer, Substage> subStages = new HashMap<>();
    private final Map<Integer, Wall> walls = new HashMap<>();
    private final Map<Integer, Fireball> fireballs = new HashMap<>();

    public Program(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }

    public Map<Integer, Substage> getSubStages() {
        return subStages;
    }

    public Map<Integer, Wall> getWalls() {
        return walls;
    }

    public Map<Integer, Fireball> getFireballs() {
        return fireballs;
    }

    /**
     * Create all new levels and substage objects, so we reset positions.
     * Render all objects again for each substage and level.
     */
    public void renderAllObjects() {
        this.levels.forEach((key, value) -> this.levels.put(key, value.copy()));
        this.subStages.forEach((key, value) -> this.subStages.put(key, value.copy(this)));
        this.getSubStages().values().forEach(s -> s.populateObjects(this));
        this.getLevels().values().forEach(l -> l.populateObjects(this));
    }
}
