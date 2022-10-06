package ast;

import libs.Renderer;
import ui.CollisionVisitor;

public abstract class Obstacle extends Renderer {
    abstract public <C,T> T accept(C context, CollisionVisitor<C,T> v); // so that we remember to define this in all subclasses
}
