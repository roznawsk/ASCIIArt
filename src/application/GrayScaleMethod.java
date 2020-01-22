package application;

import javafx.scene.paint.Color;

public enum GrayScaleMethod {
    None,
    Average,
    Luminance,
    DecompositionLight,
    DecompositionDark,
    OnlyRed,
    OnlyGreen,
    OnlyBlue;

    public double getGray(Color color){
        double r = color.getRed();
        double g = color.getGreen();
        double b = color.getBlue();
        switch(this) {
            case Average:
                return (r + g + b) / 3;
            case Luminance:
                return 0.3 * color.getRed() + 0.59 * color.getBlue() + 0.11 * color.getBlue();
            case DecompositionLight:
                return Math.max(r, Math.max(g, b));
            case DecompositionDark:
                return Math.min(r, Math.max(g, b));
            case OnlyBlue:
                return b;
            case OnlyRed:
                return r;
            case OnlyGreen:
                return g;
            default:
                return 0.5;
        }
    }
}
