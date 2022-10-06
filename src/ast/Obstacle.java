package ast;

import libs.RenderableObject;
import org.jetbrains.annotations.NotNull;
import ui.CollisionVisitor;

public abstract class Obstacle extends RenderableObject {
    @NotNull
    public Integer width;
    @NotNull
    public Integer height;
    abstract public <C,T> T accept(C context, CollisionVisitor<C,T> v); // so that we remember to define this in all subclasses
}
