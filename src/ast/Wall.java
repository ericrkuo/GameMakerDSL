package ast;

import ui.Render;
import ui.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    private int height;
    public int width;
    public int x;
    private int y;
    private String orientation; // dont need to be parsed, can be figured out from y position;
    private static final Image image = Util.loadImage("assets/wall.png"); // need to change later


    public Wall(Integer posX, Integer posY, Integer wallHeight, Integer wallWidth) {
        x = posX;
        y= posY;
        height = wallHeight;
        width = wallWidth;
    }

    public void update(int speed) {
        x -= speed;
    }
    public Render renderOne(int xCor, int yCor) {
        Render r = new Render();
        r.x = xCor;
        r.y = yCor;
        r.image = image;

        return r;
    }

    public List<Render> getRender(){
        List<Render> renderList = new ArrayList<>();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                renderList.add(renderOne(x+(50*i), y+(50*j)));
            }
        }
        return renderList;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {

       return false;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }
}
