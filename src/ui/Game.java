package ui;

import ast.Level;
import ast.Program;
import ast.Reward;
import ast.Score;
import enums.Operator;
import libs.RenderableObject;

import java.awt.event.KeyEvent;

import static constants.Constant.GAME_HEIGHT;
import static constants.Constant.REWARD_DISTANCE;

public class Game {
    private Boolean paused;
    private int pauseDelay;
    private int restartDelay;
    private Character character;
    private final Keyboard keyboard;
    public int score;
    public Reward reward;
    public boolean subStageScoreModified;
    public boolean isCleared;
    public Boolean isGameOver;
    public Boolean isGameStarted;
    public int speed;
    public int yPosCharacterReturnsTo;

    public int activeLevelIndex;
    private final CollisionVisitor<Game, Boolean> collisionDetector;
    public final Program program;

    public Game(Program program) {
        this.program = program;
        keyboard = Keyboard.getInstance();
        collisionDetector = new CollisionDetector();
        activeLevelIndex = 1;
        speed = 3;
        reward = program.getGame().getReward();
        restart();
    }

    public void restart() {
        paused = false;
        isGameStarted = false;
        isGameOver = false;
        isCleared = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        character = new Character();

        program.renderAllObjects();
    }

    public void update() {
        watchForStart();

        if (!isGameStarted)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        if (isGameOver)
            return;

        if(isCleared)
            return;

        checkForCollisions();
        speed = getCurrentLevel().activeSubstage == null ?
                getCurrentLevel().getSpeed().getValue()
                : getCurrentLevel().activeSubstage.getSpeed().getValue();
        character.update(speed);
        getCurrentLevel().update(speed);
        updateScore();
    }

    private void watchForStart() {
        if (!isGameStarted && keyboard.isDown(KeyEvent.VK_SPACE)) {
            isGameStarted = true;
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
        }
    }

    private void checkForCollisions() {
        // Ground + Character collision
        for (final RenderableObject r : getCurrentLevel().getRenderableObjects()) {
            if (r instanceof Obstacle) {
                if (((Obstacle) r).accept(this, collisionDetector)) {
                    break;
                }
            }
        }

        int groundBound = GAME_HEIGHT - 38 - character.height;
        int ceilingBound = 5;

        if (character.y > groundBound) {
            isGameOver = true;
            character.y = groundBound;
        }

        if (character.y < ceilingBound) {
            isGameOver = true;
            character.y = ceilingBound;
        }
    }

    public Level getCurrentLevel() {
        return program.getLevels().get(activeLevelIndex);
    }

    public Character getCharacter() {
        return character;
    }

    public void updateScore() {
        if (reward.getCounter() == REWARD_DISTANCE) {
            score += reward.getValueNormalized();
        }

        if (getCurrentLevel().activeSubstage != null && !subStageScoreModified) {
            Score modifyScore = getCurrentLevel().activeSubstage.getScore();
            score = changeScore(modifyScore);
            subStageScoreModified = true;
        }

        if (getCurrentLevel().activeSubstage == null) {
            subStageScoreModified = false;
        }
        reward.update();
    }

    public int changeScore(Score s) {
        Operator operator = s.getOperator();
        int modifyAmount = score;
        switch (operator) {
            case Plus -> modifyAmount += s.getValue();
            case Minus -> modifyAmount -= s.getValue();
            case Divide -> modifyAmount /= s.getValue();
            case Multiply -> modifyAmount *= s.getValue();
        }

        return modifyAmount;
    }
}
