package ui;

import libs.Renderer;

public class StaticImage extends Renderer {
    public StaticImage(String img) {
        this.img = Util.loadImage(img);
    }
}
