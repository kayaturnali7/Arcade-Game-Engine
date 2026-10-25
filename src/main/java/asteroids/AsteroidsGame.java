package asteroids;

import engine.core.Engine;
import engine.systems.Scene;
import asteroids.scenes.*;

public class AsteroidsGame {
    public static void main(String[] args){
        Scene[] scenes = {new GameScene(), new MenuScene()};
        new Engine(scenes);
    }
}
