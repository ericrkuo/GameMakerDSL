package ui;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;

    public double yvel;


    private double rotation;

    private Image image;
    private Keyboard keyboard;

    public Bird() {
        x = 100;
        y = 150;
        yvel = 0;
        width = 45;
        height = 32;
        dead = false;

        keyboard = Keyboard.getInstance();
    }

    public void update() {




        if (!dead && keyboard.isDown(KeyEvent.VK_UP)) {
            yvel = -10;
        }

        if (!dead && keyboard.isDown(KeyEvent.VK_DOWN)) {
            yvel = +10;
        }

        y += (int)yvel;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("assets/bird.png");
        }
        r.image = image;

        rotation = (90 * (yvel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
    }
}