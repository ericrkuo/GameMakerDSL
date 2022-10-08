package ui;

import libs.Renderable;
import libs.RenderableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Stage implements Renderable {

    protected int id;

    private int speed;
    private List<RenderableObject> renderableObjects = new ArrayList<>();

    @Override
    public void update(final Integer s) {
        getRenderableObjects().forEach(r -> r.update(getSpeed()));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.lightGray);
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
