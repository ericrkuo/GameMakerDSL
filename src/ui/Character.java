package ui;

import libs.RenderableObject;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import static constants.Constant.*;

public class Character extends RenderableObject {
    public int width;
    public int height;
    public boolean dead;
    private final Keyboard keyboard;

    public Character() {
        // TIP: for any character asset png, make sure to use Paint3D to crop to specified size
        img = Util.loadImage("assets/character.png");
        width = img.getWidth();
        height = img.getHeight();
        x = width;
        y = GAME_HEIGHT/2 - height;
        dead = false;
        keyboard = Keyboard.getInstance();

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
