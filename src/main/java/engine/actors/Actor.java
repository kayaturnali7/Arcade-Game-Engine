package engine.actors;

import engine.geometry.Shape;
import engine.scene.Scene;
import engine.data.Vector2;

public abstract class Actor {

    public final Scene scene;

    public Vector2 position = new Vector2(0,0);
    public double direction = 0;

    public final Shape shape;

    public final String name;
    private boolean active = true;

    public Actor(Scene scene, String name){
        this.scene = scene;
        this.name = name;
        shape = shapeInit();
    }

    public void update(){    
        onUpdate();
        shape.update(position, direction);  
    }

    public final void collision(Actor actor) {
        onCollision(actor);
    }

    protected abstract Shape shapeInit();
    protected abstract void onUpdate();
    public abstract void onCollision(Actor actor);
    protected abstract void onDestroy();

    public boolean isActive(){
        return active;
    }

    public void destroy(){
        onDestroy();
        active = false;
    }

    public String getName(){
        return name;
    }

    public void moveTo(double x, double y) {
        position.set(x,y);
    }

    public void rotate(double amount) {
        direction -= amount;
    }

    public void rotateTo(double direction) {
        this.direction = direction;
    }
}