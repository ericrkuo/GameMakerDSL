package ast;

import libs.RenderableObject;
import ui.CollisionVisitor;

public abstract class Obstacle extends RenderableObject {
    abstract public <C,T> T accept(C context, CollisionVisitor<C,T> v); // so that we remember to define this in all subclasses
}
