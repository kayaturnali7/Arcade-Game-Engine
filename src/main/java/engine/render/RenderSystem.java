package engine.render;

import engine.actors.Actor;
import engine.geometry.Shape;
import engine.geometry.AABB;
import engine.scene.Scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class RenderSystem {

    public void render(Graphics2D g2d, Scene scene){
        ArrayList<Actor> actors = scene.getActors();

        for (Actor actor : actors){
            Shape shape = actor.shape;
            if (shape == null) continue;

            drawShape(g2d, shape);
            AABB aabb = shape.AABB;
            //drawAABB(g2d, aabb);
        }
    }

    private void drawShape(Graphics2D g2d, Shape shape){
        g2d.setColor(Color.WHITE);
        g2d.drawPolygon(shape.verticesX, shape.verticesY, shape.numVertices);
    }

    private void drawAABB(Graphics2D g2d, AABB aabb){
        int x = aabb.getX();
        int y = aabb.getY();
        int width = aabb.getWidth();
        int height = aabb.getHeight();

        g2d.setColor(Color.RED);
        g2d.drawRect(x, y ,width,height);
    }



}
