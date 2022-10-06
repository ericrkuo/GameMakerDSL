package libs;

import java.awt.*;
import java.awt.geom.AffineTransform;

//considering to ditch this whole class to just use java.awt Image class
public abstract class Renderer {
    public int x;
    public int y;
    public static Image img;
    public AffineTransform transform;
}
