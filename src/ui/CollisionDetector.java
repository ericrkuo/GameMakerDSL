package ui;

import temp.Obstacle;
import temp.fireball.Fireball;
import temp.Portal;
import temp.Wall;

public class CollisionDetector implements CollisionVisitor<Game, Boolean> {
    @Override
    public Boolean visit(Game game, Portal p) {
        return detectCollision(game, p, (boolean didCollide) -> {
            if (didCollide && !p.used) {
                p.used = true;
                game.getCurrentLevel().activeSubstageId = p.destStageIndex;
                game.yBirdReturnsTo = game.getBird().y;
            }
        });
    }

    @Override
    public Boolean visit(Game game, Wall w) {
//        return detectCollision(game, w, (boolean didCollide) -> {
//            game.gameover = true;
//        });
        for (final Block b: w.blocks) {
            if (b.accept(game, this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visit(Game game, Fireball f) {
        return detectCollision(game, f, (boolean didCollide) -> {
            game.gameover = didCollide;
        });
    }

    @Override
    public Boolean visit(Game game, Block b) {
        return detectCollision(game, b, (boolean didCollide) -> {
            game.gameover = didCollide;
        });
    }

    @Override
    public Boolean visit(Game game, Goal g) {
        return detectCollision(game, g, didCollide -> {
            if (didCollide) {
                if (g.isSubstage) {
                    game.getCurrentLevel().activeSubstageId = null;
                    game.getBird().y = game.yBirdReturnsTo;
                    game.getBird().update(null);
                } else {
                    game.activeLevelIndex++;
                }
            }
        });
    }

    private boolean detectCollision(Game game, Obstacle obstacle, Callback callback) {
        final Bird bird = game.getBird();
        final int obstacleTop = obstacle.y;
        final int obstacleBottom = obstacle.y + obstacle.height;
        final int obstacleLeft = obstacle.x;
        final int obstacleRight = obstacle.x + obstacle.width;

        final int birdLeft = bird.x;
        final int birdRight = bird.x + bird.width;
        final int birdTop = bird.y;
        final int birdBottom = bird.y + bird.height;
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
