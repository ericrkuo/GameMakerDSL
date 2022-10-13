package ui;

import ast.Level;
import ast.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static constants.Constant.*;

public class GamePanel extends JPanel implements Runnable {
    private final Game game;
    private final Program program;

    public GamePanel(Program program) {
        this.program = program;
        game = new Game(program);
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderBackground((Graphics2D) g, game.getCurrentLevel());
        game.getCurrentLevel().render(g);
        game.getCharacter().render(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);

        if (!game.isGameStarted) {
            g2D.setFont(new Font(FONT, Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
        } else {
            // draw score
            // right-text alignment http://www.java2s.com/ref/java/java-awt-graphics2d-draw-string-right-alignment.html
            String txt = Integer.toString(game.score);
            Font font = new Font(FONT, Font.PLAIN, 20);
            FontRenderContext fontRenderCtx = g2D.getFontRenderContext();
            Rectangle2D bounds1 = font.getStringBounds(txt, fontRenderCtx);
            g2D.setFont(font);
            int x = (getParent().getWidth() - (int) bounds1.getWidth() - 10);
            g2D.drawString(txt, x, 25);

            // draw current level (and substage if applicable)
            String level = String.format("Level %d", game.getCurrentLevel().getId());
            if (game.getCurrentLevel().activeSubstage != null) {
                level += "-" + game.getCurrentLevel().activeSubstage.getId();
            }

            bounds1 = font.getStringBounds(level, fontRenderCtx);
            x = (getParent().getWidth() - (int) bounds1.getWidth() - 10);
            g2D.drawString(level, x, 50);
        }

        if (game.isGameOver) {
            g2D.setFont(new Font(FONT, Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
        }
        if(game.isCleared){
            renderFinish(g2D);
            g2D.setColor(Color.WHITE);
            Font font = new Font(FONT, Font.PLAIN, 30);
            String txt = "Total Score: " + game.score;
            FontRenderContext fontRenderCtx = g2D.getFontRenderContext();
            Rectangle2D bounds1 = font.getStringBounds(txt, fontRenderCtx);
            g2D.setFont(font);
            int x = getParent().getWidth()/2 - (int) bounds1.getWidth()/2;
            g2D.drawString(txt, x, GAME_HEIGHT-50);
        }
    }

    private void renderBackground(Graphics2D g2D, Level currentLevel) {
        BufferedImage image = Util.loadImage("assets/background.png");
        if (currentLevel.activeSubstage != null) {
            image = Util.loadImage("assets/space-bg.png");
        }
        image = Util.scaleImage(image.getWidth(), GAME_HEIGHT, image);

        int tileWidth = image.getWidth();
        int tileHeight = image.getHeight();
        for (int y = 0; y < GAME_HEIGHT; y += tileHeight) {
            for (int x = 0; x < program.getGame().getWidth(); x += tileWidth) {
                g2D.drawImage(image, x, y, this);
            }
        }
    }

    private void renderFinish(Graphics2D g2D) {
        BufferedImage image = Util.loadImage("assets/cleared.png");
        image = Util.scaleImage(GAME_WIDTH, GAME_HEIGHT+115, image);
        g2D.drawImage(image, 0, 0, this);
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
