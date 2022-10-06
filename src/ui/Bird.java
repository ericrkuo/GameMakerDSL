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

    public double yvel;


    private double rotation;

    private Keyboard keyboard;

    public Bird() {
        x = 100;
        y = 150;
        yvel = 0;
        width = 45;
        height = 32;
        dead = false;

        keyboard = Keyboard.getInstance();
        img = Util.loadImage("assets/bird.png");
    }

    public void update() {
        if (!dead && keyboard.isDown(KeyEvent.VK_UP)) {
            yvel = -10;
        }

        if (!dead && keyboard.isDown(KeyEvent.VK_DOWN)) {
            yvel = +10;
        }

        y += (int)yvel;

        rotation = (90 * (yvel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        this.transform = new AffineTransform();
        this.transform.translate(x + width / 2, y + height / 2);
        this.transform.rotate(rotation);
        this.transform.translate(-width / 2, -height / 2);
    }
}