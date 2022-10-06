package ui;

import libs.RenderableObject;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird extends RenderableObject {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;

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

    @Override
    public void update(final int speed) {
        if (!dead && keyboard.isDown(KeyEvent.VK_UP)) {
            y -= 10;
        }
        if (!dead && keyboard.isDown(KeyEvent.VK_DOWN)) {
            y += 10;
        }

        transform = new AffineTransform();
        transform.translate(x, y);
    }
}
