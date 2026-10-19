package engine.util;

import engine.data.Vector2;

public final class GeometryUtil {

    private GeometryUtil(){}
    
    public static Vector2 minimumVertex(int[] verticesX, int[] verticesY){
        int minX = verticesX[0];
        int minY = verticesY[0];

        for (int i = 1; i < verticesX.length; i++){
            int currentX = verticesX[i];
            int currentY = verticesY[i];

            if (currentX < minX){
                minX = currentX;
            }

            if (currentY < minY){
                minY = currentY;
            }
        }

        return new Vector2(minX,minY);
    }

    public static Vector2 maximumVertex(int[] verticesX, int[] verticesY){
        int maxX = verticesX[0];
        int maxY = verticesY[0];

        for (int i = 1; i < verticesX.length; i++){
            int currentX = verticesX[i];
            int currentY = verticesY[i];

            //maxX
            if (currentX > maxX){
                maxX = currentX;
            }

            //maxY
            if (currentY > maxY){
                maxY = currentY;
            }
        }

        return new Vector2(maxX,maxY);
    }

    public static int[][] generateCircleVertices(int radius, int resolution) {
        int[][] vertices = new int[2][resolution];

        for (int i = 1; i < resolution + 1; i++ ){
            int x = (int) (radius * Math.cos(2 * Math.PI * i / resolution));
            int y = (int) (radius * Math.sin(2 * Math.PI * i / resolution));

            vertices[0][i-1] = x;
            vertices[1][i-1] = y;
        }

        return vertices;
    }




}
