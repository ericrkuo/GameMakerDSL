package ui;

import libs.RenderableObject;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import static constants.Constant.*;

public class Character extends RenderableObject {

    public int x;
    public int y;
    public int width;
    public int height;
    public boolean dead;
    private final Keyboard keyboard;

    public Character() {
        x = 10;
        y = GAME_HEIGHT/2 - 50;
        width = 45;
        height = 32;
        dead = false;
        keyboard = Keyboard.getInstance();
        // TODO change this image
        img = Util.loadImage("assets/bird.png");
    }

    @Override
    public void update(final Integer speed) {
        if (!dead && keyboard.isDown(KeyEvent.VK_UP)) {
            y -= CHARACTER_SPEED;
        }
        if (!dead && keyboard.isDown(KeyEvent.VK_DOWN)) {
            y += CHARACTER_SPEED;
        }

        transform = new AffineTransform();
        transform.translate(x, y);
    }
}
