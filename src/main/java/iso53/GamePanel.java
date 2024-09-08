package iso53;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    private final Timer timer;
    private final int column;
    private final int row;
    private final int squareSize; // px
    private final int infoAreaColumn;
    private final Square[][] area;
    private final Instant startTime;

    private Piece current;
    private Piece next;
    private int score;
    private KeyListener keyListener;

    public GamePanel() {
        this.timer = new Timer();
        this.column = 10;
        this.row = 20;
        this.squareSize = 25;
        this.infoAreaColumn = 8;
        this.area = new Square[column][row];
        this.startTime = Instant.now();

        this.current = Piece.random(column / 2, 1, squareSize);
        this.next = Piece.random(column + infoAreaColumn / 2 + 1, 4, squareSize);
        this.score = 0;
        this.keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_S: {
                        movePiece(Direction.DOWN);
                        break;
                    }
                    case KeyEvent.VK_A: {
                        movePiece(Direction.LEFT);
                        break;
                    }
                    case KeyEvent.VK_D: {
                        movePiece(Direction.RIGHT);
                        break;
                    }
                }
                SwingUtilities.invokeLater(() -> repaint());
            }
        };
        addKeyListener(keyListener);
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

        // Next piece
        paintNextPiece(g2D);

        // Paint the remaining squares
        paintSquares(g2D);

        // Paint the labels like 'NEXT PIECE' etc.
        paintLabels(g2D);
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
            new Square(i * squareSize, 0, squareSize, Color.GRAY).paint(g2D);
            new Square(i * squareSize, (row + 1) * squareSize, squareSize, Color.GRAY).paint(g2D);
        }

        // Draw left, middle and right frame
        for (int i = 0; i < heightCol; i++) {
            new Square(0, i * squareSize, squareSize, Color.GRAY).paint(g2D);
            new Square((this.column + 1) * squareSize, i * squareSize, squareSize, Color.GRAY).paint(g2D);
            new Square((this.column + this.infoAreaColumn + 2) * squareSize, i * squareSize, squareSize, Color.GRAY)
                    .paint(g2D);
        }

        // Draw a frame after 'next piece'
        for (int i = column; i < column + infoAreaColumn; i++) {
            new Square((i + 2) * squareSize, 9 * squareSize, squareSize, Color.GRAY).paint(g2D);
        }
    }

    private void paintCurrentPiece(Graphics2D g2D) {
        for (Square square : current.getSquares()) {
            square.paint(g2D);
        }
    }

    private void paintNextPiece(Graphics2D g2D) {
        for (Square square : next.getSquares()) {
            square.paint(g2D);
        }
    }

    private void paintSquares(Graphics2D g2D) {
        for (Square[] squares : area) {
            for (Square square : squares) {
                if (square != null) {
                    square.paint(g2D);
                }
            }
        }
    }

    private void paintLabels(Graphics2D g2D) {
        // Set custom font
        Font gameFont = new Font("Monospaced", Font.BOLD, 24);
        g2D.setFont(gameFont);
        g2D.setColor(Color.WHITE);
        FontMetrics metrics = g2D.getFontMetrics(gameFont);

        // Draw the 'NEXT PIECE' label
        String label = "NEXT PIECE";
        int x = (column + infoAreaColumn / 2 + 2) * squareSize - metrics.stringWidth(label) / 2;
        int y = squareSize * 3; // Position above the next piece
        g2D.drawString(label, x, y);

        // Draw the 'SCORE' label
        label = "SCORE";
        x = (column + 3) * squareSize;
        y = squareSize * 12;
        g2D.drawString(label, x, y);

        // Draw the actual score
        label = String.valueOf(score);
        x = (column + 3) * squareSize;
        y = squareSize * 13;
        g2D.setColor(Color.CYAN);
        g2D.drawString(label, x, y);

        // Draw the 'TIME' label
        label = "TIME";
        x = (column + 3) * squareSize;
        y = squareSize * 15;
        g2D.setColor(Color.WHITE);
        g2D.drawString(label, x, y);

        // Draw the actual time
        label = getElapsedTime();
        x = (column + 3) * squareSize;
        y = squareSize * 16;
        g2D.setColor(Color.CYAN);
        g2D.drawString(label, x, y);
    }

    private void movePiece(Direction direction) {
        switch (direction) {
            case DOWN: {
                // Check if there is any intersection when we move the current piece down one square.
                boolean pieceBelow = false;
                for (Square pieceSquare : current.getSquares()) {

                    // If the below square is the game frame the piece is at the bottom
                    pieceBelow = area[0].length == pieceSquare.y / squareSize;

                    if (pieceBelow) {
                        break;
                    }

                    // Check if below is empty or not
                    if (area[pieceSquare.x / squareSize - 1][pieceSquare.y / squareSize] != null) {
                        pieceBelow = true;
                        break;
                    }
                }

                // If the piece is at the bottom render it to the 'area' and send the next piece
                if (pieceBelow) {
                    // Save the piece's squares to the area to be rendered
                    for (Square square : current.getSquares()) {
                        area[square.x / squareSize - 1][square.y / squareSize - 1] = square;
                    }

                    // Start sending the other piece
                    current = new Piece(column / 2, 1, squareSize, next.getType());
                    next = Piece.random(column + infoAreaColumn / 2 + 1, 4, squareSize);
                    return;
                }

                // If not, move the piece
                current.move(Direction.DOWN, squareSize);

                break;
            }
            case LEFT: {
                // Check if there is any intersection when we move the current piece left one square.
                for (Square pieceSquare : current.getSquares()) {

                    // Flag for if the piece is at the far left
                    boolean isAtFarLeft = pieceSquare.x / squareSize <= 1;

                    // Check if there is a piece on the left side
                    if (isAtFarLeft || area[pieceSquare.x / squareSize - 2][pieceSquare.y / squareSize - 1] != null) {
                        return;
                    }
                }

                // If not, move the piece
                current.move(Direction.LEFT, squareSize);
                break;
            }
            case RIGHT: {
                // Check if there is any intersection when we move the current piece right one square.
                for (Square pieceSquare : current.getSquares()) {

                    // Flag for if the piece is at the far right
                    boolean isAtFarRight = pieceSquare.x / squareSize >= column;

                    // Check if there is a piece on the right side
                    if (isAtFarRight || area[pieceSquare.x / squareSize][pieceSquare.y / squareSize - 1] != null) {
                        return;
                    }
                }

                // If not, move the piece
                current.move(Direction.RIGHT, squareSize);
                break;
            }
        }

        // After moving the piece check if there are full rows
        checkAndClearFullRows();
    }

    private void checkAndClearFullRows() {
        for (int y = 0; y < row; y++) {
            boolean isFull = true;
            for (int x = 0; x < column; x++) {
                if (area[x][y] == null) {
                    isFull = false;
                    break;
                }
            }
            if (isFull) {
                clearRow(y);
                score += 100;
            }
        }
    }

    private void clearRow(int rowIndex) {
        // Move all rows above the cleared row one cell down
        for (int y = rowIndex; y > 0; y--) {
            for (int x = 0; x < column; x++) {
                area[x][y] = area[x][y - 1];
                if (area[x][y] != null) {
                    area[x][y].x = (x + 1) * squareSize;
                    area[x][y].y = (y + 1) * squareSize;
                    // this is stupid, the squares should be stored in a different data structure
                }
                area[x][y - 1] = null;
            }
        }
    }

                }
                SwingUtilities.invokeLater(() -> repaint());
            }
        });
    private void removeKeyListeners() {
        this.removeKeyListener(keyListener);
    }

    private String getElapsedTime() {
        Duration elapsed = Duration.between(startTime, Instant.now());
        long seconds = elapsed.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
