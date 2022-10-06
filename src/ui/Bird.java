package ui;

import libs.Renderer;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird extends Renderer {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;

    private static final Image image = Util.loadImage("assets/bird.png");
    private Keyboard keyboard;

    public Bird() {
        x = 0;
        y = 150;
        width = 45;
        height = 32;
        dead = false;
        keyboard = Keyboard.getInstance();
        img = Util.loadImage("assets/bird.png");
    }

    public void update() {
        if (!dead && keyboard.isDown(KeyEvent.VK_UP)) {
            y -= 10;
        }
        if (!dead && keyboard.isDown(KeyEvent.VK_DOWN)) {
            y +=10;
        }

    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;
        r.image = image;
        r.transform = new AffineTransform();
        r.transform.translate(x , y);

        return r;
    }
