package ast;

import libs.RenderableObject;

import java.util.PriorityQueue;

public abstract class Level extends RenderableObject {
    private Integer speed;
    private Integer LevelNumber;
    private Integer length;
    private Integer height;
    private Integer scoreMultiplier;
    private PriorityQueue<Obstacle> objectsOutOfScreen;  //TODO: write compare so it sort by x poisiton

}
