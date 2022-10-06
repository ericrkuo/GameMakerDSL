package ui;

import ast.fireball.Fireball;
import ast.Portal;
import ast.Wall;

public class CollisionDetector implements CollisionVisitor<Bird, Boolean> {
    @Override
    public Boolean visit(Bird bird, Portal p) {
        return false;
    }

    @Override
    public Boolean visit(Bird bird, Wall w) {
        for (final Block b: w.blocks) {
            if (b.accept(bird, this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visit(Bird bird, Fireball f) {
        return false;
    }

    @Override
    public Boolean visit(Bird bird, Block b) {
        final int blockTop = b.y;
        final int blockBottom = b.y;
        final int birdTop = bird.y;
        final int birdBottom = bird.y;
        final int blockLeft = b.x;
        final int blockRight = b.x + b.width;
        final int birdLeft = bird.x;
        final int birdRight = bird.x + bird.width;
        int v = (Math.min(blockBottom, birdBottom) - Math.max(blockTop, birdTop));
        int h = (Math.min(blockRight, birdRight) - Math.max(blockLeft, birdLeft));
        return v > 0 && h > 0;
    }
}