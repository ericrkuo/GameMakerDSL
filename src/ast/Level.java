package ast;

import libs.Renderable;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Level implements Renderable {
    private Integer speed;
    private Integer LevelNumber;
    private Integer length;
    private Integer height;
    private Integer scoreMultiplier;
    private PriorityQueue<Obstacle> objectsOutOfScreen;  //TODO: write compare so it sort by x poisiton

}
