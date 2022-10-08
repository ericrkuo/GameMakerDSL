package ui;

import ast.Portal;
import ast.Wall;
import ast.fireball.Fireball;
import ast.fireball.FireballSchedule;
import ast.fireball.RecurringFireball;
import libs.RenderableObject;
import ast.Obstacle;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Boolean paused;
    private int pauseDelay;
    private int restartDelay;
    private Bird bird;
    private Keyboard keyboard;
    public int score;
    public Boolean gameover;
    public Boolean started;
    public int speed;
    public int yBirdReturnsTo;

    public int activeLevelIndex;
    public Map<Integer, Level> levels;
    private CollisionVisitor<Game, Boolean> collisionDetector;
    private static final StringBuilder s = new StringBuilder();

    public Game() {
        keyboard = Keyboard.getInstance();
        collisionDetector = new CollisionDetector();
        levels = new HashMap<>();
        activeLevelIndex = 1;
        speed = 3;
        restart();
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        bird = new Bird();
        Wall wall1 = new Wall(200, 0, 2, 3);
        Fireball fireball1 = new Fireball(500, 200, 2);
        // check substage with `destStageIndex` exists
        Portal portal1 = new Portal(800,300, 1);
        // setup level
        Level level1 = new Level(1);
        levels.put(level1.getId(), level1);
        level1.addRenderableObject(new StaticImage("assets/background.png"));
        level1.addRenderableObject(new StaticImage("assets/foreground.png"));

        level1.addRenderableObject(fireball1);
        level1.addRenderableObject(wall1);
        level1.addRenderableObject(portal1);

        //recur timer is in 'frames',
        RecurringFireball fireball2 = new RecurringFireball(600, 300, 3,50);
        RecurringFireball fireball3 = new RecurringFireball(600, 250, 5,80);
        level1.listOfFireball.addFireballToSchedule(fireball2);
        level1.listOfFireball.addFireballToSchedule(fireball3);
        level1.listOfFireball.fireballSchedule.forEach(r -> level1.getRenderableObjects().add(r));
        // setup substage
        Substage substage1 = new Substage(1);
        substage1.addRenderableObject(new Wall(300, 200, 3, 1));
        substage1.addRenderableObject(new Fireball(500, 100, 2));
        substage1.addRenderableObject(new Goal(800, true));

        level1.addSubstage(substage1);
    }

    public void update() {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        if (gameover)
            return;

        checkForCollisions();
        bird.update(speed);
        getCurrentLevel().update(speed);
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    private void checkForCollisions() {
        // Ground + Bird collision
        for (final RenderableObject r: getCurrentLevel().getRenderableObjects()) {
            if (r instanceof Obstacle) {
                if (((Obstacle) r).accept(this, collisionDetector)) {
                    break;
                }
            }
        }
        if (bird.y + bird.height > App.HEIGHT - 80) {
            gameover = true;
            bird.y = App.HEIGHT - 80 - bird.height;
        }
        // TODO: collision with ceiling
//        if (bird.y + bird.height > App.HEIGHT - 80) {
//            gameover = true;
//            bird.y = App.HEIGHT - 80 - bird.height;
//        }
    }

    public Level getCurrentLevel() {
        return levels.get(activeLevelIndex);
    }

    public Bird getBird() {
        return bird;
    }
}
