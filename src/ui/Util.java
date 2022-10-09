package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Util {

    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;

        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        try {
            image = ImageIO.read(new File(path));

            if (!cache.containsKey(path)) {
                cache.put(path, image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
