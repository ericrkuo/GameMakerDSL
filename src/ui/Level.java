package ui;

import java.awt.*;
import java.util.HashMap;
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
            final Substage substage = substages.get(activeSubstageId);
            substage.getRenderableObjects().forEach(r -> r.render(g));
        }
    }

    @Override
    public void update(final Integer s) {
        if (activeSubstageId == null) {
            getRenderableObjects().forEach(r -> r.update(getSpeed()));
        } else {
            substages.get(activeSubstageId).update(null);
        }
    }
}
