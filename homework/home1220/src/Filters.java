import java.awt.*;

public class Filters {

    public static int negative(int rgba) {
        Color col = new Color(rgba, true);
        col = new Color(255 - col.getRed(),
                255 - col.getGreen(),
                255 - col.getBlue());
        return col.getRGB();
    }

    public static int grayscale(int rgba) {
        int r = (rgba >> 16) & 0xFF;
        int g = (rgba >> 8) & 0xFF;
        int b = (rgba & 0xFF);

        int grayLevel = (r + g + b) / 3;

        return (grayLevel << 16) + (grayLevel << 8) + grayLevel;
    }

    public static int sepia(int rgba){

        int sepiaDepth = 20;
        int intensity = 10;

        Color color = new Color(rgba, true);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int gry = (r + g + b) / 3;

        r = g = b = gry;
        r = r + (sepiaDepth * 2);
        g = g + sepiaDepth;

        if (r > 255) { r = 255; }
        if (g > 255) { g = 255; }
        if (b > 255) { b = 255; }

        // Darken blue color to increase sepia effect
        b -= intensity;

        // normalize if out of bounds
        if (b < 0)   { b = 0; }
        if (b > 255) { b = 255; }

        color = new Color(r, g, b, color.getAlpha());

        return color.getRGB();
    }

}
