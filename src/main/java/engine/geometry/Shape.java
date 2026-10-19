package engine.geometry;

import engine.data.Vector2;
import engine.util.VectorMath;

import java.awt.Color;

public class Shape {
    private final int[][] baseVertices;
    public final int numVertices;

    public final int[] verticesX;
    public final int[] verticesY;

    private Color color;
    private Color outlineColor;

    public final AABB AABB = new AABB();

    public Shape(int[][] vertices, Color color) {
        this.baseVertices = vertices;
        this.numVertices = baseVertices[0].length;

        this.verticesX = new int[numVertices];
        this.verticesY = new int[numVertices];
        this.color = color;
    }

    private void updateVertices(double x, double y, double direction){
        double cos = Math.cos(Math.toRadians(direction));
        double sin = Math.sin(Math.toRadians(direction));

        for (int i = 0; i < numVertices; i++){
            double initialX = baseVertices[0][i];
            double initialY = baseVertices[1][i];

            verticesX[i] = (int) x + (int) VectorMath.rotationX(initialX, initialY, cos, sin);
            verticesY[i] = (int) y + (int) VectorMath.rotationY(initialX, initialY, cos, sin);
        }
    }

    public void update(Vector2 position, double direction){
        updateVertices(position.x, position.y, direction+90);
        AABB.update(verticesX, verticesY);
    }

    public Color getColor(){
        return color;
    }

    public Vector2 getVertex(int index) {
        double x = verticesX[index];
        double y = verticesY[index];

        return new Vector2(x,y);
    }
}
