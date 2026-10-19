package asteroids.entities;

import engine.actors.Actor;
import engine.actors.Entity;
import engine.geometry.Circle;
import engine.geometry.Shape;
import engine.scene.Scene;

import java.awt.Color;

public class TestEntity extends Entity {

    private static final Color COLOR = Color.WHITE;

    public TestEntity(Scene scene, String name){
        super(scene, name);
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected Shape shapeInit() {
        return new Circle(50, 32, COLOR);
    }

    @Override
    public void onCollision(Actor actor) {
        
    }

    @Override
    public void onDestroy() {
        System.out.println(this + " destroyed");
    }
}
