package iso53;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    private final Timer timer;
    private final Random random;

    private double speed;
    private int column;
    private int row;
    private int squareSize; // px
    private int infoAreaColumn;
    private Square[][] area;

    public GamePanel() {
        this.timer = new Timer();
        this.random = new Random();
        this.column = 10;
        this.row = 20;
        this.squareSize = 25;
        this.infoAreaColumn = 8;
        this.area = new Square[column][row];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        // Some rendering hints for smooth rendering
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // This is the outer game frame (The gray one)
        paintGameFrame(g2D);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 50);
    }
    private void paintGameFrame(Graphics2D g2D) {
        int widthCol = this.column + this.infoAreaColumn + 3; // plus 3 for frame
        int heightCol = this.row + 2; // plus 2 for frame

        // Draw top and bottom frame
        for (int i = 0; i < widthCol; i++) {
            new Square(i * squareSize, 0, squareSize).paint(g2D, Color.GRAY);
            new Square(i * squareSize, (row + 1) * squareSize, squareSize).paint(g2D, Color.GRAY);
        }

        // Draw left, middle and right frame
        for (int i = 0; i < heightCol; i++) {
            new Square(0, i * squareSize, squareSize).paint(g2D, Color.GRAY);
            new Square((this.column + 1) * squareSize, i * squareSize, squareSize).paint(g2D, Color.GRAY);
            new Square((this.column + this.infoAreaColumn + 2) * squareSize, i * squareSize, squareSize).paint(g2D, Color.GRAY);
        }
    }
}
