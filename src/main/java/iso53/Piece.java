package iso53;

import java.awt.*;
import java.util.Random;

public class Piece {

    private final Square[] squares;
    private final PieceType type;
    private final Color color;
    private final int size; // Size of the square, not the whole piece

    // Location of the piece (top left)
    private int x, y;

    public Piece(int x, int y, int size, PieceType type) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;

        // Each piece consists 4 squares, the locations of these squares varies based on type
        this.squares = new Square[4];

        switch (type) {
            case O: {
                this.color = Color.YELLOW;
                squares[0] = new Square(x * size, y * size, size, Color.YELLOW);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.YELLOW);
                squares[2] = new Square(x * size, (y + 1) * size, size, Color.YELLOW);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.YELLOW);
                break;
            }
            case I: {
                this.color = Color.BLUE;
                squares[0] = new Square(x * size, y * size, size, Color.BLUE);
                squares[1] = new Square(x * size, (y + 1) * size, size, Color.BLUE);
                squares[2] = new Square(x * size, (y + 2) * size, size, Color.BLUE);
                squares[3] = new Square(x * size, (y + 3) * size, size, Color.BLUE);
                break;
            }
            case S: {
                this.color = Color.RED;
                squares[0] = new Square((x + 1) * size, y * size, size, Color.RED);
                squares[1] = new Square((x + 2) * size, y * size, size, Color.RED);
                squares[2] = new Square(x * size, (y + 1) * size, size, Color.RED);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.RED);
                break;
            }
            case Z: {
                this.color = Color.GREEN;
                squares[0] = new Square(x * size, y * size, size, Color.GREEN);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.GREEN);
                squares[2] = new Square((x + 1) * size, (y + 1) * size, size, Color.GREEN);
                squares[3] = new Square((x + 2) * size, (y + 1) * size, size, Color.GREEN);
                break;
            }
            case L: {
                this.color = Color.ORANGE;
                squares[0] = new Square(x * size, y * size, size, Color.ORANGE);
                squares[1] = new Square(x * size, (y + 1) * size, size, Color.ORANGE);
                squares[2] = new Square(x * size, (y + 2) * size, size, Color.ORANGE);
                squares[3] = new Square((x + 1) * size, (y + 2) * size, size, Color.ORANGE);
                break;
            }
            case J: {
                this.color = Color.PINK;
                squares[0] = new Square((x + 1) * size, y * size, size, Color.PINK);
                squares[1] = new Square((x + 1) * size, (y + 1) * size, size, Color.PINK);
                squares[2] = new Square((x + 1) * size, (y + 2) * size, size, Color.PINK);
                squares[3] = new Square(x * size, (y + 2) * size, size, Color.PINK);
                break;
            }
            case T: {
                this.color = Color.MAGENTA;
                squares[0] = new Square(x * size, y * size, size, Color.MAGENTA);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.MAGENTA);
                squares[2] = new Square((x + 2) * size, y * size, size, Color.MAGENTA);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.MAGENTA);
                break;
            }
            default: {
                this.color = Color.BLACK;
                // This is impossible but if I don't add this compiler gets angry (color variable is final)
            }
        }
    }

    public static Piece random(int x, int y, int size) {
        return new Piece(x, y, size, PieceType.values()[new Random().nextInt(PieceType.values().length)]);
    }

    public Square[] getSquares() {
        return squares;
    }

    public Color getColor() {
        return color;
    }
}
