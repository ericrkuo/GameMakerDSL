package ui;

import ast.Level;
import ast.Obstacle;
import libs.Renderable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Game {

    public List<Renderable> objectInScreen;
    public List<Level> levels;
    public Integer gravity;
    public Integer birdY;
    public Integer screenWidth;
    public Integer screenHeight;
    public Integer totalScore;

    public Boolean gameover;
    public Boolean started;

    public final Integer birdX = screenWidth/2;
    public final Integer width = 45; //these are set according to the dimensions of the flappy bird sprite
    public final Integer height = 32; //these are set according to the dimensions of the flappy bird sprite



    public Keyboard keyboard;




    public Game() {

    }


    public void update() {

    }


   }