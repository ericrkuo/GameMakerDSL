package ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A level maintains a map of coordinates to substage location ids.
 */
public class Level extends Stage {
    private final Map<Coordinate, Integer> coordinateToSubstageIdMap = new HashMap<>();

    public Level(Integer id, Speed speed, List<Integer> wallIDs, List<Integer> fireballIDs) {
        super(id, speed, wallIDs, fireballIDs);
    }

    public Map<Coordinate, Integer> getCoordinateToSubstageIdMap() {
        return coordinateToSubstageIdMap;
    }
}
