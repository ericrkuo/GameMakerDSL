package ui;

import ast.fireball.Fireball;
import ast.Portal;
import ast.Wall;

public class CollisionDetector implements CollisionVisitor<String, Boolean> {
    @Override
    public Boolean visit(String context, Portal p) {
        return null;
    }

    @Override
    public Boolean visit(String context, Wall w) {
        return null;
    }

    @Override
    public Boolean visit(String context, Fireball f) {
        return null;
    }
}