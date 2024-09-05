package iso53;

import java.awt.*;
import java.util.Random;

public class Piece {

    private final Square[] squares;
    private final PieceType type;

    public Piece(int x, int y, int size, PieceType type) {
        // Size of the square, not the whole piece
        this.type = type;

        // Each piece consists 4 squares, the locations of these squares varies based on type
        this.squares = new Square[4];

        switch (type) {
            case O: {
                squares[0] = new Square(x * size, y * size, size, Color.YELLOW);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.YELLOW);
                squares[2] = new Square(x * size, (y + 1) * size, size, Color.YELLOW);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.YELLOW);
                break;
            }
            case I: {
                squares[0] = new Square(x * size, y * size, size, Color.BLUE);
                squares[1] = new Square(x * size, (y + 1) * size, size, Color.BLUE);
                squares[2] = new Square(x * size, (y + 2) * size, size, Color.BLUE);
                squares[3] = new Square(x * size, (y + 3) * size, size, Color.BLUE);
                break;
            }
            case S: {
                squares[0] = new Square((x + 1) * size, y * size, size, Color.RED);
                squares[1] = new Square((x + 2) * size, y * size, size, Color.RED);
                squares[2] = new Square(x * size, (y + 1) * size, size, Color.RED);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.RED);
                break;
            }
            case Z: {
                squares[0] = new Square(x * size, y * size, size, Color.GREEN);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.GREEN);
                squares[2] = new Square((x + 1) * size, (y + 1) * size, size, Color.GREEN);
                squares[3] = new Square((x + 2) * size, (y + 1) * size, size, Color.GREEN);
                break;
            }
            case L: {
                squares[0] = new Square(x * size, y * size, size, Color.ORANGE);
                squares[1] = new Square(x * size, (y + 1) * size, size, Color.ORANGE);
                squares[2] = new Square(x * size, (y + 2) * size, size, Color.ORANGE);
                squares[3] = new Square((x + 1) * size, (y + 2) * size, size, Color.ORANGE);
                break;
            }
            case J: {
                squares[0] = new Square((x + 1) * size, y * size, size, Color.PINK);
                squares[1] = new Square((x + 1) * size, (y + 1) * size, size, Color.PINK);
                squares[2] = new Square((x + 1) * size, (y + 2) * size, size, Color.PINK);
                squares[3] = new Square(x * size, (y + 2) * size, size, Color.PINK);
                break;
            }
            case T: {
                squares[0] = new Square(x * size, y * size, size, Color.MAGENTA);
                squares[1] = new Square((x + 1) * size, y * size, size, Color.MAGENTA);
                squares[2] = new Square((x + 2) * size, y * size, size, Color.MAGENTA);
                squares[3] = new Square((x + 1) * size, (y + 1) * size, size, Color.MAGENTA);
                break;
            }
        }
    }

    public static Piece random(int x, int y, int size) {
        return new Piece(x, y, size, PieceType.values()[new Random().nextInt(PieceType.values().length)]);
    }

    public Square[] getSquares() {
        return squares;
    }

    public void move(Direction direction, int size) {
        switch (direction) {
            case DOWN: {
                for (Square square : getSquares()) {
                    square.y += size;
                }
                break;
            }
            case LEFT: {
                for (Square square : getSquares()) {
                    square.x -= size;
                }
                break;
            }
            case RIGHT: {
                for (Square square : getSquares()) {
                    square.x += size;
                }
                break;
            }
        }
    }
}
