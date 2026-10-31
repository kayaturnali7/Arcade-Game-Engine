package asteroids.entities;

import engine.actors.Actor;
import engine.core.InputHandler;

import engine.data.Vector2;
import engine.actors.Entity;
import engine.geometry.Shape;
import engine.systems.Scene;
import engine.geometry.ConvexPolygon;
import engine.core.Engine;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;

public class Player extends Entity {
    private static final int ROTATION_SPEED = 4;
    private static final double THRUST = 0.17;
    
    private final double shootCooldown;
    private double cooldownTick = 0;

    public Player(Scene scene) {
        super(scene,"Player");
        Clip clip = scene.getSound("fire");
        shootCooldown = (double) clip.getFrameLength() / clip.getFormat().getSampleRate();
        System.out.println(shootCooldown);
    }

    @Override
    protected void onUpdate() {

        if (InputHandler.isKeyPressed(KeyEvent.VK_SPACE) && cooldownTick == 0){
            fireLaser();
        }

        if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
            applyForce(direction,THRUST);
        }

        if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
            rotate(ROTATION_SPEED);
        } else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
            rotate(-ROTATION_SPEED);
        }

        cooldownTick -= 1;
        cooldownTick = Math.clamp(cooldownTick, 0, shootCooldown*Engine.FPS);
        System.out.println(cooldownTick);
    }

    @Override
    protected Shape shapeInit() {
        double side = 60;
        double base = side * (7.0 / 9.0);
        double height = Math.sqrt((Math.pow(side, 2)) - Math.pow((base / 2), 2));

        double dTop = height * ((double) 2 / 3);
        double dBase = height * ((double) 1 / 3);

        Vector2 v1 = new Vector2(0, -dTop);
        Vector2 v2 = new Vector2(-(base /2), dBase);
        Vector2 v3 = new Vector2(base/2, dBase);

        int[][] vertices = new int[][]{
                {(int) v1.getX(), (int) v2.getX(), (int) v3.getX()},
                {(int) v1.getY(), (int) v2.getY(), (int) v3.getY()}
        };

        return new ConvexPolygon(vertices, Color.white);
    }

    private void fireLaser() {
        cooldownTick = shootCooldown * Engine.FPS;
        

        Vector2 vertex = shape.getVertex(0);
        scene.instantiate(new Laser(scene, vertex.x, vertex.y, direction));
        scene.playSound("fire");
    }

    @Override
    public void onCollision(Actor actor) {

    }

    @Override
    public void onDestroy() {

    }
}
