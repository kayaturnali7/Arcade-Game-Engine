package asteroids.scenes;

import engine.actors.Entity;
import engine.systems.Scene;
// Let these be wildcard imports
import asteroids.entities.*;

import java.awt.Color;
import java.io.File;

public class GameScene extends Scene {
     private static final File SHOOT_SOUND = new File("src\\main\\java\\asteroids\\sounds\\fire.wav");



    private final double DRAG = 0.97;

    private Entity player;
    private Entity testEntity;

    @Override
    protected void initialize() {
        setTitle("Asteroid Remake");
        setDimension(900,600);
        setBgColor(Color.BLACK);

        addAudio(SHOOT_SOUND, "shoot");        
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
    }
}
