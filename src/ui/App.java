package ui;

import java.io.IOException;
import javax.swing.*;

public class App {
    public static int WIDTH = 800;
    public static int HEIGHT = 520;
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }
}
