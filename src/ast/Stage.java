package ast;

import java.util.List;

/**
 * A stage can either be a level or substage. Each stage has an id, speed, list of ids of walls and fireballs.
 */
public abstract class Stage extends Node {
    private final Integer id;
    private final Speed speed;
    private final List<Integer> wallIDs;
    private final List<Integer> fireballIDs;

    protected Stage(Integer id, Speed speed, List<Integer> wallIDs, List<Integer> fireballIDs) {
        this.id = id;
        this.speed = speed;
        this.wallIDs = wallIDs;
        this.fireballIDs = fireballIDs;
    }

    public Integer getId() {
        return id;
    }

    public Speed getSpeed() {
        return speed;
    }

    public List<Integer> getWallIDs() {
        return wallIDs;
    }

    public List<Integer> getFireballIDs() {
        return fireballIDs;
    }
}
