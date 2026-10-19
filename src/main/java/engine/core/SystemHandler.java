package engine.core;

import engine.audio.SoundSystem;
import engine.physics.PhysicsSystem;
import engine.render.RenderSystem;
import engine.scene.Scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class SystemHandler {
    private final PhysicsSystem physicsSystem = new PhysicsSystem();
    private final RenderSystem renderSystem = new RenderSystem();
    private final SoundSystem audioSystem = new SoundSystem();

    private final Map<String, Scene> scenes = new HashMap<>();
    private Scene currentScene;

    public SystemHandler(Scene[] scenes){
        this.currentScene = scenes[0];

        for (Scene scene: scenes){
            this.scenes.put(scene.title, scene);
        }
    }

    public void update(){
        currentScene.update();
        physicsSystem.update(currentScene);
    }

    public void render(Graphics2D g2d){
        renderSystem.render(g2d, currentScene);
    }

    public Scene getCurrentScene(){
        return currentScene;
    }

    public void switchScene(String sceneName){
        currentScene = scenes.get(sceneName);
    }
}
