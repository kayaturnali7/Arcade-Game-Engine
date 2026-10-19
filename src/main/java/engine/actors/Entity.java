package engine.actors;

import engine.data.Vector2;
import engine.scene.Scene;

public abstract class Entity extends Actor {

    public final Vector2 velocity = new Vector2(0,0);
    private final Vector2 acceleration = new Vector2(0,0);

    public Entity(Scene scene, String name) {
        super(scene, name);
    }
    
    @Override 
    public void update() {
        onUpdate();
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.set(0,0);

        shape.update(position, direction);
    }

    public void applyForce(double direction, double magnitude) {
        double ax = Math.cos(Math.toRadians(direction)) * magnitude;
        double ay = Math.sin(Math.toRadians(direction)) * magnitude;

        acceleration.add(ax,ay);
    }

    public void applyForce(Vector2 v) {
        acceleration.add(v);
    }

    public void setVelocity(double direction, double magnitude) {
        double vx = Math.cos(Math.toRadians(direction)) * magnitude;
        double vy = Math.sin(Math.toRadians(direction)) * magnitude;

        velocity.set(vx,vy);
    }

    public void setVelocity(Vector2 v) {
        velocity.set(v);
    }
}