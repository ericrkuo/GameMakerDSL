package libs;

import java.awt.*;

public interface Renderable {
    void update(final Integer speed);
    void render(Graphics g);
}
