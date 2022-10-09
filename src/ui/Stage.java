package ui;

import temp.fireball.FireballSchedule;
import libs.Renderable;
import libs.RenderableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Stage implements Renderable {

    protected int id;
    public FireballSchedule listOfFireball = new FireballSchedule();

    private List<RenderableObject> renderableObjects = new ArrayList<>();

    @Override
    public void update(final Integer s) {
        getRenderableObjects().forEach(r -> r.update(s));
        listOfFireball.updateScheduleFireballToRenderable(getRenderableObjects());
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.lightGray);
        g.drawImage(Util.loadImage("assets/space-bg.png"), 0, 0, null);
        renderableObjects.forEach(r -> r.render(g));
    }

    public void addRenderableObject(RenderableObject renderableObject) {
        this.renderableObjects.add(renderableObject);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<RenderableObject> getRenderableObjects() {
        return renderableObjects;
    }
}
