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
                squares[0] = new Square(x, y, size);
                squares[1] = new Square(x + size, y, size);
                squares[2] = new Square(x, y + size, size);
                squares[3] = new Square(x + size, y + size, size);
                break;
            }
            case I: {
                this.color = Color.BLUE;
                squares[0] = new Square(x, y, size);
                squares[1] = new Square(x, y + size, size);
                squares[2] = new Square(x, y + 2 * size, size);
                squares[3] = new Square(x, y + 3 * size, size);
                break;
            }
            case S: {
                this.color = Color.RED;
                squares[0] = new Square(x + size, y, size);
                squares[1] = new Square(x + 2 * size, y, size);
                squares[2] = new Square(x, y + size, size);
                squares[3] = new Square(x, y + 2 * size, size);
                break;
            }
            case Z: {
                this.color = Color.GREEN;
                squares[0] = new Square(x, y, size);
                squares[1] = new Square(x + size, y, size);
                squares[2] = new Square(x + size, y + size, size);
                squares[3] = new Square(x + 2 * size, y + size, size);
                break;
            }
            case L: {
                this.color = Color.ORANGE;
                squares[0] = new Square(x, y, size);
                squares[1] = new Square(x, y + size, size);
                squares[2] = new Square(x, y + 2 * size, size);
                squares[3] = new Square(x + size, y + 3 * size, size);
                break;
            }
            case J: {
                this.color = Color.PINK;
                squares[0] = new Square(x + size, y, size);
                squares[1] = new Square(x + size, y + size, size);
                squares[2] = new Square(x + size, y + 2 * size, size);
                squares[3] = new Square(x, y + 3 * size, size);
                break;
            }
            case T: {
                this.color = Color.MAGENTA;
                squares[0] = new Square(x, y, size);
                squares[1] = new Square(x + size, y, size);
                squares[2] = new Square(x + 2 * size, y, size);
                squares[3] = new Square(x + size, y + size, size);
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

    }
}
