package iso53;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Square extends Rectangle {

    private final int arcWidth;
    private final int arcHeight;
    private final Color color;

    public Square(int x, int y, int size, Color color) {
        super(x, y, size, size);
        this.arcWidth = 6;
        this.arcHeight = 6;
        this.color = color;
    }

    public void paint(Graphics2D g2D) {
        // Create a rounded rectangle
        RoundRectangle2D roundedSquare = new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight);

        // Draw the main square
        g2D.setColor(color);
        g2D.fill(roundedSquare);

        // Draw the borders
        g2D.setColor(color.darker());
        g2D.draw(roundedSquare);

        // Add shading for 3D effect
        int gradientHeight = (int) (height * 0.3);
        int gradientWidth = (int) (width * 0.3);

        // Left shading
        GradientPaint gradientLeft = new GradientPaint(x, y, new Color(color.brighter().getRed(),
                color.brighter().getGreen(), color.brighter().getBlue(), 200), x + gradientWidth, y,
                new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
        g2D.setPaint(gradientLeft);
        g2D.fill(new RoundRectangle2D.Float(x, y, gradientWidth, height, arcWidth, arcHeight));

        // Right shading
        GradientPaint gradientRight = new GradientPaint(x + width - gradientWidth, y, new Color(color.getRed(),
                color.getGreen(), color.getBlue(), 200), x + width, y, new Color(color.darker().getRed(),
                color.darker().getGreen(), color.darker().getBlue(), 128));
        g2D.setPaint(gradientRight);
        g2D.fill(new RoundRectangle2D.Float(x + width - gradientWidth, y, gradientWidth, height, arcWidth, arcHeight));

        // Top shading
        GradientPaint gradientTop = new GradientPaint(x, y, new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(),
                Color.WHITE.getBlue(), 200), x, y + gradientHeight, new Color(color.getRed(),
                color.getGreen(), color.getBlue(), 128));
        g2D.setPaint(gradientTop);
        g2D.fill(new RoundRectangle2D.Float(x, y, width, gradientHeight, arcWidth, arcHeight));

        // Bottom shading
        GradientPaint gradientBottom = new GradientPaint(x, y + height - gradientHeight, new Color(color.getRed()
                , color.getGreen(), color.getBlue(), 200), x, y + height, new Color(Color.BLACK.getRed(),
                Color.BLACK.getGreen(), Color.BLACK.getBlue(), 128));
        g2D.setPaint(gradientBottom);
        g2D.fill(new RoundRectangle2D.Float(x, y + height - gradientHeight, width, gradientHeight, arcWidth,
                arcHeight));
    }
}