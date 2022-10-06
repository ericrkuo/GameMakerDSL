package ui;

import libs.RenderableObject;

public class StaticImage extends RenderableObject {
    public StaticImage(String img) {
        this.img = Util.loadImage(img);
        x = 0;
        y = 0;
    }

    @Override
    public void update(final int speed) {
    }
}
