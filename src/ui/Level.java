package ui;

import libs.RenderableObject;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level extends Stage {
    public Integer activeSubstageId = null;
    Map<Integer, Substage> substages;
    public Level(final int id) {
        setId(id);
        substages = new HashMap<>();
    }

    public void addSubstage(final Substage substage) {
        substages.put(substage.getId(), substage);
    }

    @Override
    public void render(Graphics g) {
        if (activeSubstageId == null) {
            getRenderableObjects().forEach(r -> r.render(g));
        } else {
            substages.get(activeSubstageId).render(g);
        }
    }

    @Override
    public void update(final Integer s) {
        if (activeSubstageId == null) {
//            listOfFireball.updateScheduleFireballToRenderable(getRenderableObjects());
//            getRenderableObjects().forEach(r -> r.update(s));
            super.update(s);
        } else {
            substages.get(activeSubstageId).update(s);
        }
    }

    @Override
    public List<RenderableObject> getRenderableObjects() {
        if (activeSubstageId == null) {
            return super.getRenderableObjects();
        } else {
            return substages.get(activeSubstageId).getRenderableObjects();
        }
    }
}
