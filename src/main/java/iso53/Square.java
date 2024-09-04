package iso53;

import java.awt.*;

public class Square extends Rectangle {

    public Square(int x, int y, int size) {
        super(x, y, size, size);
    }

    public void paint(Graphics2D g2D, Color baseColor) {
        // Draw the main square
        g2D.setColor(baseColor);
        g2D.fill(this);

        // Draw the borders
        g2D.setColor(baseColor.darker());
        g2D.draw(this);

        // Add shading for 3D effect
        int gradientHeight = (int) (height * 0.3);
        int gradientWidth = (int) (width * 0.3);

        // Left shading
        GradientPaint gradientLeft = new GradientPaint(x, y, new Color(baseColor.brighter().getRed(),
                baseColor.brighter().getGreen(), baseColor.brighter().getBlue(), 200), x + gradientWidth, y,
                new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 128));
        g2D.setPaint(gradientLeft);
        g2D.fillRect(x, y, gradientWidth, height);

        // Right shading
        GradientPaint gradientRight = new GradientPaint(x + width - gradientWidth, y, new Color(baseColor.getRed(),
                baseColor.getGreen(), baseColor.getBlue(), 200), x + width, y, new Color(baseColor.darker().getRed(),
                baseColor.darker().getGreen(), baseColor.darker().getBlue(), 128));
        g2D.setPaint(gradientRight);
        g2D.fillRect(x + width - gradientWidth, y, gradientWidth, height);

        // Top shading
        GradientPaint gradientTop = new GradientPaint(x, y, new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(),
                Color.WHITE.getBlue(), 200), x, y + gradientHeight, new Color(baseColor.getRed(),
                baseColor.getGreen(), baseColor.getBlue(), 128));
        g2D.setPaint(gradientTop);
        g2D.fillRect(x, y, width, gradientHeight);

        // Bottom shading
        GradientPaint gradientBottom = new GradientPaint(x, y + height - gradientHeight, new Color(baseColor.getRed()
                , baseColor.getGreen(), baseColor.getBlue(), 200), x, y + height, new Color(Color.BLACK.getRed(),
                Color.BLACK.getGreen(), Color.BLACK.getBlue(), 128));
        g2D.setPaint(gradientBottom);
        g2D.fillRect(x, y + height - gradientHeight, width, gradientHeight);
    }
}
