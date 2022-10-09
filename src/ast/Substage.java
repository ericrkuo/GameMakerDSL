package ast;

import ui.Goal;
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

    @Override
    public void populateGoal(Game game) {
        this.renderableObjects.add(new Goal(game.getWidth(), true));
    }

    /**
     * We need to populate the objects again because a substage can be reused across multiple levels
     */
    public Substage copy(Program program) {
        Substage substage = new Substage(getId(), getSpeed(), getWallIDs(), getFireballIDs(), getScore());
        substage.populateObjects(program);
        return substage;
    }
}
