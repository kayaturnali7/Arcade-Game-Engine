package engine.util;

public final class VectorMath {

    private VectorMath(){}

    public static double rotationX(double initialX, double initialY, double cos, double sin){
        return initialX * cos - initialY * sin;
    }

    public static double rotationY(double initialX, double initialY, double cos, double sin){
        return initialX * sin + initialY * cos;
    }

}
