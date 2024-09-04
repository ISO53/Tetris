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
        super.paintComponent(g2D);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 50);
    }
}
