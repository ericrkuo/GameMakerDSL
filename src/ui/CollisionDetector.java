package ui;

import ast.Fireball;
import ast.Wall;

public class CollisionDetector implements CollisionVisitor<Game, Boolean> {
    @Override
    public Boolean visit(Game game, Portal p) {
        return detectCollision(game, p, (boolean didCollide) -> {
            if (didCollide && !p.used) {
                p.used = true;
                game.getCurrentLevel().activeSubstage = p.substageDestination;
                game.yBirdReturnsTo = game.getBird().y;
            }
        });
    }

    @Override
    public Boolean visit(Game game, Wall w) {
        for (final Block b : w.getBlocks()) {
            if (b.accept(game, this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visit(Game game, Fireball f) {
        return detectCollision(game, f, (boolean didCollide) -> game.isGameOver = didCollide);
    }

    @Override
    public Boolean visit(Game game, Block b) {
        return detectCollision(game, b, (boolean didCollide) -> game.isGameOver = didCollide);
    }

    @Override
    public Boolean visit(Game game, Goal g) {
        return detectCollision(game, g, didCollide -> {
            if (didCollide) {
                if (g.isSubstage) {
                    game.getCurrentLevel().activeSubstage = null;
                    game.getBird().y = game.yBirdReturnsTo;
                    game.getBird().update(0);
                } else {
                    game.activeLevelIndex++;
                }
            }
        });
    }

    private boolean detectCollision(Game game, Obstacle obstacle, Callback callback) {
        final Character character = game.getBird();
        final int obstacleTop = obstacle.y;
        final int obstacleBottom = obstacle.y + obstacle.height;
        final int obstacleLeft = obstacle.x;
        final int obstacleRight = obstacle.x + obstacle.width;

        final int birdLeft = character.x;
        final int birdRight = character.x + character.width;
        final int birdTop = character.y;
        final int birdBottom = character.y + character.height;
        int v = (Math.min(obstacleBottom, birdBottom) - Math.max(obstacleTop, birdTop));
        int h = (Math.min(obstacleRight, birdRight) - Math.max(obstacleLeft, birdLeft));
        boolean didCollide = v > 0 && h > 0;
        callback.callback(didCollide);
        return didCollide;
    }

    private interface Callback {
        void callback(boolean didCollide);
    }
}
