package ui;

import libs.RenderableObject;

public abstract class Obstacle extends RenderableObject {
    public Integer width;
    public Integer height;

    abstract public <C, T> T accept(C context, CollisionVisitor<C, T> v); // so that we remember to define this in all subclasses
}
