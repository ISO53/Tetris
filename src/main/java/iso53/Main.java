package iso53;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private final GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(560, 610));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Set application icon
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/logo.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create and set up the content pane
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setBackground(Color.BLACK);
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(backPanel);

        // Create and set up the game panel
        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLACK);
        backPanel.add(gamePanel);

        frame.pack();
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                gamePanel.start();
            }
        }, 1000);
    }
}