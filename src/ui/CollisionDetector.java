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
                game.yPosCharacterReturnsTo = game.getCharacter().y;
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
                    game.getCharacter().y = game.yPosCharacterReturnsTo;
                    game.getCharacter().update(0);
                } else {
                    game.activeLevelIndex++;
                }
            }
        });
    }

    private boolean detectCollision(Game game, Obstacle obstacle, Callback callback) {
        final Character character = game.getCharacter();
        final int obstacleTop = obstacle.y;
        final int obstacleBottom = obstacle.y + obstacle.height;
        final int obstacleLeft = obstacle.x;
        final int obstacleRight = obstacle.x + obstacle.width;

        final int characterLeft = character.x;
        final int characterRight = character.x + character.width;
        final int characterTop = character.y;
        final int characterBottom = character.y + character.height;
        int v = (Math.min(obstacleBottom, characterBottom) - Math.max(obstacleTop, characterTop));
        int h = (Math.min(obstacleRight, characterRight) - Math.max(obstacleLeft, characterLeft));
        boolean didCollide = v > 0 && h > 0;
        callback.callback(didCollide);
        return didCollide;
    }

    private interface Callback {
        void callback(boolean didCollide);
    }
}
