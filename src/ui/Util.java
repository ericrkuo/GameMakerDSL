package ui;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public static BufferedImage scaleImage(Integer newWidth, Integer newHeight, BufferedImage oldImage) {
        Image scaledImage = oldImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(
                scaledImage.getWidth(null),
                scaledImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = newImage.createGraphics();
        bGr.drawImage(scaledImage, 0, 0, null);
        bGr.dispose();

        return newImage;
    }
}
