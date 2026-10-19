package engine.util;

import engine.data.Vector2;

public final class MathUtil {

    private MathUtil(){}

    public static double pythagorean(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public static double distance2D(Vector2 point1, Vector2 point2){
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        return Math.sqrt(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
    }

    public static double dot(double a1, double a2, double b1, double b2) {
        return (a1 * a2) + (b1 * b2);
    }
}

