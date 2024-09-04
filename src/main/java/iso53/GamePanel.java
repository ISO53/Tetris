package iso53;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    private final Timer timer;
    private final Random random;
    private Graphics2D g2D;

    private double speed;
    private int column;
    private int row;
    private Square[][] area;

    public GamePanel() {
        this.timer = new Timer();
        this.random = new Random();
        this.column = 10;
        this.row = 30;
        this.g2D = (Graphics2D) getGraphics();
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
