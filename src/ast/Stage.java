package ast;

import enums.TriggerFlavour;
import libs.Renderable;
import libs.RenderableObject;
import service.FireballSchedulerService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A stage can either be a level or substage. Each stage has an id, speed, list of ids of walls and fireballs.
 */
public abstract class Stage extends Node implements Renderable {
    private final Integer id;
    private final Speed speed;
    private final List<Integer> wallIDs;
    private final List<Integer> fireballIDs;
    private final FireballSchedulerService fireballSchedulerService = new FireballSchedulerService();
    protected final List<RenderableObject> renderableObjects = new ArrayList<>();

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

    public void populateObjects(Program program) {
        this.renderableObjects.clear();
        populateWalls(program.getWalls());
        populateFireballs(program.getFireballs());
        populateSubstages(program);
        populateGoal(program.getGame());
    }

    private void populateWalls(Map<Integer, Wall> walls) {
        for (Integer wallId : this.wallIDs) {
            Wall wall = walls.get(wallId);
            if (wall != null) {
                renderableObjects.add(wall.copy());
            }
        }
    }

    private void populateFireballs(Map<Integer, Fireball> fireballs) {
        for (Integer fireballId : this.fireballIDs) {
            Fireball fireball = fireballs.get(fireballId);
            if (fireball == null) {
                continue;
            }
            if (fireball.getTrigger().getTriggerFlavour() == TriggerFlavour.Loop) {
                fireballSchedulerService.addFireballToSchedule(fireball.copy());
            } else {
                renderableObjects.add(fireball.copy());
            }
        }
        renderableObjects.addAll(fireballSchedulerService.fireballSchedule);
    }

    // Overridable by subclasses
    public void populateSubstages(Program program) {
    }

    public abstract void populateGoal(Game game);

    @Override
    public void update(final Integer s) {
        renderableObjects.forEach(r -> r.update(s));
        fireballSchedulerService.updateFireballSchedules(renderableObjects);
    }

    @Override
    public void render(Graphics g) {
        // using traditional for loop results in concurrent modification exception
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < renderableObjects.size(); i++) {
            renderableObjects.get(i).render(g);
        }
    }

    public List<RenderableObject> getRenderableObjects() {
        return renderableObjects;
    }
}
