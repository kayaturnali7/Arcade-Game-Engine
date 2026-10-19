package asteroids.entities;

import engine.actors.Actor;
import engine.core.InputHandler;

import engine.data.Vector2;
import engine.scene.Scene;
import engine.actors.Entity;
import engine.geometry.Shape;
import engine.geometry.ConvexPolygon;
import engine.core.Engine;
import java.io.File;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private static final File FIRE_SFX = new File("src\\main\\java\\asteroids\\sounds\\fire.wav");
    private static final File THRUST_SFX = new File("src\\main\\java\\asteroids\\sounds\\thrust.wav");

    private static final double SHOOT_COOLDOWN = 0.25;
    private static final int ROTATION_SPEED = 4;
    private static final double THRUST = 0.17;
    
    private double cooldownTick = 0;
    private boolean firing = false;
    private boolean canFire = false;

    public Player(Scene scene) {
        super(scene,"Player");
    }

    @Override
    protected void onUpdate() {
        firing = InputHandler.isKeyPressed(KeyEvent.VK_SPACE);

        if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
            applyThrust();
        }

        if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
            rotate(ROTATION_SPEED);
        } else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
            rotate(-ROTATION_SPEED);
        }

        if (canFire){
            fireLaser();
        }
        updateCooldown();
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


    private void applyThrust(){
        applyForce(direction,THRUST);
        scene.playSound(THRUST_SFX);
    }

    private void updateCooldown(){
        if (cooldownTick > 0){
            cooldownTick -= 1;
        }

        canFire = firing && cooldownTick == 0;
    }

    private void fireLaser() {
        cooldownTick = SHOOT_COOLDOWN * Engine.FPS;

        Vector2 vertex = shape.getVertex(0);
        scene.instantiate(new Laser(scene, vertex.x, vertex.y, direction));
        scene.playSound(FIRE_SFX);

    }

    @Override
    public void onCollision(Actor actor) {

    }
}
