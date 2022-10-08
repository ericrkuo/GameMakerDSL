package ui;

import ast.Portal;
import ast.Wall;
import ast.fireball.Fireball;
import ast.fireball.FireballSchedule;
import ast.fireball.RecurringFireball;
import libs.RenderableObject;
import ast.Obstacle;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
    private List<RenderableObject> renderableList;
    private FireballSchedule listOfFireball;
    private CollisionVisitor<Game, Boolean> collisionDetector;
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
        speed = 3;

        bird = new Bird();
        listOfFireball = new FireballSchedule();
        Wall wall1 = new Wall(200, 0, 2, 3);
        Fireball fireball1 = new Fireball(500, 200, 2);
        Portal portal1 = new Portal(800,300);
        //recur timer is in 'frames',
        RecurringFireball fireball2 = new RecurringFireball(600, 300, 3,50);
        RecurringFireball fireball3 = new RecurringFireball(600, 250, 5,80);
        listOfFireball.addFireballToSchedule(fireball2);
        listOfFireball.addFireballToSchedule(fireball3);
        listOfFireball.fireballSchedule.forEach(r -> renderableList.add(r));
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
        listOfFireball.updateScheduleFireballToRenderable(renderableList);
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

    public List<RenderableObject> getRenderableList() {
        return renderableList;
    }

    public Bird getBird() {
        return bird;
    }
}
