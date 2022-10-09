package ast;

import libs.RenderableObject;
import ui.Goal;
import ui.Portal;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * A level maintains a map of coordinates to substage location ids.
 */
public class Level extends Stage {
    public Substage activeSubstage = null;
    private final Map<Coordinate, Integer> coordinateToSubstageIdMap;

    public Level(Integer id, Speed speed, List<Integer> wallIDs, List<Integer> fireballIDs, Map<Coordinate, Integer> coordinateToSubstageIdMap) {
        super(id, speed, wallIDs, fireballIDs);
        this.coordinateToSubstageIdMap = coordinateToSubstageIdMap;
    }

    @Override
    public void populateSubstages(Program program) {
        for (Map.Entry<Coordinate, Integer> e : coordinateToSubstageIdMap.entrySet()) {
            Substage substage = program.getSubStages().get(e.getValue());
            if (substage != null) {
                this.renderableObjects.add(new Portal(e.getKey(), substage.copy(program)));
            }
        }
    }

    @Override
    public void populateGoal(Game game) {
        this.renderableObjects.add(new Goal(game.getWidth(), false));
    }

    @Override
    public void render(Graphics g) {
        if (activeSubstage == null) {
            // using traditional for loop results in concurrent modification exception
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < renderableObjects.size(); i++) {
                renderableObjects.get(i).render(g);
            }
        } else {
            activeSubstage.render(g);
        }
    }

    @Override
    public void update(final Integer s) {
        if (activeSubstage == null) {
            super.update(s);
        } else {
            activeSubstage.update(s);
        }
    }

    @Override
    public List<RenderableObject> getRenderableObjects() {
        if (activeSubstage == null) {
            return super.renderableObjects;
        } else {
            return activeSubstage.renderableObjects;
        }
    }

    public Level copy() {
        return new Level(this.getId(), this.getSpeed(), this.getWallIDs(), this.getFireballIDs(), this.coordinateToSubstageIdMap);
    }
}
