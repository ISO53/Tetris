package iso53;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private JPanel jPanelBackPanel;
    private GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("Tetris");
        frame.setContentPane(jPanelBackPanel);
        frame.setMinimumSize(new Dimension(545, 570));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Set application icon
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/logo.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    private void addKeyListeners() {
        jPanelBackPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_N: { // New Game
                        gamePanel = new GamePanel();
                        break;
                    }
                    case KeyEvent.VK_P: { // Pause
                        break;
                    }
                }
            }
        });
    }

}
