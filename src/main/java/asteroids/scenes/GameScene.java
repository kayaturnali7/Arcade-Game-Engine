package asteroids.scenes;

import engine.actors.Entity;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;

import java.awt.Color;

public class GameScene extends Scene {

    private final double DRAG = 0.97;

    private Entity player;
    private Entity testEntity;

    @Override
    protected void initialize() {
        setTitle("Asteroid Remake");
        setDimension(900,600);
        setBgColor(Color.BLACK);
    }

    @Override
    protected void onStart() {

        player = new Player(this);
        player.moveTo(dimension.width/2, dimension.height/2);

        testEntity = new TestEntity(this, "Test Entity");
        testEntity.moveTo(200, 200);

        instantiate(player);
        instantiate(testEntity);
    }


    @Override
    protected void onUpdate() {
        player.velocity.scale(DRAG);
        testEntity.velocity.scale(DRAG);
    }
}
