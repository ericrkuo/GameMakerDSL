package ast;

import java.util.List;

/**
 * A substage is entered through a level, and can have special behaviour to modify how the score is handled.
 */
public class Substage extends Stage {
    private final Score score;

    public Substage(Integer id, Speed speed, List<Integer> wallIDs, List<Integer> fireballIDs, Score score) {
        super(id, speed, wallIDs, fireballIDs);
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
