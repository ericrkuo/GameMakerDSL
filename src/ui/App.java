package ui;

import ast.Program;
import javax.swing.*;
import static constants.Constant.*;

public class App {
    public static void createGame(Program program) {
        System.out.println("Launching game...");
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel(program);
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        // https://stackoverflow.com/a/16327279
        frame.setLocationRelativeTo(null);
    }
}
