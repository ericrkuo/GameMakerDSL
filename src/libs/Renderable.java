package libs;

import java.awt.*;

//considering to ditch this whole class to just use java.awt Image class
public interface Renderable {

    public static Image render(String imagePath){
        return null;
    }
    //parameter taken from Image function java.awt  Image Class
    public static Image resize(int width, int height, int hints){
       return null;
    }
}
