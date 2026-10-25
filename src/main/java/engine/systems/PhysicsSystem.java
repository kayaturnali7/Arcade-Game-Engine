package engine.systems;

import engine.actors.Actor;
import engine.geometry.AABB;
import engine.geometry.Shape;
import engine.util.MathUtil;

import java.util.ArrayList;

public class PhysicsSystem {

    public void update(Scene scene){
        ArrayList<Actor> actors = scene.getActors();
        checkCollisions(actors);   
    }

    private void checkCollisions(ArrayList<Actor> actors){
        int size = actors.size();

        for (int i = 0; i < size; i++){
            Actor e2 = actors.get(i);

            for (int j = i + 1; j < size; j++){
                Actor e1 = actors.get(j);

                Shape s1 = e1.shape;
                Shape s2 = e2.shape;

                if (AABBCollision(s1, s2)){
                    if (SATCollision(s1, s2)){
                        e1.onCollision(e2);
                        e2.onCollision(e1);
                    }
                }
            }
        }
    }

    public static boolean AABBCollision(Shape s1, Shape s2) {
        AABB e1Bounds = s1.AABB;
        AABB e2Bounds = s2.AABB;

        int e1X = e1Bounds.getX();
        int e1Y = e1Bounds.getY();
        int e1Width = e1Bounds.getWidth();
        int e1Height = e1Bounds.getHeight();

        int e2X = e2Bounds.getX();
        int e2Y = e2Bounds.getY();
        int e2Width = e2Bounds.getWidth();
        int e2Height = e2Bounds.getHeight();

        return e1X < e2X + e2Width && e1X + e1Width > e2X && e1Y < e2Y + e2Height && e1Y + e1Height > e2Y;
    }

    public static boolean SATCollision(Shape shape1, Shape shape2){

        Shape s1 = shape1;
        Shape s2 = shape2;

        for (int shape = 0; shape < 2; shape++){
            if (shape == 1){
                s1 = shape2;
                s2 = shape1;
            }

            int[] s1VerticesX = s1.verticesX;
            int[] s1VerticesY = s1.verticesY;
            int s1numVertices = s1.numVertices;

            int[] s2VerticesX = s2.verticesX;
            int[] s2VerticesY = s2.verticesY;
            int s2numVertices = s2.numVertices;

            for (int a = 0; a < s1numVertices; a++){
                int b = (a + 1) % s1numVertices;

                double axisProjX = s1VerticesY[a] - s1VerticesY[b];
                double axisProjY = s1VerticesX[b] - s1VerticesX[a];

                double min_r1 = Double.POSITIVE_INFINITY, max_r1 = Double.NEGATIVE_INFINITY;
                for (int i = 0; i < s1numVertices; i++){
                    int x = s1VerticesX[i];
                    int y = s1VerticesY[i];

                    double q = MathUtil.dot(x, axisProjX, y, axisProjY);
                    min_r1 = Math.min(min_r1, q);
                    max_r1 = Math.max(max_r1, q);
                }

                double min_r2 = Double.POSITIVE_INFINITY, max_r2 = Double.NEGATIVE_INFINITY;
                for (int i = 0; i < s2numVertices; i++){
                    int x = s2VerticesX[i];
                    int y = s2VerticesY[i];

                    double q = MathUtil.dot(x, axisProjX, y, axisProjY);
                    min_r2 = Math.min(min_r2, q);
                    max_r2 = Math.max(max_r2, q);
                }

                if (!(max_r2 >= min_r1 && max_r1 >= min_r2)){
                    return  false;
                }
            }
        }
        return true;
    }

}
