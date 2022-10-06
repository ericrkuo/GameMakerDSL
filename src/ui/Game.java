package ui;

import ast.Portal;
import ast.Wall;
import ast.fireball.DefaultFireball;
import ast.fireball.Fireball;
import libs.RenderableObject;
import ast.Obstacle;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Boolean paused;
    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    private Bird bird;
    private Keyboard keyboard;
    public int score;
    public Boolean gameover;
    public Boolean started;
    public int speed;
    private List<RenderableObject> renderableList;
    private CollisionVisitor<Bird, Boolean> collisionDetector;
    private static final StringBuilder s = new StringBuilder();

    public Game() {
        keyboard = Keyboard.getInstance();
        collisionDetector = new CollisionDetector();
        restart();
    }

    public void restart() {
        renderableList = new ArrayList<>();
        renderableList.add(new StaticImage("assets/background.png"));
        renderableList.add(new StaticImage("assets/foreground.png"));

        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;
        speed = 3;

        bird = new Bird();
        Wall wall1 = new Wall(200, 0, 2, 3);
        Fireball fireball1 = new DefaultFireball(500, 200, 4);
        Portal portal1 = new Portal(800,300);
        renderableList.add(fireball1);
        renderableList.add(bird);
        renderableList.add(wall1);
        renderableList.add(portal1);
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
        renderableList.forEach(r -> r.update(speed));
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
        for (final RenderableObject r: renderableList) {
            if (r instanceof Obstacle) {
                if (((Obstacle) r).accept(bird, collisionDetector)) {
                    gameover = true;
                    break;
                }
            }
        }
        if (bird.y + bird.height > App.HEIGHT - 80) {
            gameover = true;
            bird.y = App.HEIGHT - 80 - bird.height;
        }
    }

    public List<RenderableObject> getRenderableList() {
        return renderableList;
    }
}
