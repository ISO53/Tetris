package iso53;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    private JPanel jPanelBackPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("Tetris");
        frame.setContentPane(jPanelBackPanel);
        frame.setMinimumSize(new Dimension(400, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Set application icon
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/logo.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GamePanel gp = new GamePanel();
        jPanelBackPanel.add(gp);

        frame.pack();
        frame.setVisible(true);
    }
}
