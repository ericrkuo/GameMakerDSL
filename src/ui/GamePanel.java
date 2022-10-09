package ui;

import ast.Level;
import ast.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static constants.Constant.GAME_HEIGHT;

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
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString(Integer.toString(game.score), 10, 465);
        }

        if (game.isGameOver) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
        }
    }

    private void renderBackground(Graphics2D g2D, Level currentLevel) {
        BufferedImage image = Util.loadImage("assets/background.png");
        if (currentLevel.activeSubstage != null) {
            image = Util.loadImage("assets/space-bg.png");
        }

        Image img = image.getScaledInstance(image.getWidth(), GAME_HEIGHT, Image.SCALE_SMOOTH);
        image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = image.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        int tileWidth = image.getWidth();
        int tileHeight = image.getHeight();
        for (int y = 0; y < GAME_HEIGHT; y += tileHeight) {
            for (int x = 0; x < program.getGame().getWidth(); x += tileWidth) {
                g2D.drawImage(image, x, y, this);
            }
        }
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
