package ui;

import ast.Wall;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {


    private Boolean paused;

    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;

    private Bird bird;
    private ArrayList<Wall> walls;
    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;
    public int speed;

    public Game() {
        keyboard = Keyboard.getInstance();
        restart();
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;
        speed = 3;

        bird = new Bird();
        Wall wall1 = new Wall(200,0,2,3);
        walls = new ArrayList<Wall>();
        walls.add(wall1);
    }

    public void update() {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        bird.update();

        if (gameover)
            return;

        moveWalls();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "assets/background.png"));
        for (Wall wall : walls)
            renders.addAll(wall.getRender());
        renders.add(new Render(0, 0, "assets/foreground.png"));
        renders.add(bird.getRender());
        return renders;
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

    private void moveWalls() {
        for(Wall wall: walls) {
            wall.update(speed);
        }
    }

    private void checkForCollisions() {

        for (Wall wall : walls) {
            if (wall.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameover = true;
                bird.dead = true;
            }
        }

        // Ground + Bird collision
        if (bird.y + bird.height > App.HEIGHT - 80) {
            gameover = true;
            bird.y = App.HEIGHT - 80 - bird.height;
        }
    }
}