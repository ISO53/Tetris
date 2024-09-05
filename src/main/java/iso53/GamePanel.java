package iso53;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    private enum Direction {
        DOWN, LEFT, RIGHT
    }

    private final Timer timer;
    private final Random random;
    private final int column;
    private final int row;
    private final int infoAreaColumn;
    private final Square[][] area;

    private double speed;
    private int squareSize; // px
    private Piece current;
    private Piece next;

    public GamePanel() {
        this.timer = new Timer();
        this.random = new Random();
        this.column = 10;
        this.row = 20;
        this.infoAreaColumn = 8;
        this.area = new Square[column][row];

        this.squareSize = 25;
        this.current = Piece.random(5, 0, squareSize);
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

        // Current piece that's falling down
        paintCurrentPiece(g2D);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                movePiece(Direction.DOWN);
                SwingUtilities.invokeLater(() -> repaint());
            }
        }, 0, 500);
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
            new Square((this.column + this.infoAreaColumn + 2) * squareSize, i * squareSize, squareSize)
                    .paint(g2D, Color.GRAY);
        }
    }

    private void paintCurrentPiece(Graphics2D g2D) {
        for (Square square : current.getSquares()) {
            square.paint(g2D, current.getColor());
        }
    }
    private void movePiece(Direction direction) {
        switch (direction) {
            case DOWN: {
                // Check if there is any intersection when we move the current piece down one square.
                for (Square pieceSquare : current.getSquares()) {

                    // Out of bounds check
                    if (area.length <= (pieceSquare.x / squareSize) || area[0].length <= pieceSquare.y / squareSize) {
                        return;
                    }

                    // Check if below is empty
                    if (area[pieceSquare.x / squareSize][pieceSquare.y / squareSize] != null) {
                        // TODO piece stopped
                        return;
                    }
                }

                // If not, move the piece
                for (Square square : current.getSquares()) {
                    square.y += squareSize;
                }
                break;
            }
        }
    }
}
